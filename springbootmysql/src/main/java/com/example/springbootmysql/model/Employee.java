package com.example.springbootmysql.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity(name = "employee")
@Data
public class Employee {
	
	@Id
	@Column(name="id")
	int id;
	
	@Column(name="name")
	String name;
	
	@Column(name="age")
	int age;
	
	@Column(name="address")
	String address;
	
	

}
