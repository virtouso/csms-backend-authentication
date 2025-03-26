package com.room.authentication.dto.driver;

import jakarta.validation.constraints.Size;

public record CreateDriverRequest(
        @Size(min = 5, max = 15, message = "Username must be between 5 and 15 characters")  String driverId, boolean allowed) {
}
