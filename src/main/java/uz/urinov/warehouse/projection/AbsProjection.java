package uz.urinov.warehouse.projection;


import org.springframework.data.rest.core.config.Projection;
import uz.urinov.warehouse.entity.Currency;
import uz.urinov.warehouse.entity.Measurement;
import uz.urinov.warehouse.entity.Warehouse;

@Projection(types = {Currency.class, Warehouse.class, Measurement.class})
public interface AbsProjection {

    Integer getId();

    String getName();

    boolean isActive();
}
