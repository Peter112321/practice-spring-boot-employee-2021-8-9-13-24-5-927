package com.afs.restfulapi.DTO;

import com.afs.restfulapi.Entity.Employee;

import java.util.List;

public class CompanyResponse {
    private Integer id;
    private String name;
    private List<Employee> employees;

    public CompanyResponse() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
