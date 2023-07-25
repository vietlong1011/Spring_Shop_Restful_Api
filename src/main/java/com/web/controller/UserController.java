package com.web.controller;

import com.web.dto.UserDtoIn;
import com.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<UserDtoIn> getUser() {
        return userService.getAllUser();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") Long idUser) {
        try {
            UserDtoIn userDtoIn = userService.getUser(idUser);
            return ResponseEntity.ok(userDtoIn);
        } catch (NoSuchElementException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No value present");
        }
    }


    @GetMapping("/user/{id}")
    public List<Object[]> getUserDetailById(@PathVariable("id") Long idUser) {
        return userService.getUserDetailById(idUser);
    }


}
