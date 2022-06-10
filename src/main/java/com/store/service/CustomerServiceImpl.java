package com.store.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.model.Customer;
import com.store.repository.CustomerRepository;
import com.store.shared.CustomerDTO;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository repo;

    @Override
    public List<CustomerDTO> getAllCustomer() {
        List<Customer> customers = repo.findAll();
        return customers.stream()
            .map(c -> new ModelMapper().map(c, CustomerDTO.class))
            .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO register(CustomerDTO customer) {
        return salveCustomer(customer);
    }

    @Override
    public Optional<CustomerDTO> getById(Long id) {
        Optional <Customer> customer = repo.findById(id);

        if(customer.isPresent()){
            return Optional.of(new ModelMapper()
            .map(customer, CustomerDTO.class));
        }

        return Optional.empty();

    }

    @Override
    public void deleteCustomer(Long id) {
        repo.deleteById(id);
    }

    @Override
    public CustomerDTO updateCustomer(Long id, CustomerDTO newCustomer) {
        newCustomer.setId(id);
        return salveCustomer(newCustomer);
    }


    private CustomerDTO salveCustomer(CustomerDTO customer){
        ModelMapper mapper = new ModelMapper();
        Customer customer_entity = mapper.map(customer, Customer.class);
        customer_entity = repo.save(customer_entity);
        return mapper.map(customer_entity, CustomerDTO.class);
    }
    
}
