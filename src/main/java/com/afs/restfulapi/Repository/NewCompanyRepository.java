package com.afs.restfulapi.Repository;

import com.afs.restfulapi.Entity.Company;
import com.afs.restfulapi.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewCompanyRepository extends JpaRepository<Company, Integer> {

}
