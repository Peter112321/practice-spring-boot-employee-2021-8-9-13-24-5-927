package com.afs.restfulapi;

import com.afs.restfulapi.Entity.Company;
import com.afs.restfulapi.Entity.Employee;
import com.afs.restfulapi.Repository.CompanyRepository;
import com.afs.restfulapi.Repository.EmployeeRepository;
import com.afs.restfulapi.Service.CompanyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class CompanyServiceTest {
    @Mock
    CompanyRepository companyRepository;
    @Mock
    EmployeeRepository employeeRepository;
    @InjectMocks
    CompanyService companyService;



    //1
    @Test
    void should_return_all_Companies_when_get_Company_list_given_3_Companies() {
        //given
        List<Company> companies = Arrays.asList(
                new Company("a"),
                new Company("b"),
                new Company("c")
        );
        when(companyRepository.findAll()).thenReturn(companies);

        //when
        List<Company> actual = companyService.getCompanyList();
        //then
        assertEquals(actual, companies);
    }

    //2
    @Test
    void should_return_Company_when_get_Company_given_CompanyId() {
        //given
        List<Company> companies = Arrays.asList(
                new Company("a"),
                new Company("b"),
                new Company("c")
        );
        when(companyRepository.findById(1)).thenReturn(Optional.of(companies.get(0)));

        //when
        Company actual = companyService.getCompanyById(1);
        //then
        assertEquals(actual, companies.get(0));
    }
    //3

    @Test
    void should_return_Employee_list_when_get_Company_given_CompanyId() {
        //given

        List<Employee> employeeList = Arrays.asList(
                new Employee("Benny", 19, "male", 20000,1),
                new Employee("Tommy", 22, "male", 20000,1),
                new Employee("Mary", 22, "female", 100000,2)
        );

        when(employeeRepository.findAllByCompanyId(1)).thenReturn(employeeList);

        //when
        List<Employee> actual = companyService.getEmployeeListInCompanyById(1);
        //then
        assertEquals(actual, employeeList);
    }

    //4
    @Test
    void should_return_page_when_get_Company_given_page_and_pageSize() {
        //given
        List<Company> companies = Arrays.asList(
                new Company("a"),
                new Company("b"),
                new Company("c")
        );
        List<Company> expected = companies.stream().skip(1).limit(1).collect(Collectors.toList());
        when(companyRepository.findAll(any(Pageable.class)))
                .thenReturn(new PageImpl(expected));
        //when
        Page<Company> actual = companyService.getCompanyListByPage(1, 1);
        //then
        assertEquals(actual, new PageImpl(expected));
    }

    //5
    @Test
    void should_return_company_when_add_Company_given_companyInfo() {
        //given
        List<Company> companies = Arrays.asList(
                new Company("a"),
                new Company("b"),
                new Company("c")
        );
        Company newCompany = new Company("d");
        when(companyRepository.save(newCompany))
                .thenReturn(newCompany);
        //when
        Company actual = companyService.addCompany(newCompany);
        //then
        assertEquals(actual, newCompany);
    }

    //6

    @Test
    void should_return_updated_company_when_put_Company_given_companyInfo() {
        //given
        Company a = new Company("a");
        when(companyRepository.findById(1)).thenReturn(Optional.of(a));
        Company updated = new Company("d");
        when(companyRepository.save(any(Company.class)))
                .thenReturn(updated);
        //when
        Company actual = companyService.updateCompany(1, updated);
        //then
        assertEquals(actual, updated);
    }

    //7
    @Test
    void should_return_True_when_delete_Company_given_company_id() {

        //given
        doNothing().when(companyRepository).deleteById(1);
        //when
        companyService.deleteCompanyById(1);
        //then
        verify(companyRepository, times(1)).deleteById(1);
    }
}
