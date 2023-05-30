package com.web.service;

import com.web.dto.DtoIn.UserDtoIn;


import java.util.List;

public interface UserService {
    UserDtoIn getUser(Long idUser);

    List<UserDtoIn> getAllUser();

    //CRUD
    UserDtoIn saveUser(UserDtoIn user);

    UserDtoIn deleteUserById(Long idUser);

    List<Object[]> getUserDetailById(Long idUser);

    //UserDtoIn updateUserById(UserDtoIn in);






}
