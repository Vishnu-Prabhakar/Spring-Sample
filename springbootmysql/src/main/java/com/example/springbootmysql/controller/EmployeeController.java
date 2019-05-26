package com.example.springbootmysql.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springbootmysql.model.Employee;
import com.example.springbootmysql.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	@GetMapping(value="/{id}",produces=MediaType.APPLICATION_JSON_VALUE)
	public Employee getEmployee(@PathVariable int id){
		return employeeService.getEmployee(id);
	}
	
	@GetMapping(value="/getEmployees",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Employee> getAllEmployee(){
		return employeeService.getAllEmployee();
	}

}
