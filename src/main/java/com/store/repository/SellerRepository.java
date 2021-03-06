package com.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.store.model.Seller;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Long> {
    
}
