package gmail.roadtojob2019.brewery.controller;

import gmail.roadtojob2019.brewery.entity.*;
import gmail.roadtojob2019.brewery.repository.ProduceRequestRepository;
import gmail.roadtojob2019.brewery.security.UserRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class ProduceRequestControllerUnitTest extends AbstractControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProduceRequestRepository produceRequestRepository;

    @Test
    public void testSalesProduceRequestIsCreated() throws Exception {
        // given
        final ProduceRequest produceRequest = getProduceRequest();
        willReturn(produceRequest).given(produceRequestRepository).save(any(ProduceRequest.class));
        String token = signInAsUser(UserRole.SALES);
        // when
        mockMvc.perform(post("/brewery/sales/requests").header("Authorization", token)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"date\" : \"2020-02-05\",\n" +
                        "  \"term\" : \"2020-02-10\",\n" +
                        "  \"status\" : \"NEW\",\n" +
                        "  \"produceRequestItemDtos\" : [\n" +
                        "                                 {\"productId\" : 1,\n" +
                        "                                  \"amount\" : 350 }\n" +
                        "                               ]\n" +
                        "}"))
                //then
                .andExpect(status().isCreated())
                .andExpect(content().json("1"));
        verify(produceRequestRepository, times(1)).save(any(ProduceRequest.class));
    }

    @Test
    public void testGetProduceRequestsByStatusIsOk() throws Exception {
        // given
        final ProduceRequest produceRequest = getProduceRequest();
        final List<ProduceRequest> produceRequests = List.of(produceRequest);
        willReturn(produceRequests).given(produceRequestRepository).findByStatus(Status.NEW);
        String token = signInAsUser(UserRole.BREWER);
        //when
        mockMvc.perform(get("/brewery/brewer/requests/?status=new").header("Authorization", token))
                //then
                .andExpect(status().isOk())
                .andExpect(content().json("[\n" +
                        "  {\n" +
                        "  \"date\" : \"05.02.2020\",\n" +
                        "  \"term\" : \"10.02.2020\",\n" +
                        "  \"status\" : \"NEW\",\n" +
                        "  \"produceRequestItemDtos\" : [\n" +
                        "                                 {\"productId\" : 1,\n" +
                        "                                  \"amount\" : 350.0 }\n" +
                        "                               ]\n" +
                        "  }\n" +
                        "]"));
        verify(produceRequestRepository, times(1)).findByStatus(any(Status.class));
    }

    @Test
    public void testGetProduceRequestIsOk() throws Exception {
        // given
        final Optional<ProduceRequest> produceRequest = Optional.of(getProduceRequest());
        willReturn(produceRequest).given(produceRequestRepository).findById(1L);
        String token = signInAsUser(UserRole.BREWER);
        //when
        mockMvc.perform(get("/brewery/brewer/requests/1").header("Authorization", token))
                //then
                .andExpect(status().isOk())
                .andExpect(content().json("{\n" +
                        "  \"date\" : \"05.02.2020\",\n" +
                        "  \"term\" : \"10.02.2020\",\n" +
                        "  \"produceRequestItemDtos\" : [\n" +
                        "                                 {\"productId\" : 1,\n" +
                        "                                  \"amount\" : 350.0 }\n" +
                        "                               ]\n" +
                        "  }\n"));
        verify(produceRequestRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetProduceRequestThrowsBrewerySuchProduceRequestNotFoundException() throws Exception {
        // given
        willReturn(Optional.empty()).given(produceRequestRepository).findById(1L);
        String token = signInAsUser(UserRole.BREWER);
        //when
        mockMvc.perform(get("/brewery/brewer/requests/1").header("Authorization", token))
                //then
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("errorMessage").value("ProduceRequest with id = 1 was not found"));
        verify(produceRequestRepository, times(1)).findById(1L);
    }

    @Test
    public void testChangeProduceRequestStatusIsOk() throws Exception {
        // given
        final Optional<ProduceRequest> produceRequest = Optional.of(getProduceRequest());
        willReturn(produceRequest).given(produceRequestRepository).findById(1L);
        willReturn(produceRequest.get()).given(produceRequestRepository).save(any(ProduceRequest.class));
        String token = signInAsUser(UserRole.BREWER);
        //when
        mockMvc.perform(patch("/brewery/brewer/requests/1").header("Authorization", token)
                .contentType(MediaType.APPLICATION_JSON)
                .content("  {\n" +
                        "  \"status\" : \"In_progress\"\n" +
                        "  }\n"))
                //then
                .andExpect(status().isOk())
                .andExpect(content().json("1"));
        verify(produceRequestRepository, times(1)).findById(1L);
        verify(produceRequestRepository, times(1)).save(any(ProduceRequest.class));
    }

    @Test
    public void testChangeProduceRequestStatusThrowsBrewerySuchProduceRequestNotFoundException() throws Exception {
        // given
        willReturn(Optional.empty()).given(produceRequestRepository).findById(1L);
        String token = signInAsUser(UserRole.BREWER);
        //when
        mockMvc.perform(patch("/brewery/brewer/requests/1").header("Authorization", token)
                .contentType(MediaType.APPLICATION_JSON)
                .content("  {\n" +
                        "  \"status\" : \"In_progress\"\n" +
                        "  }\n"))
                //then
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("errorMessage").value("ProduceRequest with id = 1 was not found"));
        verify(produceRequestRepository, times(1)).findById(1L);
        verify(produceRequestRepository, times(0)).save(any(ProduceRequest.class));
    }

    private ProduceRequest getProduceRequest() {
        final ProduceRequestItem produceRequestItem = ProduceRequestItem.builder()
                .id(1L)
                .amount(350.0)
                .product(Product.builder().id(1L).build())
                .build();

        return ProduceRequest.builder()
                .id(1L)
                .date(LocalDate.of(2020, 2, 5))
                .status(Status.NEW)
                .term(LocalDate.of(2020, 2, 10))
                .produceRequestItems(List.of(produceRequestItem))
                .build();
    }
}