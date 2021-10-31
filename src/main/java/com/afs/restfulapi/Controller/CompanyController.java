package com.afs.restfulapi.Controller;

import com.afs.restfulapi.DTO.CompanyRequest;
import com.afs.restfulapi.DTO.CompanyResponse;
import com.afs.restfulapi.DTO.EmployeeResponse;
import com.afs.restfulapi.Service.CompanyService;
import com.afs.restfulapi.mapper.CompanyMapper;
import com.afs.restfulapi.mapper.EmployeeMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    private final CompanyService companyService;
    private final CompanyMapper companyMapper;
    private final EmployeeMapper employeeMapper;

    public CompanyController(CompanyService companyService, CompanyMapper companyMapper, EmployeeMapper employeeMapper) {
        this.companyService = companyService;

        this.companyMapper = companyMapper;
        this.employeeMapper = employeeMapper;
    }

    @GetMapping
    public List<CompanyResponse> getCompanyList() {
        return this.companyService.getCompanyList().stream()
                .map(companyMapper::toResponse)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public CompanyResponse getCompanyById(@PathVariable Integer id) {
        return companyMapper.toResponse(this.companyService.getCompanyById(id));

    }

    @GetMapping("/{id}/employees")
    public List<EmployeeResponse> findEmployeeById(@PathVariable("id") int id) {
        return this.companyService.getEmployeeListInCompanyById(id).stream()
                .map(employeeMapper::toResponse)
                .collect(Collectors.toList());
    }

    @RequestMapping(params = {"page", "pageSize"}, method = RequestMethod.GET)
    public List<CompanyResponse> getCompanyListByPage(@RequestParam(value = "page", defaultValue = "0") int page,
                                                      @RequestParam(value = "pageSize", defaultValue = "5") int pageSize) {
        return this.companyService.getCompanyListByPage(page, pageSize).toList().stream()
                .map(company -> companyMapper.toResponse(company))
                .collect(Collectors.toList());
    }

    @PostMapping
    public CompanyResponse createCompany(@RequestBody CompanyRequest companyRequest) {
        return companyMapper.toResponse(this.companyService.addCompany(companyMapper.toEntity(companyRequest)));

    }

    @PutMapping("{/id}")
    public CompanyResponse editCompany(@PathVariable Integer id, @RequestBody CompanyRequest companyRequest) {
        return companyMapper.toResponse(this.companyService.updateCompany(id, companyMapper.toEntity(companyRequest)));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCompany(@PathVariable Integer id) {
        this.companyService.deleteCompanyById(id);
    }


}