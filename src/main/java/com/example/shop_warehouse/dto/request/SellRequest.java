package com.example.shop_warehouse.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class SellRequest {
    private Long itemId;
    private Long variantId;
    @NotNull
    @Positive
    private Integer quantity;
}
