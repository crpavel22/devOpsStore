package com.castillo.pavel.store.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import org.springframework.http.HttpStatus;

import java.util.List;

@JsonRootName("CompanyList")
public class CompanyListResponse extends Generic {

    @JsonProperty("companies")
    private List<CompanyResponse> companies;

    public CompanyListResponse(HttpStatus status, List<CompanyResponse> companies) {
        super(status);
        this.companies = companies;
    }
}
