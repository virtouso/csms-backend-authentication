package com.room.authentication.dto.DriverInStation;

import com.fasterxml.jackson.annotation.JsonProperty;

public record DriverInStationAuthRequest(
        @JsonProperty("stationUuid") String StationUuid,
        @JsonProperty("driverIdentifier") DriverIdentifier driverIdentifier
) {

    public record DriverIdentifier(
            @JsonProperty("id") String id
    ) {
    }
}
