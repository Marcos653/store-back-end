package com.store.view.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class SellerRequest {
    @NotNull @NotEmpty
    private String name;
    @NotNull @NotEmpty
    private String CNPJ;


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCNPJ() {
        return CNPJ;
    }
    public void setCNPJ(String cNPJ) {
        CNPJ = cNPJ;
    }

}
