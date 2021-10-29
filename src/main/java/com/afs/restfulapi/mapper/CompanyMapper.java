package com.afs.restfulapi.mapper;

import com.afs.restfulapi.DTO.CompanyRequest;
import com.afs.restfulapi.DTO.CompanyResponse;
import com.afs.restfulapi.Entity.Company;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapper {
    public Company toEntity(CompanyRequest companyRequest) {
        Company company = new Company();
        BeanUtils.copyProperties(companyRequest, company);
        return company;
    }

    public CompanyResponse toResponse(Company company) {
        CompanyResponse companyResponse = new CompanyResponse();
        BeanUtils.copyProperties(company, companyResponse);

        return companyResponse;

    }
}
