package com.afs.restfulapi.Employee;

import java.util.function.Supplier;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException() {
        super("Employee Not Found");
    }


}
