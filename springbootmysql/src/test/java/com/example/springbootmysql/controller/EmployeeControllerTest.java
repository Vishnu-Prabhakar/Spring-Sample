package com.example.springbootmysql.controller;

import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.HttpClientErrorException.Unauthorized;

import com.example.springbootmysql.model.Employee;
import com.example.springbootmysql.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private EmployeeService employeeService;

	@Autowired
	ObjectMapper objectMapper;

	@Test
	public void whenGetEmployeebyIdThenReturnResponseSuccess() throws Exception {
		Employee emp = new Employee();
		when(employeeService.getEmployee(Mockito.anyInt())).thenReturn(emp);
		this.mockMvc
				.perform(get("/employee/1").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void whenGetAllEmployeeThenReturnResponseSuccess() throws Exception {
		List<Employee> empList = new ArrayList<>();
		empList.add(new Employee(1, "Vishnu", 17, "Coimbatore"));
		Mockito.doReturn(empList).when(employeeService).getAllEmployee();
		this.mockMvc
				.perform(get("/employee/getEmployees").header("accessToken", "dummy").accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$[0].id", is(1)));
	}

	@Test
	public void whenRequestWithoutAccessTokenThenReturnBadRequest() throws Exception {
		this.mockMvc
				.perform(get("/employee/getEmployees").accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}
}
