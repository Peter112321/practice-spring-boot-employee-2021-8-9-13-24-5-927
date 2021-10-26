package com.afs.restfulapi;

import java.util.function.Supplier;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException() {
        super("Employee Not Found");
    }

}
