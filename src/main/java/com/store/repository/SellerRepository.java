package com.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.store.model.Seller;

public interface SellerRepository extends JpaRepository<Seller, Long> {
    
}
