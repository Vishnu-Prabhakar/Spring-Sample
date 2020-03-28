package com.example.springbootmysql.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.springbootmysql.constants.ExceptionMessage;
import com.example.springbootmysql.exception.NoDataFoundException;
import com.example.springbootmysql.model.Employee;
import com.example.springbootmysql.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	private final Logger servicelog = LoggerFactory.getLogger(EmployeeService.class);
	
	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
	
	@Autowired
	EmployeeRepository repository;
	
	@Value("${employee-get-url}")
	String employeeGetURL;
	
	@Value("${employee-post-url}")
	String employeePostURL;
	
	@Autowired
	RestTemplate restTemplate;
	
	public Employee getEmployee(int id){
		return repository.findById(id).get();
	}
	
	public static final String CONTENT_TYPE = "Content-Type";

	public static final String ACCEPT = "Accept";

	public static final String APPLICATION_JSON = "application/json";
	
	public List<Employee> getAllEmployee(){
		servicelog.info("get all employee list");
		List<Employee> result = (List<Employee>) repository.findAll();
		if(result == null || result.isEmpty())
			throw new NoDataFoundException(ExceptionMessage.NO_DATA_FOUND_EXCEPTION);
		return result;
	}
	
	public Employee getEmployeeByName(String name){
		return repository.findByName(name);
	}

	public Employee getEmployeeByIdAndName(int id,String name) {
		return repository.findByIdAndName(id, name);
	}
	
	public List<Employee> findAllEmployeeNameDesc(){
		return repository.findAllEmployeeNameDesc();
	}

	public Employee createEmployee(Employee emp) {
		return repository.save(emp);
	}
	
	public void deleteEmployee(Employee emp) {
		repository.delete(emp);
	}
	
	public Employee consumeGetEmployee(int id){
		ResponseEntity<Employee> response = restTemplate.exchange(employeeGetURL+id, HttpMethod.GET, null, Employee.class);
		return response.getBody();
		
	}

	public Employee consumePostEmployee() {
		//Employee postData = new Employee(17,"Dravid",35,"Bangalore");
		HttpHeaders headers = new HttpHeaders();
//		headers.add(CONTENT_TYPE, APPLICATION_JSON);
//		headers.add(ACCEPT, APPLICATION_JSON);
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Employee> requestEntity =  new HttpEntity<>(new Employee(18,"Kaif",45,"Madurai"),headers);
		ResponseEntity<Employee> response = null ;
		try{
			response = restTemplate.postForEntity(employeePostURL, requestEntity, Employee.class);
		}catch(Exception e){
			e.printStackTrace();
		}
		return response.getBody();
	}
	
	

}
