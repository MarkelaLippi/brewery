package gmail.roadtojob2019.brewery.controller.unit;

import gmail.roadtojob2019.brewery.entity.AuthInfoEntity;
import gmail.roadtojob2019.brewery.entity.UserEntity;
import gmail.roadtojob2019.brewery.repository.AuthInfoRepository;
import gmail.roadtojob2019.brewery.repository.UserRepository;
import gmail.roadtojob2019.brewery.security.UserRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
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
@TestPropertySource("classpath:application-test.properties")
class AuthControllerUnitTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;
    @MockBean
    private AuthInfoRepository authInfoRepository;

    @Test
    public void testCustomerSignUpIsCreated() throws Exception {
        // given
        final UserEntity userEntity = getUserEntity();
        final AuthInfoEntity authInfoEntity = getAuthInfoEntity(userEntity);
        willReturn(Optional.empty(), Optional.of(authInfoEntity)).given(authInfoRepository).findByLogin("Ivanov@gmail.com");
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
        verify(authInfoRepository, times(2)).findByLogin(any(String.class));
        verify(userRepository, times(1)).save(any(UserEntity.class));
        verify(authInfoRepository, times(1)).save(any(AuthInfoEntity.class));
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
                .password("$2a$08$Glcfbit5s.gZwyxU1M6/e.l2Z76DbbFRtFuqGuW2KEQvE5IWQYUOG")
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
}

