package com.store.view.model;

import java.util.ArrayList;
import java.util.List;

import com.store.model.Product;
import com.store.shared.CustomerDTO;

public class CustomerResponse {
    
    private Long id;
    private String name;
    private String CPF;
    private List<Product> products = new ArrayList<>();

    
    public CustomerResponse(CustomerDTO customerDTO) {
        this.id = customerDTO.getId();
        this.name = customerDTO.getName();
        this.CPF = customerDTO.getCPF();
        this.products = customerDTO.getProducts();
    }

    public CustomerResponse() {
    }


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCPF() {
        return CPF;
    }
    public void setCPF(String cPF) {
        CPF = cPF;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    
}
