package com.comp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.comp.model.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {

	@Query("SELECT c FROM Company c WHERE c.compName LIKE %:searchString% OR c.compEmail LIKE %:searchString%")
	Page<Company> getCompanySearchResult(@Param("searchString") String searchString, Pageable pageable);

}
