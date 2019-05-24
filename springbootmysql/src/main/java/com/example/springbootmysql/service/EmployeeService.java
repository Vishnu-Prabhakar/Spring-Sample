package com.example.springbootmysql.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springbootmysql.model.Employee;
import com.example.springbootmysql.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	EmployeeRepository repository;
	
	public Employee getEmployee(int id){
		return repository.findById(id).get();
	}
	
	public List<Employee> getAllEmployee(){
		return (List<Employee>) repository.findAll();
	}
	
	

}
