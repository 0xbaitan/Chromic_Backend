package com.hexbaitan.chromic.users.dto;


import com.hexbaitan.chromic.users.User;
import org.mapstruct.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING
)
public interface ICreateUserDtoMapper {

    CreateUserDto toDto(User user);

    @Mapping( target="password", qualifiedByName = "encodedPassword")
    User toEntity(CreateUserDto dto, @Context PasswordEncoder passwordEncoder);


    @Named("encodedPassword")
    default String encodedPassword(String rawPassword, @Context PasswordEncoder passwordEncoder) {
        return passwordEncoder.encode(rawPassword);
    }


}
