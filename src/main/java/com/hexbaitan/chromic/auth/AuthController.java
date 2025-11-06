package com.hexbaitan.chromic.auth;

import com.hexbaitan.chromic.auth.dto.LoginRequestDto;
import com.hexbaitan.chromic.users.CrudUserService;
import com.hexbaitan.chromic.users.User;
import com.hexbaitan.chromic.users.dto.CreateUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final CrudUserService crudUserService;
    private final AuthService authService;

    @Autowired
    public AuthController(CrudUserService crudUserService, AuthService authService) {
        this.crudUserService = crudUserService;
        this.authService = authService;
    }


    @GetMapping("/")
    public String defaultPath() {
        return "Hello World";
    }

    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody CreateUserDto dto) {
        return ResponseEntity.ok().body(crudUserService.createUser(dto));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDto dto) {
        return ResponseEntity.ok().body(authService.login(dto));
    }
}
