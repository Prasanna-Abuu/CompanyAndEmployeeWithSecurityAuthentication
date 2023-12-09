package com.comp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.comp.model.Company;
import com.comp.model.CompanyEmployees;
import com.comp.service.CompanyService;

@RestController
public class CompanyController {

	@Autowired
	CompanyService companyService;

	@RequestMapping("/hello")
	@PreAuthorize("hasRole('ROLE_USER')")
	public String echo() {
		return "WellCome To Tcs Tata Prasanna Kumar!";
	}

	@PostMapping("/company/add")
	public ResponseEntity<String> saveCompany(@RequestBody Company compRequest) {

		companyService.saveCompany(compRequest);

		return ResponseEntity.status(201).body("Company Created Successfully ");
	}

	@PostMapping("/employee/add")
	public ResponseEntity<String> saveEmployee(@RequestBody CompanyEmployees compEmpRequest) {

		companyService.saveEmployee(compEmpRequest);
		return ResponseEntity.status(201).body("Employee Created Successfully ");

	}

	@GetMapping("/company/list")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public List<Company> getCompany() {
		return companyService.getCompany();
	}

	@GetMapping(path = "/company/{id}")
	@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
	public Optional<Company> getcompanyById(@PathVariable int id) {
		return companyService.getById(id);
	}

	@PutMapping("/company/edit/{id}")
	public ResponseEntity<String> updateCompany(@PathVariable int id, @RequestBody Company compRequest) {
		Company company = companyService.updateCompany(id, compRequest);
		return ResponseEntity.status(201).body("Company or Employee updated Successfully with Id " + company.getId());
	}

	@DeleteMapping("/Company/delete/{id}")
	public ResponseEntity<String> deleteCompanyById(@PathVariable int id) {
		companyService.deleteCompanyById(id);
		return ResponseEntity.status(200).body("Company or employee successfully deleted ");
	}

	@GetMapping("/company/search")
	public Page<Company> getcompanySearchResult(@RequestParam(value = "searchString") String searchString,
			@RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "0") Integer pageNumber,
			@RequestParam(value = "sortBy", required = false, defaultValue = "Newest First") String sortBy) {
		return companyService.getcompanySearchResult(searchString, pageSize, pageNumber, sortBy);

	}

	@GetMapping(path = "/company/pagination")
	public ResponseEntity<List<Company>> getCompanyCodesByPagination(@RequestParam Integer pageSize,
			@RequestParam Integer pageNumber) {

		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<Company> company1 = companyService.getCompanyCodesByPagination(pageable);
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<List<Company>>(company1.getContent(), headers, HttpStatus.OK);
	}
}
