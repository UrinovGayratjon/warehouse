package uz.urinov.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.urinov.warehouse.entity.Product;

import java.util.Optional;

@RepositoryRestResource(path = "product")
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query(value = "select * from product where id=(select max(id) from product)", nativeQuery = true)
    Optional<Product> getMaxId();
}
