package com.example.springbootmysql.repository;

import java.util.List;

import javax.persistence.NamedQuery;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.springbootmysql.model.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

	Employee findByName(String name);

	Employee findByIdAndName(int id, String name);
	
	@Query(value = "select * from employee order by name desc", nativeQuery = true)
	//@Query("select e from employee e order by name desc")
	List<Employee> findAllEmployeeNameDesc();
	

}

