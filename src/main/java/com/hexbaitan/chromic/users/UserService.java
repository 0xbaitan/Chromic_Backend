package com.hexbaitan.chromic.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {


    @Autowired
    private UserRepository userRepository;



    public List<User> getUsers() {
        return this.userRepository.findAll();
    }


}
