package com.web.dto.DtoIn;

import com.web.entity.User;
import lombok.*;

import javax.validation.constraints.Email;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDtoIn {

   // private User user;
    private Long idUser;
    private String country;
    @Email
    private String email;
    private Long money;
    private String nameUser;
    private Long phone;
}
