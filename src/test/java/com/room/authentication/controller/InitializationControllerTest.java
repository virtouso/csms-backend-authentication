package com.room.authentication.controller;

import com.room.authentication.dto.MetaResult;
import com.room.authentication.dto.driver.CreateDriverRequest;
import com.room.authentication.dto.station.CreateStationRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class InitializationControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testCreateDriver() {
        // Arrange
        CreateDriverRequest request = new CreateDriverRequest("driver123", true);

        // Act
        ResponseEntity<MetaResult> response = restTemplate.postForEntity(
                "/init/create_driver",
                request,
                MetaResult.class
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void testCreateStation() {
        // Arrange
        CreateStationRequest request = new CreateStationRequest("station123");

        // Act
        ResponseEntity<MetaResult> response = restTemplate.postForEntity(
                "/init/create_station",
                request,
                MetaResult.class
        );

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }
}
