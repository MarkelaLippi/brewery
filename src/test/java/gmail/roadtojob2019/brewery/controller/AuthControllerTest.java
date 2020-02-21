package gmail.roadtojob2019.brewery.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import gmail.roadtojob2019.brewery.dto.UserSignInResponseDto;
import gmail.roadtojob2019.brewery.entity.AuthInfoEntity;
import gmail.roadtojob2019.brewery.entity.UserEntity;
import gmail.roadtojob2019.brewery.security.UserRole;
import gmail.roadtojob2019.brewery.service.AuthInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.hamcrest.Matchers.hasLength;
import static org.mockito.BDDMockito.willReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource("classpath:application-test.properties")
class AuthControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @MockBean
    private AuthInfoService authInfoService;

    @Test
    public void testCustomerSignUpIsCreated() throws Exception {
        mockMvc.perform(post("/brewery/sign-up")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"email\" : \"Ivanov@gmail.com\",\n" +
                        "  \"password\" : \"12345678\", \n" +
                        "  \"phone\" : \"+375297772255\",\n" +
                        "  \"fullName\" : \"Ivanov Ivan\" \n" +
                        "}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("token", hasLength(145)));
    }

    @Test
    public void testCustomerSignUpWhenUserAlreadyExisted() throws Exception {
        mockMvc.perform(post("/brewery/sign-up")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"email\" : \"Ivanov@gmail.com\",\n" +
                        "  \"password\" : \"12345678\", \n" +
                        "  \"phone\" : \"+375297772255\",\n" +
                        "  \"fullName\" : \"Ivanov Ivan\" \n" +
                        "}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testUserSignInIsOk() throws Exception {
        mockMvc.perform(post("/brewery/sign-in")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"email\" : \"Petrov@gmail.com\",\n" +
                        "  \"password\" : \"12345678\"\n" +
                        "}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("token", hasLength(145)));
    }

    @Test
    public void testUserSignInWithWrongPassword() throws Exception {
        mockMvc.perform(post("/brewery/sign-in")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"email\" : \"Sydorov@gmail.com\",\n" +
                        "  \"password\" : \"Wrong password\"\n" +
                        "}"))
                .andExpect(status().isForbidden());
    }

    @Test
    public void testUserSignInWithWrongEmail() throws Exception {
        mockMvc.perform(post("/brewery/sign-in")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"email\" : \"Wrong email\",\n" +
                        "  \"password\" : \"12345678\"\n" +
                        "}"))
                .andExpect(status().isForbidden());
    }

    private String signInAsCustomer() throws Exception {
        final AuthInfoEntity authInfo = createAuthInfo();
        willReturn(Optional.of(authInfo)).given(authInfoService).findByLogin("Ivanov@gmail.com");

        final String response = mockMvc.perform(post("/brewery/sign-in")
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

