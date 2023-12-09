package com.comp.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "Company")
public class Company {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "comp_name")
	private String compName;

	@Column(name = "comp_grade_number")
	private String compGradeNumber;

	@Column(name = "comp_email")
	private String compEmail;

	@Column(name = "comp_location")
	private String compLocation;

	// @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	// @JoinColumn(name = "company_id", referencedColumnName = "id")
	// Set<CompanyEmployees> companyemployees = new HashSet<>();

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "company_id", referencedColumnName = "id")
	Set<CompanyEmployees> companyemployees = new HashSet<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCompName() {
		return compName;
	}

	public void setCompName(String compName) {
		this.compName = compName;
	}

	public String getCompGradeNumber() {
		return compGradeNumber;
	}

	public void setCompGradeNumber(String compGradeNumber) {
		this.compGradeNumber = compGradeNumber;
	}

	public String getCompEmail() {
		return compEmail;
	}

	public void setCompEmail(String compEmail) {
		this.compEmail = compEmail;
	}

	public String getCompLocation() {
		return compLocation;
	}

	public void setCompLocation(String compLocation) {
		this.compLocation = compLocation;
	}

	public Set<CompanyEmployees> getCompanyemployees() {
		return companyemployees;
	}

	public void setCompanyemployees(Set<CompanyEmployees> companyemployees) {
		this.companyemployees = companyemployees;
	}

	
	
}