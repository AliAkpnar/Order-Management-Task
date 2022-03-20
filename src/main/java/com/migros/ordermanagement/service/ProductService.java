package com.migros.ordermanagement.service;

import com.migros.ordermanagement.model.dto.ProductDto;
import com.migros.ordermanagement.model.request.ProductRequest;

import java.util.List;

public interface ProductService {
    List<ProductDto> getAllProducts();
    ProductDto saveProduct(long orderId, ProductRequest productRequest);
    List<ProductDto> getProductsByOrderId(long orderId);
    ProductDto updateProduct(Long orderId, Long productId, ProductRequest productRequest);
    void deleteProduct(Long orderId, Long productId);
}
