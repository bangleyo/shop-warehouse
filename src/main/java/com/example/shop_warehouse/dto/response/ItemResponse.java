package com.example.shop_warehouse.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ItemResponse {
    private Long id;
    private String name;
    private String description;
    private BigDecimal basePrice;
    private Integer stockQuantity;
    private Boolean hasVariants;
    private List<VariantResponse> variants;
}
