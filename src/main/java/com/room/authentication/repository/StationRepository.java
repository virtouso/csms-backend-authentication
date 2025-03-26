package com.room.authentication.repository;

import com.room.authentication.entity.postgres.Station;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StationRepository  extends JpaRepository<Station, Long> {
    boolean existsByStationId( String stationId);
}
