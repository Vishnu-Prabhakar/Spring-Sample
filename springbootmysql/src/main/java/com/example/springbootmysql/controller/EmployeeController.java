package com.example.springbootmysql.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springbootmysql.annotation.Secure;
import com.example.springbootmysql.model.Employee;
import com.example.springbootmysql.service.EmployeeService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	@ApiOperation(value = "Get Emloyee details by Id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved employee details") })
	@GetMapping(value="/{id}",produces=MediaType.APPLICATION_JSON_VALUE)
	public Employee getEmployee(@PathVariable int id){
		return employeeService.getEmployee(id);
	}
	
	@GetMapping(value="/get/{name}",produces=MediaType.APPLICATION_JSON_VALUE)
	public Employee getEmployeeByName(@PathVariable String name){
		return employeeService.getEmployeeByName(name);
	}
	
	@GetMapping(value="/getByIdAndName/{id}/{name}",produces=MediaType.APPLICATION_JSON_VALUE)
	public Employee getEmployeeByNameAndId(@PathVariable int id,@PathVariable String name){
		return employeeService.getEmployeeByIdAndName(id, name);
	}
	
	
	@GetMapping(value="/getAllEmployeeNameDesc",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Employee> getEmployeeByNameDesc(){
		return employeeService.findAllEmployeeNameDesc();
	}
	
	@GetMapping(value="/getEmployees",produces=MediaType.APPLICATION_JSON_VALUE)
	@Secure(crudOperation = "READ")
	public List<Employee> getAllEmployee(@RequestHeader("accessToken") String accessToken){
		return employeeService.getAllEmployee();
	}

	
	@PostMapping(value="/createEmployee")
	public Employee createEmployee(@RequestBody Employee emp){
		return employeeService.createEmployee(emp);
	}
	
	@DeleteMapping(value="/deleteEmployee")
	public void deleteEmployee(@RequestBody Employee emp){
		 employeeService.deleteEmployee(emp);
	}
	
	@GetMapping(value="/consumeGetEmployee/{id}",produces=MediaType.APPLICATION_JSON_VALUE)
	public Employee consumeGetEmployee(@PathVariable int id){
		return employeeService.consumeGetEmployee(id);
	}
	
	/**
	 * This is the method to consume employee.....
	 * @return
	 */
	@GetMapping(value="/consumePostEmployee",produces=MediaType.APPLICATION_JSON_VALUE)
	public Employee consumePostEmployee(){
		return employeeService.consumePostEmployee();
	}
}
