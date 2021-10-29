package com.afs.restfulapi.Service;

import com.afs.restfulapi.Entity.Company;
import com.afs.restfulapi.Entity.Employee;
import com.afs.restfulapi.Exception.CompanyNotFoundException;
import com.afs.restfulapi.Repository.CompanyRepository;
import com.afs.restfulapi.Repository.EmployeeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final EmployeeRepository employeeRepository;

    public CompanyService(CompanyRepository companyRepository, EmployeeRepository employeeRepository) {
        this.companyRepository = companyRepository;
        this.employeeRepository = employeeRepository;
    }

    public List<Company> getCompanyList() {
        return this.companyRepository.findAll();
    }

    public Company getCompanyById(int id) {
        return this.companyRepository.findById(id).orElseThrow(CompanyNotFoundException::new);
    }

        public List<Employee> getEmployeeListInCompanyById(int id) {
        return this.employeeRepository.findAllByCompanyId(id);
  }
    //todo try previous method
    public Page<Company> getCompanyListByPage(int page, int pageSize) {
        return this.companyRepository.findAll(PageRequest.of(page, pageSize));
    }

    public Company addCompany(Company company) {

        return this.companyRepository.save(company);
    }

    public Company updateCompany(Integer id, Company update) {
        Company company = this.getCompanyById(id);
        if (update.getCompanyId() != null) {
            company.updateCompanyData(update);
        }
        if (update.getCompanyEmployee() != null) {
            company.updateCompanyData(update);
        }
        return this.companyRepository.save(company);
    }

    public boolean deleteCompanyById(Integer id) {
        Company company = this.getCompanyById(id);
        this.companyRepository.delete(company);
        return true;
    }


}