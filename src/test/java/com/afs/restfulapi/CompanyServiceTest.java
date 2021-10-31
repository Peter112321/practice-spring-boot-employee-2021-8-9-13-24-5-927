package com.afs.restfulapi;

import com.afs.restfulapi.Entity.Company;
import com.afs.restfulapi.Repository.CompanyRepository;
import com.afs.restfulapi.Service.CompanyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class CompanyServiceTest {
    @Mock
    CompanyRepository companyRepository;
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
        List<Company> actual= companyService.getCompanyList();
        //then
        assertEquals(actual,companies);
    }

    
}
