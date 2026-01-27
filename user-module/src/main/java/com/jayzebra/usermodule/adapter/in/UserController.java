package com.jayzebra.usermodule.adapter.in;

import com.jayzebra.usermodule.domain.dto.UserDto;
import com.jayzebra.usermodule.domain.dto.UserListDto;
import com.jayzebra.usermodule.domain.port.in.UserUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.catalina.User;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/users")
@Tag(name ="Users")
public class UserController {

    private final UserUseCase userUseCase;

    public UserController(UserUseCase userUseCase) {
        this.userUseCase = userUseCase;
    }

    @GetMapping
    public ResponseEntity<UserListDto> listUsers() {
        List<UserDto> users = userUseCase.listUsers();
        return ResponseEntity.ok(new UserListDto(users));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable String userId) {
        return userUseCase.getUserById(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
