package com.afs.restfulapi.Company;

public class CompanyNotFoundException extends RuntimeException{
    public CompanyNotFoundException() {
        super("Company not found");
    }
}
