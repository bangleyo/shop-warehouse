package com.example.shop_warehouse.service;

import com.example.shop_warehouse.dto.request.ItemRequest;
import com.example.shop_warehouse.dto.response.ItemResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ItemService {
    ItemResponse create(ItemRequest request);
    ItemResponse update(Long id, ItemRequest request);
    Page<ItemResponse> findAll(Pageable pageable);
    ItemResponse findById(Long id);
    void delete(Long id);
}
