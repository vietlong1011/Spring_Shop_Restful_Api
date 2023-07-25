package com.web.service.impl;

import com.web.convert.UserConvert;
import com.web.dto.UserDtoIn;
import com.web.entity.Order;
import com.web.entity.User;
import com.web.repository.UserRepository;
import com.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserImpl implements UserService {

    @Autowired
    private UserConvert userConvert;

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDtoIn getUser(Long idUser) {
        Optional<User> optionalUser = userRepository.findById(idUser);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return userConvert.userToDto(user);
        } else {
            return null;
        }
    }

    @Override
    public List<UserDtoIn> getAllUser() {
        List<User> userList = userRepository.findAll(Sort.by("nameUser").ascending());
        List<UserDtoIn> userDtoInList = new ArrayList<>();
        for (User user : userList) {
            UserDtoIn userDtoIn = userConvert.userToDto(user);
            userDtoInList.add(userDtoIn);
        }
        return userDtoInList;
    }

    @Override
    public UserDtoIn saveUser(UserDtoIn userDtoIn) {
        User user = userConvert.userToEntity(userDtoIn);
        user = userRepository.save(user);
        return userConvert.userToDto(user);
    }


    @Override
    public List<Object[]> getUserDetailById(Long idUser) {
        Optional<User> optionalUser = userRepository.findById(idUser);
        User user = optionalUser.orElse(null);
        List<Object[]> list = new ArrayList<>();
        List<Object[]> orderDetails ;
        for (Order order : Objects.requireNonNull(user).getOrder()) {
            Long idOrder = order.getIdOrder();
            orderDetails = userRepository.findUserDetail(idOrder, idUser);
            list.addAll(orderDetails);
        }
        return list;
    }

}
