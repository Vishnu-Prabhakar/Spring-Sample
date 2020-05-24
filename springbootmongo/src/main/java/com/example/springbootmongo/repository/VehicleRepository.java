package com.example.springbootmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.springbootmongo.entity.Vehicle;

@Repository
public interface VehicleRepository extends MongoRepository<Vehicle, String> {

}
