package com.afs.restfulapi.Exception;

public class CompanyNotFoundException extends RuntimeException{
    public CompanyNotFoundException() {
        super("Company not found");
    }
}
