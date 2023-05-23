package com.web.service.impl;

import com.web.convert.UserConvert;
import com.web.dto.UserDtoIn;
import com.web.entity.User;
import com.web.repository.UserRepository;
import com.web.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserImpl implements UserService {
    @Autowired
    private UserConvert userConvert;
    @Autowired
    private UserRepository userRepository;

    // lay ra user theo idUser
    @Override
    public UserDtoIn getUser(Long idUser) {
        User user = userRepository.findById(idUser).get();
        UserDtoIn userDtoIn = userConvert.userToDto(user);
        return userDtoIn;
    }

    // lay ra toan bo danh sach User
    @Override
    public List<UserDtoIn> getAllUser() {
     List<User> userList =  userRepository.findAll(Sort.by("nameUser").ascending());
     List<UserDtoIn> userDtoInList = new ArrayList<>();
     for (User user : userList){
        UserDtoIn userDtoIn = new UserDtoIn();
          userDtoIn =   userConvert.userToDto(user);
       userDtoInList.add(userDtoIn);
     }
        return userDtoInList;
    }

    @Override
    public UserDtoIn saveUser(UserDtoIn userDtoIn) {
        User user = new User();
        user = userConvert.userToEntity(userDtoIn);
        user = userRepository.save(user);
        return userConvert.userToDto(user);
    }

    @Override
    public UserDtoIn deleteUserById(Long idUser) {
         User user = userRepository.findById(idUser).orElseThrow();
         if (user.getIdUser() == idUser) {
             userRepository.deleteById(user.getIdUser());
         }
        return userConvert.userToDto(user);
    }

//    @Override
//    public UserDtoIn updateUserById(UserDtoIn userDtoIn) {
//        User user = new User();
//        user.setIdUser(userDtoIn.getUser().getIdUser());
//        user = userConvert.userToEntity(userDtoIn);
//        user = userRepository.save(user);
//        return userConvert.userToDto(user);
//    }
}
