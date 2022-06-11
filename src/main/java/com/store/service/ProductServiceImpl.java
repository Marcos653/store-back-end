package com.store.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.model.Product;
import com.store.repository.ProductRepository;
import com.store.shared.ProductDTO;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repo;

    @Override
    public List<ProductDTO> getAllProduct() {
        List<Product> products = repo.findAll();
        return products.stream()
            .map(p -> new ModelMapper().map(p, ProductDTO.class))
            .collect(Collectors.toList());

    }

    @Override
    public ProductDTO register(ProductDTO Product) {
        return salveProduct(Product);
    }

    @Override
    public Optional<ProductDTO> getById(Long id) {
        Optional<Product> product = repo.findById(id);

        if(product.isPresent()){
            return Optional.of(new ModelMapper()
                .map(product.get(), ProductDTO.class));
        }

        return Optional.empty();
    }

    @Override
    public void deleteProduct(Long id) {
        repo.deleteById(id);
        
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO newProduct) {
        newProduct.setId(id);
        return salveProduct(newProduct);
    }

    private ProductDTO salveProduct(ProductDTO product) {
        ModelMapper mapper = new ModelMapper();
        Product product_entity = mapper.map(product, Product.class);
        product_entity = repo.save(product_entity);
        return mapper.map(product_entity, ProductDTO.class);
    }

    @Override
    public ProductDTO partialUpdateProduct(Long id, String name, Integer amount, double price) {
        ModelMapper mapper = new ModelMapper();
        Optional<Product> product = repo.findById(id);

        if(product.isPresent()){

            if(name != null){
                product.get().setName(name);
                repo.save(product.get());
            }

            if(amount != null){
                product.get().setAmount(amount);;
                repo.save(product.get());
            }

            if(price != 0){
                product.get().setPrice(price);
                repo.save(product.get());
            }

            return mapper.map(product.get(), ProductDTO.class);

        }

        return null;
        
    }
    
}
