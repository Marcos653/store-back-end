package com.store.view.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.store.service.CustomerService;
import com.store.shared.CustomerDTO;
import com.store.view.model.CustomerRequest;
import com.store.view.model.CustomerResponse;
import com.store.view.model.CustomerUpdate;

@RestController
@RequestMapping("api/customer")
public class CustomerController {

    @Autowired
    private CustomerService service;
    

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> getCustomers(){
        List<CustomerDTO> customerDTOs = service.getAllCustomer();

        if(customerDTOs.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        ModelMapper mapper = new ModelMapper();
        List<CustomerResponse> customerResponses = customerDTOs.stream()
            .map(c -> mapper.map(c, CustomerResponse.class))
            .collect(Collectors.toList());

        return new ResponseEntity<>(customerResponses, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CustomerResponse> getById(@PathVariable Long id){

        Optional<CustomerDTO> customer = service.getById(id);

        if(customer.isPresent()){
            return new ResponseEntity<>(new ModelMapper().map(customer.get(), CustomerResponse.class),HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<CustomerRequest> createCustomer(@RequestBody @Valid CustomerRequest customer){
        ModelMapper mapper = new ModelMapper();
        CustomerDTO customerDTO = mapper
            .map(customer, CustomerDTO.class);
        customerDTO = service.register(customerDTO);
        return new ResponseEntity<>(mapper.map(customerDTO, CustomerRequest.class), HttpStatus.CREATED);
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<CustomerUpdate> updateCustomer(@RequestBody @Valid CustomerUpdate newCustomer, @PathVariable Long id){
        ModelMapper mapper = new ModelMapper();
        CustomerDTO customerDTO = mapper
            .map(newCustomer, CustomerDTO.class);

        if(newCustomer.getName() != null){
            customerDTO = service.partialUpdateCustomer(id, newCustomer.getName(), newCustomer.getCPF()); 
        }

        if(customerDTO.getCPF() != null){
            customerDTO = service.partialUpdateCustomer(id, newCustomer.getName(), newCustomer.getCPF());
            
        }
        
        return new ResponseEntity<>(mapper.map(customerDTO, CustomerUpdate.class), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id){
        service.deleteCustomer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    

}
