package com.castillo.pavel.store.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CompanyResponse {

    private Long companyId;
    private String name;
    private Integer status;

}
