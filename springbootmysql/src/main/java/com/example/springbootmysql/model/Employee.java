package com.example.springbootmysql.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "employee")
@Data
@AllArgsConstructor
@NoArgsConstructor
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
