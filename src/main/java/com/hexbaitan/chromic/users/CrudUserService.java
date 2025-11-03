package com.hexbaitan.chromic.users;

import com.hexbaitan.chromic.users.dto.ICreateUserDtoMapper;
import com.hexbaitan.chromic.users.dto.CreateUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CrudUserService {



    private final CrudUserRepository userRepository;

    private final ICreateUserDtoMapper dtoMapper;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public CrudUserService(CrudUserRepository userRepository, ICreateUserDtoMapper dtoMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.dtoMapper = dtoMapper;
        this.passwordEncoder = passwordEncoder;
    }
  

    public User createUser(CreateUserDto dto) {
        User user = this.dtoMapper.toEntity(dto, passwordEncoder);
        return this.userRepository.save(user);
    }

    public void deleteUser(UUID id) {
        this.userRepository.deleteById(id);
    }



}
