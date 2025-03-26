package com.room.authentication.repository;

import com.room.authentication.entity.postgres.Driver;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {
    Optional<Driver> findByDriverId(String driverId);
    boolean existsByDriverId(String driverId);
}
