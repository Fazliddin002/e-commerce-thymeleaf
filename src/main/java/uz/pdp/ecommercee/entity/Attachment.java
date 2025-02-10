package uz.pdp.ecommercee.entity;

import jakarta.persistence.Entity;
import lombok.*;
import uz.pdp.ecommercee.entity.abs.BaseEntity;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Attachment extends BaseEntity {
    private byte[] content;
}
