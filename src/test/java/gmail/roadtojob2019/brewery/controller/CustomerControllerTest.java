package gmail.roadtojob2019.brewery.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import gmail.roadtojob2019.brewery.dto.ProductDto;
import gmail.roadtojob2019.brewery.dto.UserSignInResponseDto;
import gmail.roadtojob2019.brewery.entity.AuthInfoEntity;
import gmail.roadtojob2019.brewery.entity.Product;
import gmail.roadtojob2019.brewery.entity.UserEntity;
import gmail.roadtojob2019.brewery.repository.ProductRepository;
import gmail.roadtojob2019.brewery.security.UserRole;
import gmail.roadtojob2019.brewery.service.AuthInfoService;
import gmail.roadtojob2019.brewery.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasLength;
import static org.mockito.BDDMockito.willReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource("classpath:application-test.properties")
class CustomerControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @MockBean
    private AuthInfoService authInfoService;
    @SpyBean
    private CustomerService customerService;
@Autowired
    private ProductRepository productRepository;

    @Test
    public void testCustomerSignUpIsCreated() throws Exception {
        // given
        willReturn(Optional.empty(), Optional.of(createAuthInfo())).given(authInfoService)
                .findByLogin("Ivanov@gmail.com");
        // when
        mockMvc.perform(post("/brewery/customer/sign-up")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"email\" : \"Ivanov@gmail.com\",\n" +
                        "  \"phone\" : \"+375297772255\",\n" +
                        "  \"login\" : \"Ivanov\",\n" +
                        "  \"password\" : \"12345678\" \n" +
                        "}"))
                // then
                .andExpect(status().isCreated())
                .andExpect(jsonPath("token", hasLength(145)));
    }

    @Test
    public void testCustomerSignUpWhenUserAlreadyExisted() throws Exception {
        // given
        // when
        mockMvc.perform(post("/brewery/customer/sign-up")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"email\" : \"Ivanov@gmail.com\",\n" +
                        "  \"phone\" : \"+375297772255\",\n" +
                        "  \"login\" : \"Ivanov\",\n" +
                        "  \"password\" : \"12345678\" \n" +
                        "}"))
                // then
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testCustomerSignInIsOk() throws Exception {
        // given
        // when
        mockMvc.perform(post("/brewery/customer/sign-in")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"email\" : \"Ivanov@gmail.com\",\n" +
                        "  \"password\" : \"12345678\"\n" +
                        "}"))
                // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("token", hasLength(145)));
    }

    @Test
    public void testCustomerSignInWithWrongPassword() throws Exception {
        // given
        // when
        mockMvc.perform(post("/brewery/customer/sign-in")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"email\" : \"Ivanov@gmail.com\",\n" +
                        "  \"password\" : \"Wrong password\"\n" +
                        "}"))
                // then
                .andExpect(status().isForbidden());
    }

    @Test
    public void testCustomerSignInWithWrongEmail() throws Exception {
        // given
        // when
        mockMvc.perform(post("/brewery/customer/sign-in")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"email\" : \"Wrong email\",\n" +
                        "  \"password\" : \"12345678\"\n" +
                        "}"))
                // then
                .andExpect(status().isForbidden());
    }

    @Test
    void testGetAllProductsIsOk() throws Exception {
        // given
        productRepository.save(Product.builder()
                .id(1L)
                .name("BudBeer")
                .description("Dark, 4,6%...")
                .price(2.0)
                .build());

        mockMvc.perform(get("/brewery/customer/products"))
                .andExpect(status().isOk())
                .andExpect(content().json("[\n" +
                        "  {\n" +
                        "    \"id\" : 1, \n" +
                        "    \"name\" : \"BudBeer\",\n" +
                        "    \"description\" : \"Dark, 4,6%...\",\n" +
                        "    \"price\" : 2.0 \n" +
                        "  }\n" +
                        "]"));
    }




    @Test
    public void testCustomerOrderIsCreated() throws Exception {

        mockMvc.perform(post("/brewery/customer/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"date\" : \"2020-02-05\",\n" +
                        "  \"name_beer\" : \"BudBeer\",\n" +
                        "  \"amount\" : 200,\n" +
                        "  \"customer_id\" : 1 \n" +
                        "}"))
                .andExpect(status().isCreated())
                .andExpect(content().json("{\n" +
                        "  \"id\" : 1\n" +
                        "}"));
    }

    @Test
    public void testCustomerReviewIsCreated() throws Exception {
        mockMvc.perform(post("/brewery/customer/review")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"date\" : \"2020-02-06\",\n" +
                        "  \"content\" : \"Хочу поблагодарить специалиста по продажам ...\",\n" +
                        "  \"customer_id\" : \"1\",\n" +
                        "  \"order_id\" : \"1\"\n" +
                        "}"))
                .andExpect(status().isCreated())
                .andExpect(content().json("{\n" +
                        "  \"id\" : 1\n" +
                        "}"));
    }

    private String signInAsCustomer() throws Exception {
        final AuthInfoEntity authInfo = createAuthInfo();
        willReturn(Optional.of(authInfo)).given(authInfoService).findByLogin("Ivanov@gmail.com");

        final String response = mockMvc.perform(post("/brewery/customer/sign-in")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"email\" : \"Ivanov@gmail.com\",\n" +
                        "  \"password\" : \"12345678\"\n" +
                        "}"))
                // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("token", hasLength(145)))
                .andReturn().getResponse().getContentAsString();
        return "Bearer " + objectMapper.readValue(response, UserSignInResponseDto.class).getToken();
    }

    private AuthInfoEntity createAuthInfo() {
        final UserEntity user = new UserEntity();
        user.setUserRole(UserRole.CUSTOMER);
        user.setEmail("Ivanov@gmail.com");

        final AuthInfoEntity authInfo = new AuthInfoEntity();
        authInfo.setLogin(user.getEmail());
        authInfo.setPassword(passwordEncoder.encode("12345678"));
        authInfo.setUser(user);
        return authInfo;
    }
}

