package com.web.service;

import com.web.dto.UserDtoIn;
import com.web.entity.User;


import java.util.List;

public interface UserService {
    UserDtoIn getUser(Long idUser);

    List<UserDtoIn> getAllUser();

    //CRUD
    UserDtoIn saveUser(UserDtoIn user);

    UserDtoIn deleteUserById(Long idUser);

    //UserDtoIn updateUserById(UserDtoIn in);






}
