package com.afs.restfulapi.Company;

import com.afs.restfulapi.Company.Company;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CompanyRepository {

    private final List<Company> Companies = new ArrayList<>();

    public CompanyRepository() {
        Companies.add(new Company(1, "A"));
        Companies.add(new Company(2, "B"));
        Companies.add(new Company(3, "C"));
    }


    public List<Company> findAll() {
        return Companies;

    }
}
