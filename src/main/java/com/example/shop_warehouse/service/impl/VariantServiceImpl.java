package com.example.shop_warehouse.service.impl;

import com.example.shop_warehouse.dto.request.VariantRequest;
import com.example.shop_warehouse.dto.response.VariantResponse;
import com.example.shop_warehouse.entity.Item;
import com.example.shop_warehouse.entity.ItemVariant;
import com.example.shop_warehouse.exception.NotFoundException;
import com.example.shop_warehouse.repository.ItemRepository;
import com.example.shop_warehouse.repository.ItemVariantRepository;
import com.example.shop_warehouse.service.VariantService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class VariantServiceImpl implements VariantService {
    private final ItemVariantRepository variantRepository;
    private final ItemRepository itemRepository;

    public VariantResponse create(Long itemId, VariantRequest request) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new NotFoundException("Item not found with id " + itemId));

        ItemVariant variant = new ItemVariant();
        mapRequestToEntity(request, variant);
        variant.setItem(item);

        variant = variantRepository.save(variant);
        return toResponse(variant);
    }

    public Page<VariantResponse> findByItemId(Long itemId, Pageable pageable) {
        if (!itemRepository.existsById(itemId)) {
            throw new NotFoundException("Item not found with id " + itemId);
        }
        return variantRepository.findByItemId(itemId, pageable).map(this::toResponse);
    }

    public VariantResponse findById(Long id) {
        ItemVariant variant = variantRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Variant not found with id " + id));
        return toResponse(variant);
    }

    public VariantResponse update(Long id, VariantRequest request) {
        ItemVariant variant = variantRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Variant not found with id " + id));
        mapRequestToEntity(request, variant);
        variant = variantRepository.save(variant);
        return toResponse(variant);
    }

    public void delete(Long id) {
        if (!variantRepository.existsById(id)) {
            throw new NotFoundException("Variant not found with id " + id);
        }
        variantRepository.deleteById(id);
    }

    private void mapRequestToEntity(VariantRequest request, ItemVariant variant) {
        variant.setSku(request.getSku());
        variant.setSize(request.getSize());
        variant.setColor(request.getColor());
        variant.setPrice(request.getPrice());
        variant.setStockQuantity(request.getStockQuantity());
    }

    private VariantResponse toResponse(ItemVariant v) {
        VariantResponse res = new VariantResponse();
        res.setId(v.getId());
        res.setSku(v.getSku());
        res.setSize(v.getSize());
        res.setColor(v.getColor());
        res.setPrice(v.getPrice());
        res.setStockQuantity(v.getStockQuantity());
        return res;
    }
}
