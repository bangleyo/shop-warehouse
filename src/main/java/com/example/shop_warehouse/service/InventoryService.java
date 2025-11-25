package com.example.shop_warehouse.service;

import com.example.shop_warehouse.dto.request.SellRequest;

public interface InventoryService {
    void sell(SellRequest request);
}
