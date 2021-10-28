package com.afs.restfulapi.Controller;

import com.afs.restfulapi.Entity.Company;
import com.afs.restfulapi.Exception.CompanyNotFoundException;
import com.afs.restfulapi.Repository.CompanyRepository;
import com.afs.restfulapi.Repository.NewCompanyRepository;
import com.afs.restfulapi.Service.CompanyService;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Componies")
public class CompanyController {
    private final CompanyService companyService;
    private final CompanyRepository companyRepository;

    public CompanyController(CompanyService companyService, CompanyRepository companyRepository) {
        this.companyService = companyService;
        this.companyRepository = companyRepository;
    }

    @GetMapping
    public List<Company> getCompanyList() {
        return this.companyService.getCompanyList();
    }

    @GetMapping("/{id}")
    public Company getCompanyById(@PathVariable Integer id) {
        return this.companyService.getCompanyById(id)
//                .orElseThrow(CompanyNotFoundException::new)
                ;
    }

//    @GetMapping("/{id}")
//    public List<Company> findByCompanyName(@RequestParam("CompanyName") String CompanyName) {
//        return this.companyService.getCompanyByName(CompanyName);
//    }

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
        Company originCompany = this.companyService.getCompanyById(id);
        if (originCompany.getCompanyName() != null) {
            originCompany.setCompanyName(updatedcompany.getCompanyName());
        }
        return this.companyService.updateCompany(id,updatedcompany);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCompany(@PathVariable Integer id) {

        this.companyService.deleteCompanyById(id);
    }


}

