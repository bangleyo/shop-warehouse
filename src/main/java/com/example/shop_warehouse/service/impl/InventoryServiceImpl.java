package com.example.shop_warehouse.service.impl;

import com.example.shop_warehouse.dto.request.SellRequest;
import com.example.shop_warehouse.entity.Item;
import com.example.shop_warehouse.entity.ItemVariant;
import com.example.shop_warehouse.exception.InsufficientStockException;
import com.example.shop_warehouse.exception.NotFoundException;
import com.example.shop_warehouse.repository.ItemRepository;
import com.example.shop_warehouse.repository.ItemVariantRepository;
import com.example.shop_warehouse.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final ItemRepository itemRepository;
    private final ItemVariantRepository variantRepository;

    @Override
    public void sell(SellRequest request) {
        if (Objects.nonNull(request.getVariantId()) && request.getVariantId() != 0) {
            sellVariant(request.getVariantId(), request.getQuantity());
        } else if (request.getItemId() != null && request.getItemId() != 0) {
            sellItem(request.getItemId(), request.getQuantity());
        } else {
            throw new IllegalArgumentException("Either itemId or variantId must be provided");
        }
    }

    private void sellItem(Long itemId, int quantity) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new NotFoundException("Item not found with id " + itemId));

        if (Boolean.TRUE.equals(item.getHasVariants())) {
            throw new IllegalArgumentException("Item with variants must be sold by variantId");
        }

        int currentStock = item.getStockQuantity() != null ? item.getStockQuantity() : 0;
        if (currentStock < quantity) {
            throw new InsufficientStockException("Not enough stock for item " + itemId);
        }

        item.setStockQuantity(currentStock - quantity);
        itemRepository.save(item);
    }

    private void sellVariant(Long variantId, int quantity) {
        ItemVariant variant = variantRepository.findById(variantId)
                .orElseThrow(() -> new NotFoundException("Variant not found with id " + variantId));

        int currentStock = variant.getStockQuantity();
        if (currentStock < quantity) {
            throw new InsufficientStockException("Not enough stock for variant " + variantId);
        }

        variant.setStockQuantity(currentStock - quantity);
        variantRepository.save(variant);
    }
}
