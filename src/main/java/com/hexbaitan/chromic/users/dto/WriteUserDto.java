package com.hexbaitan.chromic.users.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record WriteUserDto(String givenName, String surname, LocalDateTime dateOfBirth) {
}
