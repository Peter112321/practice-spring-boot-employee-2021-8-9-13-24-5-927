package com.afs.restfulapi.Entity;

import net.bytebuddy.dynamic.loading.InjectionClassLoader;
import org.springframework.context.annotation.Primary;

import javax.persistence.*;
import java.util.List;

@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer companyId;
    private String companyName;
    @OneToMany(mappedBy = "companyId")
    private List<Employee> companyEmployee;


    public Company(Integer companyId, String companyName) {
        this.companyId = companyId;
        this.companyName = companyName;

    }

    public Company() {
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public List<Employee> getCompanyEmployee() {
        return companyEmployee;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyEmployee(List<Employee> companyEmployee) {
        this.companyEmployee = companyEmployee;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void updateCompanyData(Company company) {
        this.companyName = company.companyName;
        this.companyEmployee = company.companyEmployee;
    }
}