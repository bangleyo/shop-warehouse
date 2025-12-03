package com.example.shop_warehouse.repository;

import com.example.shop_warehouse.entity.ItemVariant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemVariantRepository extends JpaRepository<ItemVariant,Long> {

    Page<ItemVariant> findByItemId(Long itemId, Pageable pageable);

}
