package com.hexbaitan.chromic.users.dto;


import com.hexbaitan.chromic.users.User;
import org.mapstruct.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING
)
public interface ICreateUserDtoMapper {

    CreateUserDto toDto(User user);

    @Mapping( target="password", qualifiedByName = "EncodedPassword")
    User toEntity(CreateUserDto dto, @Context PasswordEncoder passwordEncoder);


    @Named("EncodedPassword")
    default String encodePassword(String rawPassword, @Context PasswordEncoder passwordEncoder) {
        return passwordEncoder.encode(rawPassword);
    }


}
