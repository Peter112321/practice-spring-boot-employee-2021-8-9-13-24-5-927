package com.afs.restfulapi.Company;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Componies")
public class CompanyController {
    private final CompanyRepository companyRepository;

    public CompanyController(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }
    @GetMapping
    public List<Company> getCompanyList() {
        return new CompanyRepository().findAll();
    }

    @GetMapping("/{id}")
    public Company getCompanyListById(@PathVariable Integer id){
        return new CompanyRepository().findById(id);
    }

    @PostMapping
    public  Company createCompany(@RequestBody Company company){
        return this.companyRepository.createCompany(company);
    }

}

