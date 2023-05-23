package com.web.controller;

import com.web.dto.UserDtoIn;
import com.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class UserController {
    @Autowired
    private UserService userService;


    @GetMapping("/user")
    public List<UserDtoIn> getUser(){
        return userService.getAllUser();
    }

    @GetMapping("/user/{idUser}")
    private ResponseEntity getUserById(@PathVariable("idUser") Long idUser){
         UserDtoIn userDtoIn = userService.getUser(idUser);
        if (userService.getUser(idUser) == null)
            return (ResponseEntity<String>) ResponseEntity.notFound();
        return ResponseEntity.ok(userDtoIn);
    }

    @PostMapping("/user")
    public UserDtoIn saveUser(@RequestBody UserDtoIn userDtoIn){
        userService.saveUser(userDtoIn);
        return userDtoIn;
    }

    @DeleteMapping("/user/{idUser}")
    public ResponseEntity<String> deleteUserById(@PathVariable("idUser") Long idUser){
        userService.deleteUserById(idUser);
        if (idUser == null)
            return (ResponseEntity<String>) ResponseEntity.notFound();
        return ResponseEntity.status(HttpStatus.OK).body("delete user successfully");
    }
}
