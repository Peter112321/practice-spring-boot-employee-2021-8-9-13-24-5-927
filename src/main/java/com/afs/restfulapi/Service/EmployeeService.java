package com.afs.restfulapi.Service;

import com.afs.restfulapi.Entity.Employee;
import com.afs.restfulapi.Exception.EmployeeNotFoundException;
import com.afs.restfulapi.Repository.EmployeeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getEmployeeList() {
        return this.employeeRepository.findAll();
    }

    public Employee getEmployeeById(int id) {
        return this.employeeRepository.findById(id).orElseThrow(EmployeeNotFoundException::new);
    }

    public Page<Employee> getEmployeeListByPage(int page, int pageSize) {
        return this.employeeRepository.findAll(PageRequest.of(page, pageSize));
    }

    public List<Employee> getEmployeeListByGender(String gender) {
        return this.employeeRepository.findAllByGender(gender);
    }

    public Employee addEmployee(Employee employee) {
        return this.employeeRepository.save(employee);
    }

    public Employee updateEmployee(Integer id, Employee update) {
        Employee employee = this.getEmployeeById(id);
        if(update.getName()!=null){
            employee.setName(update.getName());
        }
        if(update.getAge()!=null){
            employee.setAge(update.getAge());
        }
        if(update.getGender()!=null){
            employee.setGender(update.getGender());
        }
        if(update.getSalary()!=null){
            employee.setSalary(update.getSalary());
        }
        if(update.getCompanyId()!=null){
            employee.setCompanyId(update.getCompanyId());
        }
        return this.employeeRepository.save(employee);
}

    public boolean deleteEmployeeById(Integer id) {
        Employee employee =this.getEmployeeById(id);
        this.employeeRepository.delete(employee);
        return true;
    }
}
