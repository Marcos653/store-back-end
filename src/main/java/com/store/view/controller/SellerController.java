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

import com.store.service.SellerService;
import com.store.shared.SellerDTO;
import com.store.view.model.SellerRequest;
import com.store.view.model.SellerResponse;
import com.store.view.model.SellerUpdate;

@RestController
@RequestMapping("api/seller")
public class SellerController {
    
    @Autowired
    private SellerService service;

    @GetMapping
    public ResponseEntity<List<SellerResponse>> getSellers(){
        List<SellerDTO> sellerDTOs = service.getAllSeller();

        if(sellerDTOs.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        ModelMapper mapper = new ModelMapper();
        List<SellerResponse> sellerResponses = sellerDTOs.stream()
            .map(s -> mapper.map(s, SellerResponse.class))
            .collect(Collectors.toList());

        return new ResponseEntity<>(sellerResponses, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<SellerResponse> getById(@PathVariable Long id){

        Optional<SellerDTO> seller = service.getById(id);

        if(seller.isPresent()){
            return new ResponseEntity<>(new ModelMapper().map(seller.get(), SellerResponse.class),HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<SellerRequest> createSeller(@RequestBody @Valid SellerRequest seller){
        ModelMapper mapper = new ModelMapper();
        SellerDTO sellerDTO = mapper
            .map(seller, SellerDTO.class);
        sellerDTO = service.register(sellerDTO);
        return new ResponseEntity<>(mapper.map(sellerDTO, SellerRequest.class), HttpStatus.CREATED);
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<SellerUpdate> updateSeller(@RequestBody @Valid SellerUpdate newSeller, @PathVariable Long id){
        ModelMapper mapper = new ModelMapper();
        SellerDTO sellerDTO = mapper
            .map(newSeller, SellerDTO.class);

        if(newSeller.getName() != null){
            sellerDTO = service.partialUpdateSeller(id, newSeller.getName(), newSeller.getCNPJ()); 
        }

        if(newSeller.getCNPJ() != null){
            sellerDTO = service.partialUpdateSeller(id, newSeller.getName(), newSeller.getCNPJ());
            
        }
        
        return new ResponseEntity<>(mapper.map(sellerDTO, SellerUpdate.class), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteSeller(@PathVariable Long id){
        service.deleteSeller(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
