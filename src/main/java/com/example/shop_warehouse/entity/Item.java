package com.example.shop_warehouse.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "items")
@Data
@NoArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String name;

    @Column(name = "base_price", precision = 19, scale = 2)
    private BigDecimal basePrice;

    @PositiveOrZero
    @Column(name = "stock_quantity")
    private Integer stockQuantity = 0;

    @NotNull
    @Column(name = "has_variants", nullable = false)
    private Boolean hasVariants = Boolean.FALSE;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ItemVariant> variants = new ArrayList<>();

    private String description;
}
