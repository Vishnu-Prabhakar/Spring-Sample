package com.example.springbootmongo.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;

@Document("vehicle")
@Data
public class Vehicle {
	
	/*@Id
	String systemId;*/
	
	String name;
	String type;

}
