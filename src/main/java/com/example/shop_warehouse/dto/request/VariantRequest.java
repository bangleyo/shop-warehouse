package com.example.shop_warehouse.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class VariantRequest {
    @NotBlank
    private String sku;

    private String size;

    private String color;

    @NotNull
    private BigDecimal price;

    @PositiveOrZero
    @NotNull
    private Integer stockQuantity;
}
