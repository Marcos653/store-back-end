package com.store.view.model;

import javax.validation.constraints.NotNull;

import com.store.model.Customer;
import com.store.model.Product;
import com.store.model.Seller;

public class SaleRequest {
    
    @NotNull
    private Integer sale_amount;
    @NotNull
    private Customer customer;
    @NotNull
    private Seller seller;
    @NotNull
    private Product product;

    
    
    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
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
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    public Seller getSeller() {
        return seller;
    }
    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    

}
