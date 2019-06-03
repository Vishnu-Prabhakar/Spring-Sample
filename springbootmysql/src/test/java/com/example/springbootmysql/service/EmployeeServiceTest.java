package com.example.springbootmysql.service;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.springbootmysql.model.Employee;
import com.example.springbootmysql.repository.EmployeeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
public class EmployeeServiceTest {

	@TestConfiguration
	static class UserRangeServiceTestContextConfiguration {
		@Bean
		public EmployeeService employeeService() {
			return new EmployeeService();
		}
	}

	@Autowired
	Environment env;

	@MockBean
	private EmployeeRepository employeeRepository;

	@Autowired
	private EmployeeService employeeService;

	ObjectMapper mapper;


	@Test
	public void givenEmployeeIdWhenGetThenReturnEmployee() {
		Employee emp =new Employee(1, "Vishnu", 17, "Coimbatore");
		Mockito.when(employeeRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(emp));
		assertNotNull(employeeService.getEmployee(1));
	}
	
	@Test
	public void givenGetAllployeeIdWhenGetThenReturnEmployee() {
		List<Employee> empList = new ArrayList<>();
		empList.add(new Employee(1, "Vishnu", 17, "Coimbatore"));
		assertNotNull(Mockito.when(employeeRepository.findAll()).thenReturn(empList));
	}

}
