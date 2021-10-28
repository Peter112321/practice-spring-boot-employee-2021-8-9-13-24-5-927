package com.afs.restfulapi.Controller;


import com.afs.restfulapi.Entity.Employee;
import com.afs.restfulapi.Exception.EmployeeNotFoundException;
import com.afs.restfulapi.Service.EmployeeService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> getEmployeeList() {
        return this.employeeService.getEmployeeList();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable("id") Integer id) {
        return this.employeeService.getEmployeeById(id);
    }

    @RequestMapping(params = {"page", "pageSize"}, method = RequestMethod.GET)
    public Page<Employee> getEmployeeListByPage(@RequestParam(value = "page", defaultValue = "0") int page,
                                                @RequestParam(value = "pageSize", defaultValue = "5") int pageSize) {
        return this.employeeService.getEmployeeListByPage(page, pageSize);
    }

    @RequestMapping(params = {"gender"}, method = RequestMethod.GET)
    public List<Employee> getEmployeeListByGender(@RequestParam(value = "gender", defaultValue = "male") String gender) {
        return this.employeeService.getEmployeeListByGender(gender);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee) {
        return this.employeeService.addEmployee(employee);
    }

    @PutMapping("/{id}")
    public Employee updateEmployeeById(@PathVariable("id") Integer id, @RequestBody Employee employee) {
        return this.employeeService.updateEmployee(id, employee);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployeeById(@PathVariable("id") Integer id) {
        boolean isRemoved;

        try {
            isRemoved = this.employeeService.deleteEmployeeById(id);
        } catch (EmployeeNotFoundException e) {
            return new ResponseEntity<>(e.getMessage() + " ID:  " + id, HttpStatus.NOT_FOUND);
        }

        if (isRemoved) {
            return new ResponseEntity<>("Deleted Employee ID: " + id, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }
}