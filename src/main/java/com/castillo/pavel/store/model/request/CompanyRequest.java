package com.castillo.pavel.store.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CompanyRequest {
    private String name;
    private Integer status;
}
