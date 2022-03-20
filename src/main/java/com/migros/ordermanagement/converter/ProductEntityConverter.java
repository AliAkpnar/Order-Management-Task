package com.migros.ordermanagement.converter;

import com.migros.ordermanagement.model.dto.ProductDto;
import com.migros.ordermanagement.persistence.entity.ProductEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductEntityConverter {
    public static ProductDto toMapProductDto(final ProductEntity productEntity){
        return ProductDto.builder()
                .productId(productEntity.getId())
                .productName(productEntity.getProductName())
                .productQuantity(productEntity.getProductQuantity())
                .productDescription(productEntity.getProductDescription())
                .build();
    }
}
