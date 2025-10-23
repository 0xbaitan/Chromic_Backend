package com.hexbaitan.chromic.users;

import com.hexbaitan.chromic.users.dto.IWriteUserDtoMapper;
import com.hexbaitan.chromic.users.dto.WriteUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CrudUserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IWriteUserDtoMapper dtoMapper;

    public List<User> getUsers() {
        return this.userRepository.findAll();
    }

    public User createUser(WriteUserDto dto) {
        User user = this.dtoMapper.toEntity(dto);
        return this.userRepository.save(user);
    }

    public void deleteUser(UUID id) {
        this.userRepository.deleteById(id);
    }



}
