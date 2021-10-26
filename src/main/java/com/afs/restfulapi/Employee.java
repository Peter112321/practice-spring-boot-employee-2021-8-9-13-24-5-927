package com.afs.restfulapi;

public class Employee {
    private Integer id;
    private String name;
    private Integer age;
    private String gender;
    private Integer salary;


    public Employee(Integer id, String name, Integer age, String gender, Integer salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.salary = salary;
    }

    public Integer getSalary() {
        return salary;
    }

    public String getGender() {
        return gender;
    }

    public Integer getAge() {
        return age;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public void setAge(Integer age) {
        this.age = age;
    }


}
