package uz.pdp.ecommercee.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import uz.pdp.ecommercee.entity.abs.BaseEntity;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SuperBuilder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;
    private Integer price;

    @ManyToOne(fetch = FetchType.LAZY)
    private Attachment attachment;

    @ManyToOne
    private Category category;

    @Transient
    private boolean hasInBasket;
}
