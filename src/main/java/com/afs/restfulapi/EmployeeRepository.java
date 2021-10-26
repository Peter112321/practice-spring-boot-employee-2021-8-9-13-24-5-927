package com.afs.restfulapi;

import org.hibernate.sql.Update;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;


@Repository
public class EmployeeRepository {
    private final List<Employee> employees = new ArrayList<>();

    public EmployeeRepository() {
        employees.add(new Employee(1, "ang", 18, "male", 9));
        employees.add(new Employee(2, "Benny", 18, "male", 9));
        employees.add(new Employee(3, "Peter", 18, "male", 9));
    }

    public List<Employee> findAll() {
        return employees;
    }

    public Employee findById(Integer id) {
        return employees.stream()
                .filter(employee -> employee.getId() == (id))
                .findFirst()
                .orElseThrow(EmployeeNotFoundException::new);
    }

    public List<Employee> findByGender(String gender) {
        return employees.stream().filter(employee -> employee.getGender().equals(gender)).collect(Collectors.toList());
    }

    public Employee createEmployee(Employee employee) {
        Integer newId = employees.stream()
                .mapToInt(Employee::getId)
                .max()
                .orElse(0) + 1;
        employee.setId(newId);
        this.employees.add(employee);
        return employee;
    }

    public PageImpl<Employee> findPagingEmployees(Pageable pageable) {
        List<Employee> page = this.employees.stream()
                .skip((long) pageable.getPageNumber() * pageable.getPageSize())
                .limit(pageable.getPageSize())
                .collect(Collectors.toList());
        return new PageImpl<>(page, pageable, this.employees.size());
    }

    public Employee save(Integer id, Employee updatedEmployee) {
        this.deleteById(id);
        this.employees.add(updatedEmployee);
        return updatedEmployee;
    }

    public void deleteById(Integer id) {
        Employee employee = this.findById(id);
        this.employees.remove(employee);
    }


}
