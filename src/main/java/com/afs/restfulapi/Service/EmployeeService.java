package com.afs.restfulapi.Service;

import com.afs.restfulapi.Entity.Employee;
import com.afs.restfulapi.Repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getEmployeeList() {
        return this.employeeRepository.getEmployeeList();
    }

    public Employee getEmployeeById(int id) {
        return this.employeeRepository.getEmployeeById(id);
    }

    public List<Employee> getEmployeeListByPage(int page, int pageSize) {
        return this.employeeRepository.getEmployeeListByPage(page, pageSize);
    }

    public List<Employee> getEmployeeListByGender(String gender) {
        return this.employeeRepository.getEmployeeListByGender(gender);
    }

    public Employee addEmployee(Employee employee) {
        return this.employeeRepository.addEmployee(employee);
    }

    public Employee updateEmployee(Integer id, Employee update) {
        return this.employeeRepository.updateEmployee(id, update);
    }

    public boolean deleteEmployeeById(Integer id) {
        return this.employeeRepository.deleteEmployeeById(id);
    }
}
