package com.example.springbootmongo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springbootmongo.entity.Vehicle;
import com.example.springbootmongo.repository.VehicleRepository;

@Service
public class VehicleService {
	
	@Autowired
	VehicleRepository vehicleRepository;
	
	public List<Vehicle> getAllVehicle(){
		return vehicleRepository.findAll();
	}


}
