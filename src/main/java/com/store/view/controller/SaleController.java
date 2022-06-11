package com.store.view.controller;

import java.util.Collection;
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

import com.store.service.SaleService;
import com.store.shared.SaleDTO;
import com.store.view.model.SaleRequest;
import com.store.view.model.SaleResponse;
import com.store.view.model.SaleUpdate;


@RestController
@RequestMapping("api/sale")
public class SaleController {

    @Autowired
    private SaleService service;

    @GetMapping
    public ResponseEntity<List<SaleResponse>> getSales(){
        List<SaleDTO> saleDTOs = service.getAllSale();

        if(saleDTOs.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        ModelMapper mapper = new ModelMapper();
        List<SaleResponse> saleResponses = saleDTOs.stream()
            .map(s -> mapper.map(s, SaleResponse.class))
            .collect(Collectors.toList());

        return new ResponseEntity<>(saleResponses, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<SaleResponse> getById(@PathVariable Long id){

        Optional<SaleDTO> sale = service.getById(id);

        if(sale.isPresent()){
            return new ResponseEntity<>(new ModelMapper().map(sale.get(), SaleResponse.class),HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<SaleRequest> createSale(@RequestBody @Valid SaleRequest sale){
        System.out.println(sale.getCustomer().getId());
        ModelMapper mapper = new ModelMapper();
        SaleDTO saleDTO = mapper
            .map(sale, SaleDTO.class);
        saleDTO = service.register(saleDTO);
        return new ResponseEntity<>(mapper.map(saleDTO, SaleRequest.class), HttpStatus.CREATED);
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<SaleUpdate> updateSale(@RequestBody @Valid SaleUpdate newSale, @PathVariable Long id){
        ModelMapper mapper = new ModelMapper();
        SaleDTO saleDTO = mapper
            .map(newSale, SaleDTO.class);

        if(newSale.getSale_amount() != null){
            saleDTO = service.partialUpdateSale(id, newSale.getSale_amount(), newSale.getCustomer(), newSale.getSeller()); 
        }

        if(newSale.getCustomer() != null){
            saleDTO = service.partialUpdateSale(id, newSale.getSale_amount(), newSale.getCustomer(), newSale.getSeller());
            
        }

        if(newSale.getSeller() != null){
            saleDTO = service.partialUpdateSale(id, newSale.getSale_amount(), newSale.getCustomer(), newSale.getSeller());
            
        }
        
        return new ResponseEntity<>(mapper.map(saleDTO, SaleUpdate.class), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteSale(@PathVariable Long id){
        service.deleteSale(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
}
