package com.store.service;

import java.util.List;
import java.util.Optional;

import com.store.model.Customer;
import com.store.model.Seller;
import com.store.shared.SaleDTO;

public interface SaleService {
    List<SaleDTO> getAllSale();
    SaleDTO register(SaleDTO sale);
    Optional<SaleDTO> getById(Long id);
    void deleteSale(Long id);
    SaleDTO updateSale(Long id, SaleDTO newSale);
    SaleDTO partialUpdateSale(Long id, Integer sale_amount, Customer customer, Seller seller);
}
