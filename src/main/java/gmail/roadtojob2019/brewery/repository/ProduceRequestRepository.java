package gmail.roadtojob2019.brewery.repository;

import gmail.roadtojob2019.brewery.entity.ProduceRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProduceRequestRepository extends JpaRepository<ProduceRequest,Long> {
}
