package uz.urinov.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.urinov.warehouse.entity.OutputProduct;

@Repository
public interface OutputProductRepository extends JpaRepository<OutputProduct, Integer> {
}
