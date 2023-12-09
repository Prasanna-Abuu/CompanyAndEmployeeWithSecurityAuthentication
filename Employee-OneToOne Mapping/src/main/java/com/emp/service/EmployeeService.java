package com.emp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.emp.model.Employee;

public interface EmployeeService {

	Employee createEmployee(Employee empRequest);

	List<Employee> getEmployees();

	Employee updateEmployee(int id, Employee empRequest);

	void deleteEmployeeById(int id);

	Page<Employee> getemployeeSearchResult(String searchString, Integer pageSize, Integer pageNumber, String sortBy);

	Page<Employee> getEmployeeCodesByPagination(Pageable page);

	Optional<Employee> getById(int id);

}
