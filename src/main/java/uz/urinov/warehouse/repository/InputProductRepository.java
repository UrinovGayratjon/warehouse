package uz.urinov.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.urinov.warehouse.entity.InputProduct;

@Repository
public interface InputProductRepository extends JpaRepository<InputProduct, Integer> {
}
