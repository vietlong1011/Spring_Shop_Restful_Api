package com.web.convert;

import com.web.dto.UserDtoIn;
import com.web.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserConvert {

    @Autowired
    private ModelMapper modelMapper;

    public UserDtoIn userToDto(User user) {
        UserDtoIn userDtoIn ;
        userDtoIn = modelMapper.map(user, UserDtoIn.class);
        return userDtoIn;
    }

    public User userToEntity(UserDtoIn userDtoIn){
        User user ;
        user = modelMapper.map(userDtoIn, User.class);
        return user;
    }

}
