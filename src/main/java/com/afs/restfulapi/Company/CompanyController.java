package com.afs.restfulapi.Company;

import com.afs.restfulapi.Employee.Employee;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
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
    public Company getCompanyListById(@PathVariable Integer id) {
        return new CompanyRepository().findById(id);
    }

    @GetMapping(params = {"page", "size"})
    public PageImpl<Company> findPagingCompanies(@PageableDefault Pageable pageable) {
        return this.companyRepository.findPagingCompanies(pageable);
    }

    @PostMapping
    public Company createCompany(@RequestBody Company company) {
        return this.companyRepository.createCompany(company);
    }

    @PutMapping("{/id}")
    public Company editCompany(@PathVariable Integer id, @RequestBody Company updatedcompany) {
        Company originCompany = this.companyRepository.findById(id);
        if (originCompany.getCompanyName() != null) {
            originCompany.setCompanyName(updatedcompany.getCompanyName());
        }
        return this.companyRepository.saveCompany(id, updatedcompany);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCompany(@PathVariable Integer id){
        this.companyRepository.deleteCompany(id);
    }


}

