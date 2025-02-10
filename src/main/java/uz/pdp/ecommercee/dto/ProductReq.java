package uz.pdp.ecommercee.dto;

import java.util.UUID;

public record ProductReq(
       String name,
       Integer price,
       UUID categoryId
) {
}
