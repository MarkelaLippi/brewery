package gmail.roadtojob2019.brewery.service;

import gmail.roadtojob2019.brewery.entity.AuthInfoEntity;

import java.util.Optional;

public interface AuthInfoService {
    Optional<AuthInfoEntity> findByLogin(String username);
}
