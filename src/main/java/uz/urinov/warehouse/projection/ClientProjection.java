package uz.urinov.warehouse.projection;

import org.springframework.data.rest.core.config.Projection;
import uz.urinov.warehouse.entity.Client;

@Projection(types = {Client.class})
public interface ClientProjection {

    Integer getId();

    String getName();

    String getPhoneNumber();
}
