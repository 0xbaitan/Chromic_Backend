package com.hexbaitan.chromic.users.dto;

import java.time.LocalDateTime;

public record CreateUserDto(String givenName, String surname, String emailId, String password, LocalDateTime dateOfBirth) {
}
