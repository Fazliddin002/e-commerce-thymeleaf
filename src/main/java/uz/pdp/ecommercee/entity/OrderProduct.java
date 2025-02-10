package uz.pdp.ecommercee.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.ecommercee.entity.abs.BaseEntity;
@EqualsAndHashCode(callSuper = true)

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderProduct extends BaseEntity {


    @ManyToOne
    private Order order;

    @ManyToOne
    Product product;

    private Integer amount;

}
