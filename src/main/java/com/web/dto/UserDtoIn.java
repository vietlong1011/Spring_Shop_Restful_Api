package com.web.dto;

import lombok.*;
import javax.validation.constraints.Email;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDtoIn {

    private Long idUser;

    private String country;

    @Email
    private String email;

    private Long money;

    private String nameUser;

    private Long phone;

}
