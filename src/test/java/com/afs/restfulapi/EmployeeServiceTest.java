package com.afs.restfulapi;

import com.afs.restfulapi.Entity.Employee;
import com.afs.restfulapi.Repository.EmployeeRepository;
import com.afs.restfulapi.Service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class EmployeeServiceTest {
    @Test
    void should_return_all_employee_when_get_employee_list_given_3_employee() {
        EmployeeRepository employeeRepository = Mockito.mock(EmployeeRepository.class);
        EmployeeService employeeService = new EmployeeService(employeeRepository);
        List<Employee> employeeList = Arrays.asList(
                new Employee("Benny", 19, "male", 20000),
                new Employee("Tommy", 22, "male", 20000),
                new Employee("Mary", 22, "female", 100000)
        );
        when(employeeRepository.getEmployeeList()).thenReturn(employeeList);

        //when
        List<Employee> actual = employeeService.getEmployeeList();

        //then
        assertEquals(employeeList, actual);
    }

    @Test
    void should_return_correct_employee_when_get_by_id_given_id() {
        EmployeeRepository employeeRepository = Mockito.mock(EmployeeRepository.class);
        EmployeeService employeeService = new EmployeeService(employeeRepository);
        List<Employee> employeeList = Arrays.asList(
                new Employee("Benny", 19, "male", 20000),
                new Employee("Tommy", 22, "male", 20000),
                new Employee("Mary", 22, "female", 100000)
        );
        when(employeeRepository.getEmployeeById(1)).thenReturn(employeeList.get(0));

        //when
        Employee actual = employeeService.getEmployeeById(1);

        //then
        assertEquals(employeeList.get(0), actual);
    }

    @Test
    void should_return_correct_employees_page_when_get_page_given_page_and_page_size() {
        EmployeeRepository employeeRepository = Mockito.mock(EmployeeRepository.class);
        EmployeeService employeeService = new EmployeeService(employeeRepository);
        List<Employee> employeeList = Arrays.asList(
                new Employee("Benny", 19, "male", 20000),
                new Employee("Tommy", 22, "male", 20000),
                new Employee("Mary", 22, "female", 100000)
        );
        List<Employee> expected = employeeList.stream().skip(1).limit(1).collect(Collectors.toList());
        when(employeeRepository.getEmployeeListByPage(1, 1))
                .thenReturn(expected);

        //when
        List<Employee> actual = employeeService.getEmployeeListByPage(1, 1);

        //then
        assertEquals(expected, actual);
    }

    @Test
    void should_return_employees_list_when_get_by_gender_given_gender() {
        EmployeeRepository employeeRepository = Mockito.mock(EmployeeRepository.class);
        EmployeeService employeeService = new EmployeeService(employeeRepository);
        List<Employee> employeeList = Arrays.asList(
                new Employee("Benny", 19, "male", 20000),
                new Employee("Tommy", 22, "male", 20000),
                new Employee("Mary", 22, "female", 100000)
        );
        List<Employee> expected = employeeList
                .stream()
                .filter(employee -> employee.getGender().equals("male"))
                .collect(Collectors.toList());
        when(employeeRepository.getEmployeeListByGender("male"))
                .thenReturn(expected);

        //when
        List<Employee> actual = employeeService.getEmployeeListByGender("male");

        //then
        assertEquals(expected, actual);
    }

    @Test
    void should_return_employee_when_add_employee_given_employee() {
        EmployeeRepository employeeRepository = Mockito.mock(EmployeeRepository.class);
        EmployeeService employeeService = new EmployeeService(employeeRepository);
        List<Employee> employeeList = Arrays.asList(
                new Employee("Benny", 19, "male", 20000),
                new Employee("Tommy", 22, "male", 20000),
                new Employee("Mary", 22, "female", 100000)
        );

        Employee add = new Employee("Manny", 22, "male", 50000);
        Employee expected = new Employee("Manny", 22, "male", 50000);
        expected.setId(4);
        when(employeeRepository.addEmployee(add)).thenReturn(expected);

        //when
        Employee actual = employeeService.addEmployee(add);

        //then
        assertEquals(expected, actual);
    }

    @Test
    void should_return_updated_employee_when_update_employee_by_id_given_id_and_employee_info() {
        EmployeeRepository employeeRepository = Mockito.mock(EmployeeRepository.class);
        EmployeeService employeeService = new EmployeeService(employeeRepository);
        Employee employee = new Employee("Benny", 19, "male", 20000);

        when(employeeRepository.getEmployeeById(1)).thenReturn(employee);
        Employee update = new Employee(null, null, null, 123);
        Employee updated = new Employee("Benny", 19, "male", 123);
        updated.setId(1);
        when(employeeRepository.updateEmployee(1, update))
                .thenReturn(updated);


        //when
        Employee actual = employeeService.updateEmployee(1, update);

        //then
        assertEquals(1, actual.getId());
        assertEquals("Benny", actual.getName());
        assertEquals("male", actual.getGender());
        assertEquals(19, actual.getAge());
        assertEquals(123, actual.getSalary());
    }

    @Test
    void should_return_delete_message_when_delete_employee_given_id() {
        EmployeeRepository employeeRepository = Mockito.mock(EmployeeRepository.class);
        EmployeeService employeeService = new EmployeeService(employeeRepository);
        Employee employee = new Employee("Benny", 19, "male", 20000);
        employeeRepository.addEmployee(employee);

        when(employeeRepository.deleteEmployeeById(1)).thenReturn(true);


        //when
        boolean actual = employeeService.deleteEmployeeById(1);

        //then
        assertTrue(actual);
    }
}
