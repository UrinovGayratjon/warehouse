package uz.urinov.warehouse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.urinov.warehouse.entity.template.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Product extends AbsEntity {

    private String code;

    @ManyToOne(optional = false)
    private Category category;

    @ManyToOne(optional = false)
    private Measurement measurement;

    @OneToOne
    private Attachment attachment;
}
