package com.hexbaitan.chromic.users.dto;


import com.hexbaitan.chromic.users.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IWriteUserDtoMapper {

    WriteUserDto toDto(User user);
    User toEntity(WriteUserDto dto);
}
