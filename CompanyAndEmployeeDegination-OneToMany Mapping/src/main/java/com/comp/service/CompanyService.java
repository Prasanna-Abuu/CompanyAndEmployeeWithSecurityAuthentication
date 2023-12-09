package com.comp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.comp.model.Company;
import com.comp.model.CompanyEmployees;

public interface CompanyService {

	public Company saveCompany(Company compRequest);

	List<Company> getCompany();

	Company updateCompany(int id, Company compRequest);

	void deleteCompanyById(int id);

	Page<Company> getcompanySearchResult(String searchString, Integer pageSize, Integer pageNumber, String sortBy);

	Page<Company> getCompanyCodesByPagination(Pageable page);

	Optional<Company> getById(int id);

	void saveEmployee(CompanyEmployees compEmpRequest);

	

	

}
