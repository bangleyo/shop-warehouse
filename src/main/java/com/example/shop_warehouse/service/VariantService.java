package com.example.shop_warehouse.service;

import com.example.shop_warehouse.dto.request.VariantRequest;
import com.example.shop_warehouse.dto.response.VariantResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface VariantService {
    VariantResponse create(Long itemId, VariantRequest request);
    Page<VariantResponse> findByItemId(Long itemId, Pageable pageable);
    VariantResponse findById(Long id);
    VariantResponse update(Long id, VariantRequest request);
    void delete(Long id);
}
