package com.store.service;

import java.util.List;
import java.util.Optional;

import com.store.shared.CustomerDTO;

public interface CustomerService {
    List<CustomerDTO> getAllCustomer();
    CustomerDTO register(CustomerDTO customer);
    Optional<CustomerDTO> getById(Long id);
    void deleteCustomer(Long id);
    CustomerDTO updateCustomer(Long id, CustomerDTO newCustomer);
    CustomerDTO partialUpdateCustomer(Long id, String name, String cpf);
}