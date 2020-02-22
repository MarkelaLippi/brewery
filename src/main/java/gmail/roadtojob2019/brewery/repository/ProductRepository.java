package gmail.roadtojob2019.brewery.repository;

import gmail.roadtojob2019.brewery.entity.Product;
import gmail.roadtojob2019.brewery.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByType(Type type);
}
