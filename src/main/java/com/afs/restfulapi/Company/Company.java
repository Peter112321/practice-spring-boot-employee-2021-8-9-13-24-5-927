package com.afs.restfulapi.Company;

import com.afs.restfulapi.Employee.Employee;

import java.util.List;

public class Company {
    private Integer companyId;
    private String companyName;
    private List<Employee> companyEmployee;


    public Company(Integer companyId, String companyName) {
        this.companyId = companyId;
        this.companyName = companyName;

    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }
}
