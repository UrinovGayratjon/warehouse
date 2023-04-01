package uz.urinov.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.urinov.warehouse.entity.Currency;
import uz.urinov.warehouse.projection.AbsProjection;

@RepositoryRestResource(path = "currency", excerptProjection = AbsProjection.class)
public interface CurrencyRepository extends JpaRepository<Currency, Integer> {
}
