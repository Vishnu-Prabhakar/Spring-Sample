package com.example.springbootmongo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springbootmongo.entity.Vehicle;
import com.example.springbootmongo.service.VehicleService;

@RestController
@RequestMapping("/")
public class VehicleController {
	
	@Autowired
	VehicleService vehicleService;

	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Vehicle> getVehicles(){
		return vehicleService.getAllVehicle();
	}
}
