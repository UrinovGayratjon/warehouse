package uz.urinov.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.urinov.warehouse.entity.Supplier;
import uz.urinov.warehouse.projection.SupplierProjection;

@RepositoryRestResource(path = "supplier", excerptProjection = SupplierProjection.class)
public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
}
