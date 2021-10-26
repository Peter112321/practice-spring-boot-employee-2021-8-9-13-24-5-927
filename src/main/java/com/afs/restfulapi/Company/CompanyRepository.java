package com.afs.restfulapi.Company;

import com.afs.restfulapi.Company.Company;
import com.afs.restfulapi.Employee.Employee;
import com.afs.restfulapi.Employee.EmployeeNotFoundException;
import org.hibernate.sql.Update;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

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


    public Company createCompany(Company company) {
        Integer newId = Companies.stream()
                .mapToInt(Company::getCompanyId)
                .max()
                .orElse(0) + 1;
        //todo
        Company.setCompanyId(newId);
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
}
