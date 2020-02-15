package gmail.roadtojob2019.brewery.repository;

import gmail.roadtojob2019.brewery.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
}
