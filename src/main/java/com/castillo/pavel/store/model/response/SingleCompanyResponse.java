package com.castillo.pavel.store.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatus;

public class SingleCompanyResponse extends Generic {
    @JsonProperty("company")
    private CompanyResponse companyResponse;

    public SingleCompanyResponse(HttpStatus status, CompanyResponse companyResponse) {
        super(status);
        this.companyResponse = companyResponse;
    }
}
