package com.room.authentication.controller;


import com.room.authentication.dto.MetaResult;
import com.room.authentication.dto.driver.CreateDriverRequest;
import com.room.authentication.dto.station.CreateStationRequest;
import com.room.authentication.service.InitializationService;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/init")
public class InitializationController {

    private final InitializationService initializationService;


    public InitializationController(InitializationService initService) {
        initializationService = initService;
    }


    @PostMapping("/create_driver")
    public ResponseEntity<MetaResult> CreateDriver(@Valid @RequestBody CreateDriverRequest request) {
        return new ResponseEntity<>(initializationService.CreateDriver(request), HttpStatus.OK);
    }

    @PostMapping("create_station")
    public ResponseEntity<MetaResult> CreateStation(@Valid @RequestBody CreateStationRequest request) {
        return new ResponseEntity<>(initializationService.CreateStation(request), HttpStatus.OK);
    }


}
