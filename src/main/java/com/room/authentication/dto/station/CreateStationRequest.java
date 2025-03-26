package com.room.authentication.dto.station;

import jakarta.validation.constraints.Size;

public record CreateStationRequest(

        @Size(min = 5, max = 15, message = "Username must be between 5 and 15 characters")  String stationId) {
}
