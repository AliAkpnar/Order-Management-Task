package com.migros.ordermanagement.controller;

import com.migros.ordermanagement.model.dto.OrderDto;
import com.migros.ordermanagement.model.dto.ProductDto;
import com.migros.ordermanagement.model.request.OrderRequest;
import com.migros.ordermanagement.model.request.ProductRequest;
import com.migros.ordermanagement.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<List<ProductDto>> getProductsByOrderId(@PathVariable(value = "orderId") long orderId){
        return ResponseEntity.ok(productService.getProductsByOrderId(orderId));
    }

    @PostMapping("/save/{orderId}")
    public ResponseEntity<ProductDto> saveProduct(@PathVariable(value = "orderId") long orderId, @Valid @RequestBody ProductRequest productRequest){
        return ResponseEntity.ok(productService.saveProduct(orderId, productRequest));
    }

    @PutMapping("/{orderId}/product/{productId}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable(value = "orderId") Long orderId,
                                                    @PathVariable(value = "productId")  Long productId,
                                                    @RequestBody ProductRequest productRequest){
        return ResponseEntity.ok(productService.updateProduct(orderId, productId, productRequest));
    }

    @DeleteMapping("/{orderId}/orders/{productId}")
    public ResponseEntity<String> removeProduct(@PathVariable(value = "orderId") Long orderId,
                                              @PathVariable(value = "productId") Long productId){
        productService.deleteProduct(orderId, productId);
        return ResponseEntity.ok("Product is removed successfully");
    }
}
