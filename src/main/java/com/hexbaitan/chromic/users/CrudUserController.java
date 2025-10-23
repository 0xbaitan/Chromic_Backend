package com.hexbaitan.chromic.users;

import com.hexbaitan.chromic.users.dto.IWriteUserDtoMapper;
import com.hexbaitan.chromic.users.dto.WriteUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
public class CrudUserController {

    @Autowired
    private CrudUserService crudUserService;



    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok().body(crudUserService.getUsers());
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody WriteUserDto dto) {
      return ResponseEntity.ok().body(crudUserService.createUser(dto));
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable UUID id) {
        crudUserService.deleteUser(id);
    }


}
