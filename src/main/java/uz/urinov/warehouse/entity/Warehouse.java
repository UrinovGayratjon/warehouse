package uz.urinov.warehouse.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import uz.urinov.warehouse.entity.template.AbsEntity;

import javax.persistence.Entity;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Warehouse extends AbsEntity {
}
