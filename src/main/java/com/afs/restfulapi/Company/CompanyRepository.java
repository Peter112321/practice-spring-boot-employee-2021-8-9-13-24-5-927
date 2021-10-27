package com.afs.restfulapi.Company;

import com.afs.restfulapi.Company.Company;
import com.afs.restfulapi.Employee.Employee;
import com.afs.restfulapi.Employee.EmployeeNotFoundException;
import com.afs.restfulapi.Employee.EmployeeRepository;
import org.hibernate.sql.Update;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CompanyRepository {

    private final List<Company> Companies = new ArrayList<>();

    public CompanyRepository() {
        Companies.add(new Company(1, "A"));
        Companies.add(new Company(2, "B"));
        Companies.add(new Company(3, "C"));
    }


    public List<Company> findAll() {
        return Companies;

    }

    public Company findById(Integer id) {
        return Companies.stream()
                .filter(company -> company.getCompanyId() == id)
                .findFirst()
                .orElseThrow(CompanyNotFoundException::new);
    }


    public List<Company> findByCompanyName(String CompanyName) {
        return Companies.stream()
                .filter(Company -> Company.getCompanyName()
                        .equals(CompanyName))
                .collect(Collectors.toList());
    }

    public Company createCompany(Company company) {
        Integer newId = Companies.stream()
                .mapToInt(Company::getCompanyId)
                .max()
                .orElse(0) + 1;
        company.setCompanyId(newId);
        this.Companies.add(company);
        return company;

    }

    public Company saveCompany(Integer id, Company updatedCompany) {
        this.deleteCompany(id);
        this.Companies.add(updatedCompany);
        return updatedCompany;
    }

    public void deleteCompany(Integer id) {
        Company company = findById(id);
        Companies.remove(company);
    }

    public PageImpl<Company> findPagingCompanies(Pageable pageable) {
        List<Company> page = this.Companies.stream()
                .skip((long) pageable.getPageNumber() * pageable.getPageSize())
                .limit(pageable.getPageSize())
                .collect(Collectors.toList());
        return new PageImpl<>(page, pageable, this.Companies.size());

    }
}
