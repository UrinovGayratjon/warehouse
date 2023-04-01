package uz.urinov.warehouse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.urinov.warehouse.entity.template.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Category extends AbsEntity {
    @ManyToOne
    private Category category;
}
