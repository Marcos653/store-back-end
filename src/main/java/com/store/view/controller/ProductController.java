package com.store.view.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.store.service.ProductService;
import com.store.shared.ProductDTO;
import com.store.view.model.ProductRequest;
import com.store.view.model.ProductResponse;
import com.store.view.model.ProductUpdate;

@RestController
@RequestMapping("api/product")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getProducts(){
        List<ProductDTO> productDTOs = service.getAllProduct();

        if(productDTOs.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        ModelMapper mapper = new ModelMapper();
        List<ProductResponse> productResponses = productDTOs.stream()
            .map(p -> mapper.map(p, ProductResponse.class))
            .collect(Collectors.toList());

        return new ResponseEntity<>(productResponses, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductResponse> getById(@PathVariable Long id){

        Optional<ProductDTO> product = service.getById(id);

        if(product.isPresent()){
            return new ResponseEntity<>(new ModelMapper().map(product.get(), ProductResponse.class),HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<ProductRequest> createProduct(@RequestBody @Valid ProductRequest product){
        ModelMapper mapper = new ModelMapper();
        ProductDTO productDTO = mapper
            .map(product, ProductDTO.class);
        productDTO = service.register(productDTO);
        return new ResponseEntity<>(mapper.map(productDTO, ProductRequest.class), HttpStatus.CREATED);
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<ProductUpdate> updateProduct(@RequestBody @Valid ProductUpdate newProduct, @PathVariable Long id){
        ModelMapper mapper = new ModelMapper();
        ProductDTO productDTO = mapper
            .map(newProduct, ProductDTO.class);

        if(newProduct.getName() != null){
            productDTO = service.partialUpdateProduct(id, newProduct.getName(), newProduct.getAmount(), newProduct.getPrice()); 
        }

        if(newProduct.getAmount() != null){
            productDTO = service.partialUpdateProduct(id, newProduct.getName(), newProduct.getAmount(), newProduct.getPrice());
            
        }

        if(newProduct.getPrice() != 0){
            productDTO = service.partialUpdateProduct(id, newProduct.getName(), newProduct.getAmount(), newProduct.getPrice());
            
        }
        
        return new ResponseEntity<>(mapper.map(productDTO, ProductUpdate.class), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
        service.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
}
