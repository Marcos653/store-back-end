package com.store.shared;

import java.util.ArrayList;
import java.util.List;

import com.store.model.Product;

public class CustomerDTO {

    private Long id;
    private String name;
    private String CPF;
    private List<Product> products = new ArrayList<>();

    
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


    @Override
    public String toString() {
        return "CustomerDTO [CPF=" + CPF + ", id=" + id + ", name=" + name + "]";
    }

    
}
