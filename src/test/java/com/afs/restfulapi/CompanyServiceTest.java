package com.afs.restfulapi;

import com.afs.restfulapi.Entity.Company;
import com.afs.restfulapi.Repository.CompanyRepository;
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
import static org.mockito.ArgumentMatchers.any;
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
       Company actual= companyService.getCompanyById(1);
        //then
        assertEquals(actual,companies.get(0));
    }
    //3

    //4
    @Test
    void should_return_page_when_get_Company_given_page_and_pageSize() {
        //given
        List<Company> companies = Arrays.asList(
                new Company("a"),
                new Company("b"),
                new Company("c")
        );
        List<Company> expected=companies.stream().skip(1).limit(1).collect(Collectors.toList());
        when(companyRepository.findAll(any(Pageable.class)))
                .thenReturn(new PageImpl(expected));
        //when
        Page<Company> actual= companyService.getCompanyListByPage(1,1);
        //then
        assertEquals(actual,new PageImpl(expected));
    }
}
