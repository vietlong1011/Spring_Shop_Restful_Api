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


    // chuyen du lieu tu entity sang dto
    public UserDtoIn userToDto(User user) {
        UserDtoIn userDtoIn = new UserDtoIn();
        userDtoIn = modelMapper.map(user, UserDtoIn.class);// error
        return userDtoIn;
    }

    // chuyen tu dto sang entity
    public User userToEntity(UserDtoIn userDtoIn){
        User user = new User();
        user = modelMapper.map(userDtoIn, User.class);
        return user;
    }
}
