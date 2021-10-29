package com.afs.restfulapi.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer age;
    private String gender;
    private Integer salary;
    private Integer companyId;

    public Employee(String name, Integer age, String gender, Integer salary) {
        this.id = null;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.salary = salary;
    }

    public Employee() {
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public void updateData(Employee employee) {
        this.name = employee.name;
        this.age = employee.age;
        this.gender = employee.gender;
        this.salary = employee.salary;
    }

    //todo find out what's happening in this method
    @Override

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof Employee) {
            Employee employee = (Employee) o;
            return this.id.equals(((Employee) o).id);
        }
        return false;
    }
}