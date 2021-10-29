package com.afs.restfulapi.Controller;

import com.afs.restfulapi.Entity.Company;
import com.afs.restfulapi.Entity.Employee;
import com.afs.restfulapi.Service.CompanyService;
import com.afs.restfulapi.mapper.CompanyMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Companies")
public class CompanyController {
    private final CompanyService companyService;
    private final CompanyMapper companyMapper;

    public CompanyController(CompanyService companyService, CompanyMapper companyMapper) {
        this.companyService = companyService;

        this.companyMapper = companyMapper;
    }

    @GetMapping
    public List<Company> getCompanyList() {
        return this.companyService.getCompanyList().stream().map(company -> companyMapper.to);
    }

    @GetMapping("/{id}")
    public Company getCompanyById(@PathVariable Integer id) {
        return this.companyService.getCompanyById(id);
    }

    @GetMapping("/{id}/employees")
    public List<Employee> findEmployeeById(@RequestParam("id") int id) {
        return this.companyService.getEmployeeListInCompanyById(id);
    }

    @RequestMapping(params = {"page", "pageSize"}, method = RequestMethod.GET)
    public List<Company> getCompanyListByPage(@RequestParam(value = "page", defaultValue = "0") int page,
                                              @RequestParam(value = "pageSize", defaultValue = "5") int pageSize) {
        return this.companyService.getCompanyListByPage(page, pageSize).toList();
    }

    @PostMapping
    public Company createCompany(@RequestBody Company company) {
        return this.companyService.addCompany(company);
    }

    @PutMapping("{/id}")
    public Company editCompany(@PathVariable Integer id, @RequestBody Company updatedCompany) {
        Company originCompany = this.companyService.getCompanyById(id);
        if (originCompany.getCompanyName() != null) {
            originCompany.setCompanyName(updatedCompany.getCompanyName());
        }
        return this.companyService.updateCompany(id, updatedCompany);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCompany(@PathVariable Integer id) {

        this.companyService.deleteCompanyById(id);
    }


}