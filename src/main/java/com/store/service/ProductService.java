package com.store.service;

import java.util.List;
import java.util.Optional;

import com.store.shared.ProductDTO;

public interface ProductService {
    List<ProductDTO> getAllProduct();
    ProductDTO register(ProductDTO product);
    Optional<ProductDTO> getById(Long id);
    void deleteProduct(Long id);
    ProductDTO updateProduct(Long id, ProductDTO newProduct);
    ProductDTO partialUpdateProduct(Long id, String name, Integer amount, double price);
}
