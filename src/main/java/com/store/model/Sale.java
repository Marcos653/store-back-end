package com.store.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
@Entity
@Table(name = "sales")
public class Sale {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "sale_amount")
    private Integer sale_amount;
    @Column(name = "created_at")
    private LocalDateTime created_at = LocalDateTime.now();

    @OneToOne
    private Customer customer;

    @OneToOne
    private Seller seller;

    @OneToOne
    private Product product;
    

    public Sale() {
    }
    

    public Sale(Customer customer, Seller seller) {
        this.customer = customer;
        this.seller = seller;
    }

    


    public Sale(Customer customer, Seller seller, Product product) {
        this.customer = customer;
        this.seller = seller;
        this.product = product;
    }


    public Product getProduct() {
        return product;
    }


    public void setProduct(Product product) {
        this.product = product;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSale_amount() {
        return sale_amount;
    }

    public void setSale_amount(Integer sale_amount) {
        this.sale_amount = sale_amount;
    }

    public Customer getCustomer() {
        return customer;
    }

    @JsonProperty("customerId")
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Seller getSeller() {
        return seller;
    }

    @JsonProperty("sellerId")
    public void setSeller(Seller seller) {
        this.seller = seller;
    }
    
    

}
