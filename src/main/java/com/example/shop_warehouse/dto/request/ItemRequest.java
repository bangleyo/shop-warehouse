package com.example.shop_warehouse.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ItemRequest {
    @NotBlank
    private String name;

    private String description;

    private BigDecimal basePrice;

    @PositiveOrZero
    private Integer stockQuantity;

    @NotNull
    private Boolean hasVariants;
}
