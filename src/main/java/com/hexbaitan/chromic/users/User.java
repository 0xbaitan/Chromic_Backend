package com.hexbaitan.chromic.users;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@Document(collection = "users")
public class User {

    @Id
    private UUID id = UUID.randomUUID();

    @Field()
    private String givenName;

    @Field()
    private String surname;

    @Field(targetType = FieldType.DATE_TIME)
    private LocalDateTime dateOfBirth;


}
