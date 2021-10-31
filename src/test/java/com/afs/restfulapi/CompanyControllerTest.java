package com.afs.restfulapi;

import com.afs.restfulapi.Entity.Company;
import com.afs.restfulapi.Entity.Employee;
import com.afs.restfulapi.Repository.CompanyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@Sql(statements = "alter table employee alter column id restart with 1")
@SpringBootTest
@AutoConfigureMockMvc
public class CompanyControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CompanyRepository companyRepository;

    @BeforeEach
    void SetUp() {
        companyRepository.deleteAll();
    }

    @Test
    void Should_return_all_companies_when_get_companies_given_two_companies() throws Exception{
        //given
        Company a = new Company("a");
        Company b = new Company("b");
        companyRepository.save(a);
        companyRepository.save(b);

        //when
        ResultActions resultActions = mockMvc.perform(get("/companies"));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(a.getCompanyId()))
                .andExpect(jsonPath("$[0].name").value(a.getCompanyName()))
                .andExpect(jsonPath("$[1].id").value(b.getCompanyId()))
                .andExpect(jsonPath("$[1].name").value(b.getCompanyName()));

    }









}
