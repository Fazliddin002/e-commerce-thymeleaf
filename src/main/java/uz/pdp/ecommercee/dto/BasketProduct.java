package uz.pdp.ecommercee.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.ecommercee.entity.Product;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class BasketProduct {
    private Product product;

    private Integer amount;

}
