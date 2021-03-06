package com.afs.restfulapi;

import com.afs.restfulapi.Entity.Company;
import com.afs.restfulapi.Entity.Employee;
import com.afs.restfulapi.Repository.CompanyRepository;
import com.afs.restfulapi.Repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Sql(statements = "alter table employee alter column id restart with 1")
@SpringBootTest
@AutoConfigureMockMvc
public class CompanyControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @BeforeEach
    void SetUp() {
        companyRepository.deleteAll();
    }

    //1
    @Test
    void Should_return_all_companies_when_get_companies_given_two_companies() throws Exception {
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

    //2
    @Test
    void Should_return_Specific_companies_when_get_companies_given_company_id() throws Exception {
        //given
        Company a = new Company("a");
        Company b = new Company("b");
        companyRepository.save(a);
        companyRepository.save(b);

        //when
        ResultActions resultActions = mockMvc.perform(get("/companies/2"));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(b.getCompanyId()))
                .andExpect(jsonPath("$.name").value(b.getCompanyName()));

    }


    //3
    @Test
    void Should_return_employees_when_get_companies_given_company_id() throws Exception {
        //given
        Company a = new Company("a");
        Company b = new Company("b");
        companyRepository.save(a);
        companyRepository.save(b);

        Employee employee1 = new Employee("Benny", 19, "male", 20000, a.getCompanyId());
        Employee employee2 = new Employee("Tommy", 22, "male", 20000, b.getCompanyId());
        employeeRepository.save(employee1);
        employeeRepository.save(employee2);
//when
        ResultActions resultActions = mockMvc.perform(get("/companies/1/employees"));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(employee1.getId()))
                .andExpect(jsonPath("$[0].name").value(employee1.getName()));

    }

    //4
    @Test
    void Should_return_company_page_when_get_companies_given_page_size_and_page_number() throws Exception {
        //given
        Company a = new Company("a");
        Company b = new Company("b");
        Company c = new Company("c");

        companyRepository.save(a);
        companyRepository.save(b);
        companyRepository.save(c);

        //when
        ResultActions resultActions = mockMvc.perform(get("/companies?pageSize=1?page=1"));

        //when
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[1].id").value(b.getCompanyId()))
                .andExpect(jsonPath("$[1].name").value(b.getCompanyName()));
    }

    //5
    @Test
    void Should_update_company_when_update_company_given_company_info() throws Exception {
        //given
        Company a = new Company("a");
        companyRepository.save(a);
        String update =
                "{\n" +
                        "   \"name\": \"NewName\"\n" +
                        "}\n";

        //when
        ResultActions resultActions = mockMvc.perform(put("/companies/1")
                .content(update)
                .contentType(MediaType.APPLICATION_JSON));
        //when
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(a.getCompanyId()))
                .andExpect(jsonPath("$.name").value("NewName"));
    }

    //6
    @Test
    void Should_add_new_companies_when_add_companies_given_companies_info() throws Exception {
        //given

        String add =
                "{\n" +
                        "   \"name\": \"NewName\"\n" +
                        "}\n";

        //when
        ResultActions resultActions = mockMvc.perform(post("/companies")
                .content(add)
                .contentType(MediaType.APPLICATION_JSON));
        //when
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value("NewName"));
    }

//7

    @Test
    void should_get_deleted_success_message_when_delete_company_given_company_id() throws Exception {
        Company a = new Company("a");
        companyRepository.save(a);

        ResultActions resultActions = mockMvc.perform(delete("/companies/1"));

        resultActions
                .andExpect(status().isNoContent())
                .andExpect(jsonPath("$[0].id").doesNotExist())
                .andExpect(jsonPath("$[0].name").doesNotExist());
    }
}
