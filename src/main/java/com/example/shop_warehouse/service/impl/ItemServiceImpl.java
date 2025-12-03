package com.example.shop_warehouse.service.impl;

import com.example.shop_warehouse.dto.request.ItemRequest;
import com.example.shop_warehouse.dto.response.ItemResponse;
import com.example.shop_warehouse.dto.response.VariantResponse;
import com.example.shop_warehouse.entity.Item;
import com.example.shop_warehouse.entity.ItemVariant;
import com.example.shop_warehouse.exception.NotFoundException;
import com.example.shop_warehouse.repository.ItemRepository;
import com.example.shop_warehouse.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;

    @Transactional(readOnly = false)
    public ItemResponse create(ItemRequest request) {
        Item item = new Item();
        mapRequestToEntity(request, item);
        item = itemRepository.save(item);
        return toResponse(item);
    }

    public Page<ItemResponse> findAll(Pageable pageable) {
        return itemRepository.findAll(pageable).map(this::toResponse);
    }

    public ItemResponse findById(Long id) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Item not found with id " + id));
        return toResponse(item);
    }

    @Transactional(readOnly = false)
    public ItemResponse update(Long id, ItemRequest request) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Item not found with id " + id));
        mapRequestToEntity(request, item);
        item = itemRepository.save(item);
        return toResponse(item);
    }

    @Transactional(readOnly = false)
    public void delete(Long id) {
        if (!itemRepository.existsById(id)) {
            throw new NotFoundException("Item not found with id " + id);
        }
        itemRepository.deleteById(id);
    }

    private void mapRequestToEntity(ItemRequest request, Item item) {
        item.setName(request.getName());
        item.setDescription(request.getDescription());
        item.setHasVariants(request.getHasVariants());
        item.setBasePrice(request.getBasePrice());
        item.setStockQuantity(request.getStockQuantity() != null ? request.getStockQuantity() : 0);
    }

    private ItemResponse toResponse(Item item) {
        ItemResponse response = new ItemResponse();
        response.setId(item.getId());
        response.setName(item.getName());
        response.setDescription(item.getDescription());
        response.setBasePrice(item.getBasePrice());
        response.setStockQuantity(item.getStockQuantity());
        response.setHasVariants(item.getHasVariants());

        List<VariantResponse> variants = item.getVariants().stream()
                .map(this::toVariantResponse)
                .toList();
        response.setVariants(variants);
        return response;
    }

    private VariantResponse toVariantResponse(ItemVariant v) {
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