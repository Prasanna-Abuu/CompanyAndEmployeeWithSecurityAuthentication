package com.emp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.emp.model.Employee;
import com.emp.repository.EmployeeRepository;
import com.emp.repository.EmployeeRoleRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	EmployeeRepository employeerepository;

	@Autowired
	EmployeeRoleRepository employeerolerepository;

	@Override
	public Employee createEmployee(Employee empRequest) {
		// TODO Auto-generated method stub
		return employeerepository.save(empRequest);
	}

	@Override
	public List<Employee> getEmployees() {
		// TODO Auto-generated method stub
		return employeerepository.findAll();
	}

	@Override
	public Optional<Employee> getById(int id) {
		// TODO Auto-generated method stub
		return employeerepository.findById(id);

	}

	@Override
	public Employee updateEmployee(int id, Employee empRequest) {
		// TODO Auto-generated method stub
		Employee emp = employeerepository.getById(id);
		emp.setEmpName(empRequest.getEmpName());
		emp.setId(empRequest.getId());
		emp.setEmpMobileNumber(empRequest.getEmpMobileNumber());
		emp.setEmpEmail(empRequest.getEmpEmail());
		emp.setEmpSal(empRequest.getEmpSal());
		// emp.setEmpRole(empRequest.getEmpRole());
		return employeerepository.save(empRequest);
	}

	@Override
	public void deleteEmployeeById(int id) {
		// TODO Auto-generated method stub
		employeerepository.deleteById(id);
	}

	@Override
	public Page<Employee> getemployeeSearchResult(String searchString, Integer pageSize, Integer pageNumber,
			String sortBy) {
		Pageable pageable;

		if (sortBy.equals("Oldest First")) {
			pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.ASC, "empName"));
		} else {
			pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, "empName"));
		}

		return employeerepository.getEmployeeSearchResult(searchString, pageable);
	}

	@Override
	public Page<Employee> getEmployeeCodesByPagination(Pageable page) {
		return employeerepository.findAll(page);

	}

}
