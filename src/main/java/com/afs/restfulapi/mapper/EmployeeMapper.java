package com.afs.restfulapi.mapper;

import com.afs.restfulapi.DTO.EmployeeRequest;
import com.afs.restfulapi.DTO.EmployeeResponse;
import com.afs.restfulapi.Entity.Employee;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {
    public Employee toEntity(EmployeeRequest employeeRequest) {
        Employee employee = new Employee();

        employee.setAge(employeeRequest.getAge());
        employee.setCompanyId(employeeRequest.getCompanyId());
        employee.setName(employeeRequest.getName());
        employee.setGender(employeeRequest.getGender());
        employee.setSalary(employeeRequest.getSalary());

//        BeanUtils.copyProperties(employeeRequest,employee);

        return employee;
    }

    public EmployeeResponse toResponse(Employee employee) {
        EmployeeResponse employeeResponse = new EmployeeResponse();

//        employeeResponse.setAge(employeeResponse.getAge());
//        employeeResponse.setGender(employeeResponse.getGender());
//        employeeResponse.setId(employeeResponse.getId());
//        employeeResponse.setName(employeeResponse.getName());
        BeanUtils.copyProperties(employee,employeeResponse);

        return employeeResponse;
    }
}
