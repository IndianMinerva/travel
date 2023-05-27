package com.example.travel.repository;

import com.example.travel.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    Iterable<Vehicle> findByCustomerNull();
}
