package uz.urinov.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.urinov.warehouse.entity.Measurement;
import uz.urinov.warehouse.projection.AbsProjection;

@RepositoryRestResource(path = "measurement", excerptProjection = AbsProjection.class)
public interface MeasurementRepository extends JpaRepository<Measurement, Integer> {
}
