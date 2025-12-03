package com.example.shop_warehouse.controller;

import com.example.shop_warehouse.dto.request.VariantRequest;
import com.example.shop_warehouse.dto.response.VariantResponse;
import com.example.shop_warehouse.service.VariantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class VariantController {
    private final VariantService variantService;

    @PostMapping("/api/items/{itemId}/variants")
    public ResponseEntity<VariantResponse> create(@PathVariable Long itemId,
                                                  @Valid @RequestBody VariantRequest request) {
        VariantResponse response = variantService.create(itemId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/api/items/{itemId}/variants")
    public Page<VariantResponse> findByItem(@PathVariable Long itemId, Pageable pageable) {
        return variantService.findByItemId(itemId, pageable);
    }

    @GetMapping("/api/variants/{id}")
    public VariantResponse findById(@PathVariable Long id) {
        return variantService.findById(id);
    }

    @PutMapping("/api/variants/{id}")
    public VariantResponse update(@PathVariable Long id,
                                  @Valid @RequestBody VariantRequest request) {
        return variantService.update(id, request);
    }

    @DeleteMapping("/api/variants/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        variantService.delete(id);
    }
}
