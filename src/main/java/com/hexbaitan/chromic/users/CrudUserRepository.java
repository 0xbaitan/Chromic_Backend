package com.hexbaitan.chromic.users;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CrudUserRepository extends CrudRepository<User, UUID> {


    Optional<User> findByEmailId(String emailId);



}
