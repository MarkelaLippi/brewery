package gmail.roadtojob2019.brewery.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import gmail.roadtojob2019.brewery.dto.UserSignInResponseDto;
import gmail.roadtojob2019.brewery.entity.AuthInfoEntity;
import gmail.roadtojob2019.brewery.entity.UserEntity;
import gmail.roadtojob2019.brewery.repository.AuthInfoRepository;
import gmail.roadtojob2019.brewery.repository.UserRepository;
import gmail.roadtojob2019.brewery.security.UserRole;
import gmail.roadtojob2019.brewery.service.AuthInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.hamcrest.Matchers.hasLength;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class AuthControllerUnitTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @MockBean
    private AuthInfoService authInfoService;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private AuthInfoRepository authInfoRepository;

    @Test
    public void testCustomerSignUpIsCreated() throws Exception {
        // given
        final UserEntity userEntity = getUserEntity();
        final AuthInfoEntity authInfoEntity = getAuthInfoEntity(userEntity);
        willReturn(Optional.empty()).given(authInfoRepository).findByLogin("Ivanov@gmail.com");
        willReturn(userEntity).given(userRepository).save(any(UserEntity.class));
        willReturn(authInfoEntity).given(authInfoRepository).save(any(AuthInfoEntity.class));
        //when
        mockMvc.perform(post("/brewery/sign-up")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"email\" : \"Ivanov@gmail.com\",\n" +
                        "  \"password\" : \"12345678\", \n" +
                        "  \"phone\" : \"+375297772255\",\n" +
                        "  \"fullName\" : \"Ivanov Ivan\" \n" +
                        "}"))
                //then
                .andExpect(status().isCreated())
                .andExpect(jsonPath("token", hasLength(145)));
    }

    @Test
    public void testCustomerSignUpWhenUserAlreadyExisted() throws Exception {
        //given
        final UserEntity userEntity = getUserEntity();
        final AuthInfoEntity authInfoEntity = getAuthInfoEntity(userEntity);
        final Optional<AuthInfoEntity> requiredAuthInfoEntity = Optional.of(authInfoEntity);
        willReturn(requiredAuthInfoEntity).given(authInfoRepository).findByLogin("Ivanov@gmail.com");
        //when
        mockMvc.perform(post("/brewery/sign-up")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"email\" : \"Ivanov@gmail.com\",\n" +
                        "  \"password\" : \"12345678\", \n" +
                        "  \"phone\" : \"+375297772255\",\n" +
                        "  \"fullName\" : \"Ivanov Ivan\" \n" +
                        "}"))
                //then
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("errorMessage").value("User with email=" + userEntity.getEmail() + " already exists"));
        verify(authInfoRepository, times(1)).findByLogin(any(String.class));
    }

    @Test
    public void testUserSignInIsOk() throws Exception {
        //given
        final UserEntity userEntity = getUserEntity();
        final AuthInfoEntity authInfoEntity = getAuthInfoEntity(userEntity);
        Optional<AuthInfoEntity> requiredAuthInfoEntity = Optional.of(authInfoEntity);
        willReturn(requiredAuthInfoEntity).given(authInfoRepository).findByLogin("Ivanov@gmail.com");
        //when
        mockMvc.perform(post("/brewery/sign-in")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"email\" : \"Ivanov@gmail.com\",\n" +
                        "  \"password\" : \"12345678\"\n" +
                        "}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("token", hasLength(145)));
        verify(authInfoRepository, times(1)).findByLogin(any(String.class));
    }

    @Test
    public void testUserSignInWithWrongPassword() throws Exception {
        //given
        final UserEntity userEntity = getUserEntity();
        final AuthInfoEntity authInfoEntity = getAuthInfoEntity(userEntity);
        Optional<AuthInfoEntity> requiredAuthInfoEntity = Optional.of(authInfoEntity);
        willReturn(requiredAuthInfoEntity).given(authInfoRepository).findByLogin("Ivanov@gmail.com");
        //when
        mockMvc.perform(post("/brewery/sign-in")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"email\" : \"Ivanov@gmail.com\",\n" +
                        "  \"password\" : \"Wrong password\"\n" +
                        "}"))
                //then
                .andExpect(status().isForbidden());
        verify(authInfoRepository, times(1)).findByLogin(any(String.class));
    }

    @Test
    public void testUserSignInWithWrongEmail() throws Exception {
        //given
        willReturn(Optional.empty()).given(authInfoRepository).findByLogin("Wrong email");
        //when
        mockMvc.perform(post("/brewery/sign-in")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"email\" : \"Wrong email\",\n" +
                        "  \"password\" : \"12345678\"\n" +
                        "}"))
                //then
                .andExpect(status().isForbidden());
        verify(authInfoRepository, times(1)).findByLogin(any(String.class));
    }

    private AuthInfoEntity getAuthInfoEntity(UserEntity userEntity) {
        return AuthInfoEntity.builder()
                .id(1L)
                .login("Ivanov@gmail.com")
                .password("$2a$08$awwha45hqJvFrBQkC1ZFvO7mmST85cbTcJRBJRlMcqsZupmKo57mS")
                .user(userEntity)
                .build();
    }

    private UserEntity getUserEntity() {
        return UserEntity.builder()
                .id(1L)
                .email("Ivanov@gmail.com")
                .userRole(UserRole.CUSTOMER)
                .build();
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

