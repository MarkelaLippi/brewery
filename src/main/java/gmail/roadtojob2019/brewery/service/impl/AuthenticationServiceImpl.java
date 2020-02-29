package gmail.roadtojob2019.brewery.service.impl;

import gmail.roadtojob2019.brewery.dto.CustomerSignUpRequestDto;
import gmail.roadtojob2019.brewery.dto.SignInRequestDto;
import gmail.roadtojob2019.brewery.dto.UserSignInResponseDto;
import gmail.roadtojob2019.brewery.entity.AuthInfoEntity;
import gmail.roadtojob2019.brewery.entity.UserEntity;
import gmail.roadtojob2019.brewery.exception.BrewerySuchCustomerAlreadyExistException;
import gmail.roadtojob2019.brewery.mapper.CustomerSignUpRequestMapper;
import gmail.roadtojob2019.brewery.repository.AuthInfoRepository;
import gmail.roadtojob2019.brewery.repository.UserRepository;
import gmail.roadtojob2019.brewery.security.JwtUtil;
import gmail.roadtojob2019.brewery.security.UserRole;
import gmail.roadtojob2019.brewery.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    private final AuthInfoRepository authInfoRepository;
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final CustomerSignUpRequestMapper customerSignUpRequestMapper;

    @Override
    @Transactional
    public UserSignInResponseDto signUp(final CustomerSignUpRequestDto signUpRequest) throws BrewerySuchCustomerAlreadyExistException {
        if (authInfoRepository.findByLogin(signUpRequest.getEmail()).isPresent()) {
            throw new BrewerySuchCustomerAlreadyExistException("User with email=" + signUpRequest.getEmail() + " already exists");
        }
        saveUser(signUpRequest);
        final UserSignInResponseDto userSignInResponseDto = singIn(new SignInRequestDto(signUpRequest.getEmail(), signUpRequest.getPassword()));
        return userSignInResponseDto;

    }

    private void saveUser(final CustomerSignUpRequestDto signUpRequest) {
        final UserEntity userEntity = customerSignUpRequestMapper.sourceToDestination(signUpRequest);
        userEntity.setUserRole(UserRole.CUSTOMER);
        final UserEntity savedUser = userRepository.save(userEntity);
        saveAuthInfo(signUpRequest, savedUser);
    }

    private void saveAuthInfo(final CustomerSignUpRequestDto signUpRequest, final UserEntity savedUser) {
        final AuthInfoEntity authInfoEntity = new AuthInfoEntity();
        authInfoEntity.setLogin(signUpRequest.getEmail());
        authInfoEntity.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        authInfoEntity.setUser(savedUser);
        authInfoRepository.save(authInfoEntity);
    }

    @Override
    @Transactional
    public UserSignInResponseDto singIn(SignInRequestDto signInRequest) {
        final Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getEmail(), signInRequest.getPassword()));
        final UserDetails principal = (UserDetails) authentication.getPrincipal();
        final Collection<? extends GrantedAuthority> authorities = principal.getAuthorities();

        final String ROLE = authorities
                .stream()
                .findFirst()
                .get()
                .getAuthority();
                //.substring(5);

        User user = new User(signInRequest.getEmail(), signInRequest.getPassword(), List.of(new SimpleGrantedAuthority(ROLE)));
        String token = jwtUtil.generateToken(user);
        UserSignInResponseDto userSignInResponseDto = new UserSignInResponseDto(token);
        return userSignInResponseDto;
    }
}
