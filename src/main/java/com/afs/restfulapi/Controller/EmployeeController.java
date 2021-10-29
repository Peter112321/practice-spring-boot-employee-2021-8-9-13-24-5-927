package com.afs.restfulapi.Controller;


import com.afs.restfulapi.DTO.EmployeeRequest;
import com.afs.restfulapi.DTO.EmployeeResponse;
import com.afs.restfulapi.Entity.Employee;
import com.afs.restfulapi.Exception.EmployeeNotFoundException;
import com.afs.restfulapi.Service.EmployeeService;
import com.afs.restfulapi.mapper.EmployeeMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;
    private final EmployeeMapper employeeMapper;

    public EmployeeController(EmployeeService employeeService, EmployeeMapper employeeMapper) {
        this.employeeService = employeeService;
        this.employeeMapper = employeeMapper;
    }

    @GetMapping
    public List<EmployeeResponse> getEmployeeList() {

        return this.employeeService.getEmployeeList()
                .stream()
                .map(employee -> employeeMapper.toResponse(employee))
                .collect(Collectors.toList());
    }
//to res
    @GetMapping("/{id}")
    public EmployeeResponse getEmployeeById(@PathVariable("id") Integer id) {

        return employeeMapper.toResponse(this.employeeService.getEmployeeById(id));
    }

//    @RequestMapping(params = {"page", "pageSize"}, method = RequestMethod.GET)
//    public Page<Employee> getEmployeeListByPage(@RequestParam(value = "page", defaultValue = "0") int page,
//                                                @RequestParam(value = "pageSize", defaultValue = "5") int pageSize) {
//        return this.employeeService.getEmployeeListByPage(page, pageSize).map(employee -> employeeMapper.toResponse(Employee));
//    }



    @RequestMapping(params = {"gender"}, method = RequestMethod.GET)
    public List<EmployeeResponse> getEmployeeListByGender(@RequestParam(value = "gender", defaultValue = "male") String gender) {
        return this.employeeService.getEmployeeListByGender(gender).stream()
                .map(employee -> employeeMapper.toResponse(employee))
                .collect(Collectors.toList());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Employee addEmployee(@RequestBody EmployeeRequest employeeRequest) {
        return this.employeeService.addEmployee(employeeMapper.toEntity(employeeRequest));
    }

    @PutMapping("/{id}")
    public Employee updateEmployeeById(@PathVariable("id") Integer id, @RequestBody EmployeeRequest employeeRequest) {
        return this.employeeService.updateEmployee(id, employeeMapper.toEntity(employeeRequest));
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