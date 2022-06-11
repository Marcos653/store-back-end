package com.store.view.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CustomerRequest {
    @NotNull @NotEmpty
    private String name;
    @NotNull @NotEmpty
    private String CPF;

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
