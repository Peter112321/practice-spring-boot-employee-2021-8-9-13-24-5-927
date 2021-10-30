package com.afs.restfulapi;

import com.afs.restfulapi.Entity.Employee;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Sql(statements = "alter table employee alter column id restart with 1")
@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EmployeeRepository employeeRepository;

    @BeforeEach
    void Init() {
        employeeRepository.deleteAll();
    }

    //1
    @Test
    void should_return_all_employee_when_get_employee_list_given_2_employee() throws Exception {
        //given
        Employee employee1 = new Employee("Benny", 19, "male", 20000);
        Employee employee2 = new Employee("Tommy", 22, "male", 20000);

        employeeRepository.save(employee1);
        employeeRepository.save(employee2);

        String expected = "[\n" +
                "    {\n" +
                "        \"id\": 1,\n" +
                "        \"name\": \"Benny\",\n" +
                "        \"age\": 19,\n" +
                "        \"gender\": \"male\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 2,\n" +
                "        \"name\": \"Tommy\",\n" +
                "        \"age\": 22,\n" +
                "        \"gender\": \"male\"\n" +
                "    }\n" +
                "]";

        //when
        ResultActions resultActions = mockMvc.perform(get("/employees"));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().json(expected));
    }

    @Test
    void should_return_correct_employee_when_get_by_id_given_id() throws Exception {
        //given
        Employee employee1 = new Employee("Benny", 19, "male", 20000);
        Employee employee2 = new Employee("Tommy", 22, "male", 20000);

        employeeRepository.save(employee1);
        employeeRepository.save(employee2);

        String expected =
                "{\n" +
                        "    \"id\": 1,\n" +
                        "    \"name\": \"Benny\",\n" +
                        "    \"age\": 19,\n" +
                        "    \"gender\": \"male\",\n" +
                        "    \"salary\": 20000\n" +
                        "}\n";

        //when
        ResultActions resultActions = mockMvc.perform(get("/employees/1"));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().json(expected));
    }

    @Test
    void should_return_correct_employees_page_when_get_page_given_page_and_page_size() throws Exception {
        //given
        Employee employee1 = new Employee("Benny", 19, "male", 20000);
        Employee employee2 = new Employee("Tommy", 22, "male", 20000);
        Employee employee3 = new Employee("Mary", 22, "female", 100000);


        employeeRepository.save(employee1);
        employeeRepository.save(employee2);
        employeeRepository.save(employee3);

        String expected = "{\"content\":[{\"id\":2,\"name\":\"Tommy\",\"age\":22,\"gender\":\"male\",\"salary\":20000}],\"pageable\":{\"sort\":{\"sorted\":false,\"unsorted\":true,\"empty\":true},\"pageNumber\":1,\"pageSize\":1,\"offset\":1,\"unpaged\":false,\"paged\":true},\"last\":false,\"totalPages\":3,\"totalElements\":3,\"numberOfElements\":1,\"first\":false,\"sort\":{\"sorted\":false,\"unsorted\":true,\"empty\":true},\"size\":1,\"number\":1,\"empty\":false}";

        //when
        ResultActions resultActions = mockMvc.perform(get("/employees?page=1&pageSize=1"));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().json(expected));
    }

    @Test
    void should_return_employee_list_when_get_page_by_gender_given_gender() throws Exception {
        //given
        Employee employee1 = new Employee("Benny", 19, "male", 20000);
        Employee employee2 = new Employee("Tommy", 22, "male", 20000);
        Employee employee3 = new Employee("Mary", 22, "female", 100000);

        employeeRepository.save(employee1);
        employeeRepository.save(employee2);
        employeeRepository.save(employee3);

        String expected = "[\n" +
                "    {\n" +
                "        \"id\": 3,\n" +
                "        \"name\": \"Mary\",\n" +
                "        \"age\": 22,\n" +
                "        \"gender\": \"female\",\n" +
                "        \"salary\": 100000\n" +
                "    }\n" +
                "]";

        //when
        ResultActions resultActions = mockMvc.perform(get("/employees?gender=female"));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().json(expected));
    }

    @Test
    void should_get_employee_when_add_employee_given_employee_info() throws Exception {
        //given
        String employeeInfo =
                "    {\n" +
                        "        \"name\": \"Benny\",\n" +
                        "        \"age\": 19,\n" +
                        "        \"gender\": \"male\",\n" +
                        "        \"salary\": 20000\n" +
                        "    }\n";

        String expected =
                "    {\n" +
                        "        \"id\": 1,\n" +
                        "        \"name\": \"Benny\",\n" +
                        "        \"age\": 19,\n" +
                        "        \"gender\": \"male\",\n" +
                        "        \"salary\": 20000\n" +
                        "    }\n";

        //when
        ResultActions resultActions = mockMvc
                .perform(post("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(employeeInfo));

        //then
        resultActions
                .andExpect(status().isCreated())
                .andExpect(content().json(expected));
    }

    @Test
    void should_get_employee_when_update_employee_given_employee_info() throws Exception {
        //given
        Employee employee = new Employee("Benny", 25, "male", 10000);
        employeeRepository.save(employee);

        String updateInfo =
                "    {\n" +
                        "        \"name\": \"Benny\",\n" +
                        "        \"age\": 19,\n" +
                        "        \"gender\": \"male\",\n" +
                        "        \"salary\": 20000\n" +
                        "    }\n";

        String expected =
                "    {\n" +
                        "        \"id\": 1,\n" +
                        "        \"name\": \"Benny\",\n" +
                        "        \"age\": 19,\n" +
                        "        \"gender\": \"male\",\n" +
                        "        \"salary\": 20000\n" +
                        "    }\n";

        //when
        ResultActions resultActions = mockMvc
                .perform(put("/employees/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateInfo));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().json(expected));
    }

    @Test
    void should_get_deleted_success_message_when_delete_employee_given_employee_id() throws Exception {
        //given
        Employee employee = new Employee("Benny", 25, "male", 10000);
        employeeRepository.save(employee);

        String expected = "Deleted Employee ID: 1";

        //when
        ResultActions resultActions = mockMvc
                .perform(delete("/employees/1"));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().string(expected));
    }
}
