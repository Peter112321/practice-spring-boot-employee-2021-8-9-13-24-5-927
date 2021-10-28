package com.afs.restfulapi.Service;

import com.afs.restfulapi.Entity.Company;
import com.afs.restfulapi.Entity.Employee;
import com.afs.restfulapi.Exception.CompanyNotFoundException;
import com.afs.restfulapi.Repository.NewCompanyRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CompanyService {
    private final NewCompanyRepository newCompanyRepository;

    public CompanyService(NewCompanyRepository newCompanyRepository) {
        this.newCompanyRepository = newCompanyRepository;
    }

    public List<Company> getCompanyList() {
        return this.newCompanyRepository.findAll();
    }

    public Company getCompanyById(int id) {
        return this.newCompanyRepository.findById(id).orElseThrow(CompanyNotFoundException::new);
    }

//    public List<Employee> getEmployeeListInCompanyById(int id) {
//        return findById(id).getEmployees();
//    }

    public Company addCompany(Company company) {

        return this.newCompanyRepository.save(company);
    }

    public Company updateCompany(Integer id, Company update) {
        Company company = this.getCompanyById(id);
        if (company.getCompanyId() != null) {
            company.updateCompanyData(update);
        }
        if (company.getCompanyEmployee() != null) {
            company.updateCompanyData(update);
        }
        return this.newCompanyRepository.save(company);
    }

    public boolean deleteCompanyById(Integer id) {
        Company company = this.getCompanyById(id);
        this.newCompanyRepository.delete(company);
        return true;
    }


}
