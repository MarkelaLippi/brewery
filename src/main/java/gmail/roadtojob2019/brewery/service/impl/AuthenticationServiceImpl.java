package gmail.roadtojob2019.brewery.service.impl;

import gmail.roadtojob2019.brewery.dto.SignInRequestDto;
import gmail.roadtojob2019.brewery.dto.UserSignInResponseDto;
import gmail.roadtojob2019.brewery.entity.UserEntity;
import gmail.roadtojob2019.brewery.repository.UserRepository;
import gmail.roadtojob2019.brewery.service.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    
    @Autowired
    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserSignInResponseDto singIn(SignInRequestDto request) {




        return null;
    }
}
