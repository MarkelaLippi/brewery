package gmail.roadtojob2019.brewery.controller;

import gmail.roadtojob2019.brewery.entity.*;
import gmail.roadtojob2019.brewery.repository.ProduceRequestRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.willReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class ProduceRequestControllerUnitTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProduceRequestRepository produceRequestRepository;

    @Test
    public void testSalesProduceRequestIsCreated() throws Exception {
        // given
        //signInAsCustomer();
        final ProduceRequest produceRequest = getProduceRequest();

        willReturn(produceRequest).given(produceRequestRepository).save(any(ProduceRequest.class));
        // when
        mockMvc.perform(post("/brewery/sales/requests")
                //then
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"date\" : \"2020-02-05\",\n" +
                        "  \"term\" : \"2020-02-10\",\n" +
                        "  \"status\" : \"NEW\",\n" +
                        "  \"produceRequestItemDtos\" : [\n" +
                        "                                 {\"productId\" : 1,\n" +
                        "                                  \"amount\" : 150 }\n" +
                        "                               ]\n" +
                        "}"))
                .andExpect(status().isCreated())
                .andExpect(content().json("1"));
    }

    private ProduceRequest getProduceRequest() {
        final ProduceRequestItem produceRequestItem = ProduceRequestItem.builder()
                .id(1L)
                .amount(150.0)
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

    @Test
    public void testGetProduceRequestsByStatusIsOk() throws Exception {
        mockMvc.perform(get("/brewery/brewer/requests/?status=new"))
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
    }

    @Test
    public void testGetProduceRequestIsOk() throws Exception {
        mockMvc.perform(get("/brewery/brewer/requests/1"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\n" +
                        "  \"date\" : \"05.02.2020\",\n" +
                        "  \"term\" : \"10.02.2020\",\n" +
                        "  \"produceRequestItemDtos\" : [\n" +
                        "                                 {\"productId\" : 1,\n" +
                        "                                  \"amount\" : 350.0 }\n" +
                        "                               ]\n" +
                        "  }\n"));
    }

    @Test
    public void testChangeProduceRequestStatusIsOk() throws Exception {
        mockMvc.perform(patch("/brewery/brewer/requests/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("  {\n" +
                        "  \"status\" : \"In_progress\"\n" +
                        "  }\n"))
                .andExpect(status().isOk())
                .andExpect(content().json("1"));
    }

}