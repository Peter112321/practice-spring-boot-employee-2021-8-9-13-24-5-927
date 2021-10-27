package com.afs.restfulapi.Exception;

import java.util.function.Supplier;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException() {
        super("Employee Not Found");
    }


}
