package gmail.roadtojob2019.brewery.service.impl;

import gmail.roadtojob2019.brewery.entity.AuthInfoEntity;
import gmail.roadtojob2019.brewery.repository.AuthInfoRepository;
import gmail.roadtojob2019.brewery.service.AuthInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthInfoServiceImpl implements AuthInfoService {

    private final AuthInfoRepository authInfoRepository;

    @Override
    public Optional<AuthInfoEntity> findByLogin(final String username) {
        return authInfoRepository.findByLogin(username);
    }
}
