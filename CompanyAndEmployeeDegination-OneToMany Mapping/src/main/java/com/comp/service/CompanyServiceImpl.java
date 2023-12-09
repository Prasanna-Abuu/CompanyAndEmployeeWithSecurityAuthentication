package com.comp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.comp.model.Company;
import com.comp.model.CompanyEmployees;
import com.comp.repository.CompanyEmployeesRepository;
import com.comp.repository.CompanyRepository;

@Service
public class CompanyServiceImpl implements CompanyService {
	@Autowired
	CompanyRepository companyrepository;

	@Autowired
	CompanyEmployeesRepository companyemployeerepository;

	@Override
	public Company saveCompany(Company compRequest) {
		// TODO Auto-generated method stub
		return companyrepository.save(compRequest);

	}

	@Override
	public List<Company> getCompany() {
		// TODO Auto-generated method stub

		return companyrepository.findAll();
	}

	@Override
	public Optional<Company> getById(int id) {
		// TODO Auto-generated method stub
		return companyrepository.findById(id);

	}

	@Override
	public Company updateCompany(int id, Company compRequest) {
		// TODO Auto-generated method stub

		Company comp = companyrepository.getById(id);
		comp.setCompName(compRequest.getCompName());
		comp.setId(compRequest.getId());
		comp.setCompGradeNumber(compRequest.getCompGradeNumber());
		comp.setCompEmail(compRequest.getCompEmail());
		comp.setCompLocation(compRequest.getCompLocation());
		// emp.setEmpRole(empRequest.getEmpRole());
		return companyrepository.save(compRequest);
	}

	@Override
	public void deleteCompanyById(int id) {
		// TODO Auto-generated method stub
		companyrepository.deleteById(id);
	}

	@Override
	public Page<Company> getcompanySearchResult(String searchString, Integer pageSize, Integer pageNumber,
			String sortBy) {
		Pageable pageable;

		if (sortBy.equals("Oldest First")) {
			pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.ASC, "compName"));
		} else {
			pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, "compName"));
		}

		return companyrepository.getCompanySearchResult(searchString, pageable);
	}

	@Override
	public Page<Company> getCompanyCodesByPagination(Pageable page) {
		return companyrepository.findAll(page);

	}

	@Override
	public void saveEmployee(CompanyEmployees compEmpRequest) {
		companyemployeerepository.save(compEmpRequest);

	}

}
