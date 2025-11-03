package com.hexbaitan.chromic.users;

import com.hexbaitan.chromic.users.dto.CreateUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
public class CrudUserController {


    private final CrudUserService crudUserService;

    @Autowired
    public CrudUserController(CrudUserService crudUserService) {
        this.crudUserService = crudUserService;
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable UUID id) {
        crudUserService.deleteUser(id);
    }


}
