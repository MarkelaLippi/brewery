package gmail.roadtojob2019.brewery.repository;

import gmail.roadtojob2019.brewery.entity.AuthInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthInfoRepository extends JpaRepository<AuthInfoEntity, Long> {

    Optional<AuthInfoEntity> findByLogin(String username);
}
