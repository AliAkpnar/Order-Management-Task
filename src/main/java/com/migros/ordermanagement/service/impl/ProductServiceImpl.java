package com.migros.ordermanagement.service.impl;

import com.migros.ordermanagement.advice.exception.*;
import com.migros.ordermanagement.converter.ProductEntityConverter;
import com.migros.ordermanagement.model.dto.ProductDto;
import com.migros.ordermanagement.model.request.ProductRequest;
import com.migros.ordermanagement.persistence.entity.OrderEntity;
import com.migros.ordermanagement.persistence.entity.ProductEntity;
import com.migros.ordermanagement.persistence.repository.OrderRepository;
import com.migros.ordermanagement.persistence.repository.ProductRepository;
import com.migros.ordermanagement.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<ProductDto> getAllProducts() {
        List<ProductEntity> productEntities = productRepository.findAll();
        return productEntities.stream()
                .map(ProductEntityConverter::toMapProductDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto saveProduct(long orderId, ProductRequest productRequest) {
        ProductEntity productEntity = mapToEntity(productRequest);
        OrderEntity orderEntity = orderRepository
                .findById(orderId)
                .orElseThrow(OrderNotFoundException::new);
        productEntity.setOrder(orderEntity);
        productRepository.save(productEntity);
        return ProductEntityConverter.toMapProductDto(productEntity);
    }

    @Override
    public List<ProductDto> getProductsByOrderId(long orderId) {
        List<ProductEntity> productEntities = productRepository.findByOrderId(orderId);
        return productEntities.stream()
                .map(ProductEntityConverter::toMapProductDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto updateProduct(Long orderId, Long productId, ProductRequest productRequest) {
        ProductEntity productEntity = getProduct(orderId, productId);
        productEntity.setProductName(productRequest.getProductName());
        productEntity.setProductDescription(productRequest.getProductDescription());
        productEntity.setProductQuantity(productRequest.getProductQuantity());
        ProductEntity updatedProduct = productRepository.save(productEntity);
        return ProductEntityConverter.toMapProductDto(updatedProduct);
    }

    @Override
    public void deleteProduct(Long orderId, Long productId) {
        ProductEntity product = getProduct(orderId, productId);
        productRepository.delete(product);
    }

    private ProductEntity getProduct(long orderId, long productId) {
        OrderEntity orderEntity = orderRepository.findById(orderId).orElseThrow(OrderNotFoundException::new);
        Optional<ProductEntity> productEntity = Optional.ofNullable(productRepository.findById(productId)
                .orElseThrow(ProductNotFoundException::new));
        productEntity
                .map(ProductEntity::getOrder)
                .map(OrderEntity::getId)
                .filter(e -> e.equals(orderEntity.getId()))
                .orElseThrow(OrderProductNotFoundException::new);
        return productEntity.get();
    }

    private ProductEntity mapToEntity(ProductRequest productRequest) {
        return modelMapper.map(productRequest, ProductEntity.class);
    }
}
