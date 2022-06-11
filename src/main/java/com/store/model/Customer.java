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
@Table(name = "customers")
public class Customer {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "CPF")
    private String CPF;

    @OneToMany(mappedBy = "customer")
    private List<Product> products = new ArrayList<>();


    public Customer(Long id, String name, String cPF, List<Product> products) {
        this.id = id;
        this.name = name;
        CPF = cPF;
        this.products = products;
    }

    public Customer() {
    }

    public Customer(Long id, String name, String cPF) {
        this.id = id;
        this.name = name;
        CPF = cPF;
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


    
    
}
