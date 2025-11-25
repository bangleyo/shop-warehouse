package com.example.shop_warehouse.dto.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class VariantResponse {
    private Long id;
    private String sku;
    private String size;
    private String color;
    private BigDecimal price;
    private Integer stockQuantity;
}
