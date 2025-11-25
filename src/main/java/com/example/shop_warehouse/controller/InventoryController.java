package com.example.shop_warehouse.controller;

import com.example.shop_warehouse.dto.request.SellRequest;
import com.example.shop_warehouse.service.InventoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryService inventoryService;

    @PostMapping("/sell")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void sell(@Valid @RequestBody SellRequest request) {
        inventoryService.sell(request);
    }
}
