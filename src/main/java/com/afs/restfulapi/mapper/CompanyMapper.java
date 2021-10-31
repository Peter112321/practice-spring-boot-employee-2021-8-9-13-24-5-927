package com.afs.restfulapi.mapper;

import com.afs.restfulapi.DTO.CompanyRequest;
import com.afs.restfulapi.DTO.CompanyResponse;
import com.afs.restfulapi.DTO.EmployeeResponse;
import com.afs.restfulapi.Entity.Company;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CompanyMapper {
    private EmployeeMapper employeeMapper;

    public CompanyMapper(EmployeeMapper employeeMapper) {
        this.employeeMapper = employeeMapper;
    }

    public Company toEntity(CompanyRequest companyRequest) {
        Company company = new Company();
        company.setCompanyName(company.getCompanyName());
        return company;
    }

    public CompanyResponse toResponse(Company company) {
        CompanyResponse companyResponse = new CompanyResponse();

        companyResponse.setId(company.getCompanyId());
        companyResponse.setName(company.getCompanyName());
        List<EmployeeResponse> employeeResponseList =
                company.getCompanyEmployee()
                        .stream()
                        .map(employeeMapper::toResponse)
                        .collect(Collectors.toList());
        companyResponse.setEmployees(employeeResponseList);
        return companyResponse;

    }
}
