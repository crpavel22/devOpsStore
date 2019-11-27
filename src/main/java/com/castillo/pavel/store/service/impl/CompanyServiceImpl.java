package com.castillo.pavel.store.service.impl;

import com.castillo.pavel.store.entity.CompanyEntity;
import com.castillo.pavel.store.exception.custom.CompanyNotFoundException;
import com.castillo.pavel.store.model.request.CompanyRequest;
import com.castillo.pavel.store.model.response.CompanyResponse;
import com.castillo.pavel.store.repository.CompanyRepository;
import com.castillo.pavel.store.service.CompanyService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService {

    private CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<CompanyResponse> getAll() {

        return companyRepository.findAll().stream().map(
                ce -> new CompanyResponse(ce.getCompanyId(), ce.getName(), ce.getStatus())
        ).collect(Collectors.toList());


    }

    @Override
    public CompanyResponse findOne(Long companyId) {
        return companyRepository.findById(companyId).map(
                c -> new CompanyResponse(c.getCompanyId(), c.getName(), c.getStatus()))
                .orElseThrow(() -> new CompanyNotFoundException(String.valueOf(companyId)));
    }

    @Override
    public CompanyResponse addOne(CompanyRequest companyRequest) {
        CompanyEntity ce = new CompanyEntity();
        ce.setName(companyRequest.getName());
        ce.setCreateDate(LocalDateTime.now());
        ce.setStatus(companyRequest.getStatus());
        ce = companyRepository.save(ce);

        return new CompanyResponse(ce.getCompanyId(), ce.getName(), ce.getStatus());
    }

    @Override
    public CompanyResponse update(CompanyRequest companyRequest, Long companyId) {

        return companyRepository.findById(companyId).map(
                c -> {
                    c.setName(companyRequest.getName());
                    c.setStatus(companyRequest.getStatus());
                    c = companyRepository.save(c);
                    return new CompanyResponse(c.getCompanyId(), c.getName(), c.getStatus());
                }).orElseThrow(() -> new CompanyNotFoundException(String.valueOf(companyId)));

    }

    @Override
    public void delete(Long companyId) {
        companyRepository.deleteById(companyId);
    }
}
