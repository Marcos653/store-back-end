package com.store.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "sellers")
public class Seller {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "CNPJ")
    private String CNPJ;

    
    @OneToMany(mappedBy = "seller")
    private List<Product> products = new ArrayList<>();


    public Seller() {
    }


    public Seller(String name, String cNPJ) {
        this.name = name;
        CNPJ = cNPJ;
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


    public String getCNPJ() {
        return CNPJ;
    }


    public void setCNPJ(String cNPJ) {
        CNPJ = cNPJ;
    }


    public List<Product> getProducts() {
        return products;
    }


    public void setProducts(List<Product> products) {
        this.products = products;
    }

    


}
