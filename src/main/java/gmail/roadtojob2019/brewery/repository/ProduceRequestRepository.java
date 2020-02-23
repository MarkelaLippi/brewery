package gmail.roadtojob2019.brewery.repository;

import gmail.roadtojob2019.brewery.entity.ProduceRequest;
import gmail.roadtojob2019.brewery.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProduceRequestRepository extends JpaRepository<ProduceRequest,Long> {
    List<ProduceRequest> findByStatus(Status status);
}
