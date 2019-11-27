package com.castillo.pavel.store.service;

import com.castillo.pavel.store.model.request.CompanyRequest;
import com.castillo.pavel.store.model.response.CompanyResponse;

import java.util.List;

public interface CompanyService {

    List<CompanyResponse> getAll();

    CompanyResponse findOne(Long companyId);

    CompanyResponse addOne(CompanyRequest companyRequest);

    CompanyResponse update(CompanyRequest companyRequest, Long companyId);

    void delete(Long companyId);

}
