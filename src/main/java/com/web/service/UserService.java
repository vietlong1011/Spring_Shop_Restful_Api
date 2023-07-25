package com.web.service;

import com.web.dto.UserDtoIn;

import java.util.List;

public interface UserService {
    UserDtoIn getUser(Long idUser);

    List<UserDtoIn> getAllUser();

    UserDtoIn saveUser(UserDtoIn user);

    List<Object[]> getUserDetailById(Long idUser);

}
