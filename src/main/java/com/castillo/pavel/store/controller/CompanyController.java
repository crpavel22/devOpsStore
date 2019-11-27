package com.castillo.pavel.store.controller;

import com.castillo.pavel.store.model.request.CompanyRequest;
import com.castillo.pavel.store.model.response.CompanyListResponse;
import com.castillo.pavel.store.model.response.CompanyResponse;
import com.castillo.pavel.store.model.response.SingleCompanyResponse;
import com.castillo.pavel.store.service.CompanyService;
import com.castillo.pavel.store.utils.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class CompanyController {

    private CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping(value = Constants.COMPANIES_ROOT_PATH)
    public CompanyListResponse getAll() {

        return new CompanyListResponse(HttpStatus.OK, companyService.getAll());
    }

    @GetMapping(value = Constants.COMPANY_PATH)
    public SingleCompanyResponse findOne(@PathVariable("id") Long id) {

        return new SingleCompanyResponse(HttpStatus.OK, companyService.findOne(id));
    }

    @PostMapping(value = Constants.COMPANIES_ROOT_PATH)
    public ResponseEntity<?> create(@RequestBody CompanyRequest companyRequest) throws URISyntaxException {

        CompanyResponse companyResponse = companyService.addOne(companyRequest);
        return ResponseEntity.created(new URI(ServletUriComponentsBuilder.fromCurrentRequest().path("").toUriString() + "/" + companyResponse.getCompanyId())
        ).body(companyResponse);
    }

    @PutMapping(value = Constants.COMPANY_PATH)
    public ResponseEntity<?> update(@RequestBody CompanyRequest companyRequest, @PathVariable("id") Long id) throws URISyntaxException {
        return ResponseEntity.ok(companyService.update(companyRequest, id));
    }

    @DeleteMapping(value = Constants.COMPANY_PATH)
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        companyService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
