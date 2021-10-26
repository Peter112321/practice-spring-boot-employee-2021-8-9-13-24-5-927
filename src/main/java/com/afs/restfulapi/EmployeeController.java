package com.afs.restfulapi;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/Employees")
public class EmployeeController {
    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping
    public List<Employee> getEmployeeList() {
        return new EmployeeRepository().findAll();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeByID(@PathVariable("id") int id) {
        return new EmployeeRepository().findById(id);
    }

    @GetMapping("/gender")
    public List<Employee> getEmployeeByGender(@RequestParam("gender") String gender) {
        return new EmployeeRepository().findByGender(gender);
    }

    @GetMapping(params = {"page", "size"})
    public PageImpl<Employee> findPagingEmployees(@PageableDefault Pageable pageable) {
        return this.employeeRepository.findPagingEmployees(pageable);
    }

    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        return this.employeeRepository.createEmployee(employee);
    }

    @PutMapping("/{id}")
    public Employee editEmployee(@PathVariable Integer id, @RequestBody Employee updatedEmployee) {
        Employee originEmployee = this.employeeRepository.findById(id);
        if (updatedEmployee.getAge() != null) {
            originEmployee.setAge(updatedEmployee.getAge());
        }
        if (updatedEmployee.getSalary() != null) {
            originEmployee.setSalary(updatedEmployee.getSalary());
        }
        return this.employeeRepository.save(id, originEmployee);

    }


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        this.employeeRepository.deleteById(id);
    }
}

