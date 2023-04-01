package uz.urinov.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.urinov.warehouse.entity.Warehouse;
import uz.urinov.warehouse.projection.AbsProjection;

@RepositoryRestResource(path = "warehouse", excerptProjection = AbsProjection.class)
public interface WarehouseRepository extends JpaRepository<Warehouse, Integer> {
}
