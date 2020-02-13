package gmail.roadtojob2019.brewery.service.impl;

import gmail.roadtojob2019.brewery.entity.AuthInfoEntity;
import gmail.roadtojob2019.brewery.repository.AuthInfoRepository;
import gmail.roadtojob2019.brewery.service.AuthInfoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthInfoServiceImpl implements AuthInfoService {

    private AuthInfoRepository authInfoRepository;

    @Override
    public Optional<AuthInfoEntity> findByLogin(String username) {
        return authInfoRepository.findByLogin(username);
    }
}
