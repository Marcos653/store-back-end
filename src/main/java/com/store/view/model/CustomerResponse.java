package com.store.view.model;

import com.store.shared.CustomerDTO;

public class CustomerResponse {
    
    private Long id;
    private String name;
    private String CPF;

    
    public CustomerResponse(CustomerDTO customerDTO) {
        this.id = customerDTO.getId();
        this.name = customerDTO.getName();
        this.CPF = customerDTO.getCPF();
    }

    public CustomerResponse() {
    }


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCPF() {
        return CPF;
    }
    public void setCPF(String cPF) {
        CPF = cPF;
    }

    
}
