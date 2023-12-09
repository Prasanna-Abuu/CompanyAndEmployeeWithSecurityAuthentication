package com.comp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.comp.model.CompanyEmployees;
@Repository
public interface CompanyEmployeesRepository extends JpaRepository<CompanyEmployees, Integer> {

}
