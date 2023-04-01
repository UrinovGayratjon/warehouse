package uz.urinov.warehouse.projection;

import org.springframework.data.rest.core.config.Projection;
import uz.urinov.warehouse.entity.Supplier;

@Projection(types = {Supplier.class})
public interface SupplierProjection {
    Integer getId();

    String getName();

    String getPhoneNumber();

    boolean isActive();
}
