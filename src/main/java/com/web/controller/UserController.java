package com.web.controller;

import com.web.dto.UserDtoIn;
import com.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Map;
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

    @PostMapping("/users")
    public UserDtoIn saveUser(@Valid @RequestBody UserDtoIn userDtoIn) {
        userService.saveUser(userDtoIn);
        return userDtoIn;
    }


    @PutMapping("users/{id}")
    public ResponseEntity<?> updateUserById(@PathVariable("id") Long id, @RequestBody Map<String, Double> requestBody) {
        Double money = requestBody.get("money");
        UserDtoIn dto= userService.getUser(id);
        if (dto == null) {
            return new ResponseEntity<>("No Value",HttpStatus.NOT_FOUND);
        }
        dto.setMoney(money);
        userService.updateUser(dto);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable("id") Long idUser) {
        userService.deleteUserById(idUser);
        if (idUser == null)
            return (ResponseEntity<?>) ResponseEntity.notFound();
        return ResponseEntity.status(HttpStatus.OK).body("delete user successfully");
    }

    @GetMapping("/user/{id}")
    public List<Object[]> getUserDetailById(@PathVariable("id") Long idUser) {
        return userService.getUserDetailById(idUser);
    }


}
