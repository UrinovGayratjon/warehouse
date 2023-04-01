package uz.urinov.warehouse.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class OutputDTO {
    @NotNull
    private Integer warehouseId;
    @NotNull
    private Integer clientId;
    @NotNull
    private Integer currencyId;
    @NotNull
    private String factureNumber;
}
