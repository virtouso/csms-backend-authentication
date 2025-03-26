package com.room.authentication.service;


import com.room.authentication.dto.MetaResult;
import com.room.authentication.dto.driver.CreateDriverRequest;
import com.room.authentication.dto.station.CreateStationRequest;
import com.room.authentication.entity.postgres.Driver;
import com.room.authentication.entity.postgres.Station;
import com.room.authentication.repository.DriverRepository;
import com.room.authentication.repository.StationRepository;

import com.room.authentication.util.MetaResultFactory;
import org.springframework.stereotype.Service;

@Service
public class InitializationService {

    private final StationRepository stationRepository;
    private final DriverRepository driverRepository;
    private final MetaResultFactory metaResultFactory;

    public InitializationService(DriverRepository driverRepository, StationRepository stationRepository, MetaResultFactory metaResultFactory) {
        this.driverRepository = driverRepository;
        this.stationRepository = stationRepository;
        this.metaResultFactory = metaResultFactory;
    }


    public MetaResult CreateStation(CreateStationRequest request) {

        var stationExist = stationRepository.existsByStationId(request.stationId());

        if (!stationExist) {
            return  metaResultFactory.create("driver with same id already exist", "duplicate_record", null);
        }

        stationRepository.save(new Station(request.stationId()));

        return metaResultFactory.create("created successfully", "ok", null);
    }


    public MetaResult CreateDriver(CreateDriverRequest request) {

        var driverExist = driverRepository.existsByDriverId(request.driverId());

        if (driverExist) {
            return  metaResultFactory.create("driver with same id already exist", "duplicate_record", null);
        }


        driverRepository.save(new Driver(request.driverId(), request.allowed()));
        return metaResultFactory.create("created successfully", "ok", null);
    }


}
