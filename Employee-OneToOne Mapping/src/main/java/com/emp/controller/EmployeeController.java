package com.emp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.emp.model.Employee;
import com.emp.service.EmployeeService;

@RestController
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@RequestMapping("/hello")
	public String echo() {
		return "Hello Prasanna Kumar Tata!";
	}

	@PostMapping("/employee/add")
	public ResponseEntity<String> createEmployee(@RequestBody Employee empRequest) {

		Employee employee = employeeService.createEmployee(empRequest);

		return ResponseEntity.status(201).body("Employee Created Successfully with Id " + employee.getId());
	}

	@GetMapping("/employee/list")
	public List<Employee> getEmployees() {

		return employeeService.getEmployees();
	}

	@GetMapping(path = "/employee/{id}")
	public Optional<Employee> getemployeeById(@PathVariable int id) {
		return employeeService.getById(id);
	}

	@PutMapping("/employee/edit/{id}")
	public ResponseEntity<String> updateEmployee(@PathVariable int id, @RequestBody Employee empRequest) {
		Employee employee = employeeService.updateEmployee(id, empRequest);
		return ResponseEntity.status(201).body("Employee updated Successfully with Id " + employee.getId());
	}

	@DeleteMapping("/employee/delete/{id}")
	public ResponseEntity<String> deleteEmployeeById(@PathVariable int id) {
		employeeService.deleteEmployeeById(id);
		return ResponseEntity.status(200).body("employee successfully deleted ");
	}

	@GetMapping("/employee/search")
	public Page<Employee> getemployeeSearchResult(@RequestParam(value = "searchString") String searchString,
			@RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "0") Integer pageNumber,
			@RequestParam(value = "sortBy", required = false, defaultValue = "Newest First") String sortBy) {
		return employeeService.getemployeeSearchResult(searchString, pageSize, pageNumber, sortBy);

	}
	
	
		

	@GetMapping(path = "/employee/pagination")
	public ResponseEntity<List<Employee>> getEmployeeCodesByPagination(@RequestParam Integer pageSize,
			@RequestParam Integer pageNumber) {

		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<Employee> employee2 = employeeService.getEmployeeCodesByPagination(pageable);
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<List<Employee>>(employee2.getContent(), headers, HttpStatus.OK);
	}
}
