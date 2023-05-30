package com.web.controller;

import com.web.convert.UserConvert;
import com.web.dto.DtoIn.UserDto;
import com.web.dto.DtoIn.UserDtoIn;
import com.web.entity.Order;
import com.web.entity.User;
import com.web.repository.UserRepository;
import com.web.service.UserService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("api")
public class UserController {
    @Autowired
    private UserService userService;



    @GetMapping("/User")
    public List<UserDtoIn> getUser() {
        return userService.getAllUser();
    }

    @GetMapping("/User/{idUser}")
    private ResponseEntity getUserById(@PathVariable("idUser") Long idUser) {
        try {
            UserDtoIn userDtoIn = userService.getUser(idUser);
            return ResponseEntity.ok(userDtoIn);
        } catch (NoSuchElementException ex) {
            return (ResponseEntity<String>) ResponseEntity.status(HttpStatus.NOT_FOUND).body("No value present");
        }
    }

    @PostMapping("/User")
    public UserDtoIn saveUser(@Valid @RequestBody UserDtoIn userDtoIn) {
        userService.saveUser(userDtoIn);
        return userDtoIn;
    }

    @DeleteMapping("/User/{idUser}")
    public ResponseEntity<String> deleteUserById(@PathVariable("idUser") Long idUser) {
        userService.deleteUserById(idUser);
        if (idUser == null)
            return (ResponseEntity<String>) ResponseEntity.notFound();
        return ResponseEntity.status(HttpStatus.OK).body("delete user successfully");
    }

    @GetMapping("/User_Detail/{idUser}")
    private List<Object[]> getUserDetailById(@PathVariable("idUser") Long idUser) {
        List<Object[]> list = new ArrayList<>();
        list = userService.getUserDetailById(idUser);
        return list;

    }
}
