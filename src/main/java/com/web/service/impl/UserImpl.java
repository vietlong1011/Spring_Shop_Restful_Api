package com.web.service.impl;

import com.web.convert.UserConvert;
import com.web.dto.DtoIn.UserDtoIn;
import com.web.entity.User;
import com.web.repository.UserRepository;
import com.web.service.UserService;

import org.modelmapper.ModelMapper;
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
    @Autowired
    private ModelMapper modelMapper;

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

    @Override
    public List<Object[]> getUserDetailById(Long idUser) {
        User user = userRepository.findById(idUser).get();
        List<Object[]> list = new ArrayList<>();
        for (int i = 0 ; i < user.getOrder().size(); i++ ){
            list.addAll(userRepository.findUserDetail(user.getOrder().get(i).getIdOrder(), idUser));
        }
        // lay gia tri cua idOrder cua Order trong User
        return list;
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
