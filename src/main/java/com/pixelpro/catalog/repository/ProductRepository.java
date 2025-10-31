package com.pixelpro.catalog.repository;

import com.pixelpro.catalog.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    List<ProductEntity> findByNameContainingIgnoreCase(String keyword);

    Page<ProductEntity> findByNameContainingIgnoreCase(String keyword, Pageable pageable);

    boolean existsByNameIgnoreCase(String name);

    @Query("SELECT p FROM ProductEntity p " +
            "LEFT JOIN FETCH p.categories " +
            "LEFT JOIN FETCH p.images " +
            "WHERE p.id = :productId")
    Optional<ProductEntity> findDetailedById(Long productId);
}
