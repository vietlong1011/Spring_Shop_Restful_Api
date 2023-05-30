package com.web.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

//@Data
//@NoArgsConstructor
//@AllArgsConstructor
@Getter
@Setter
@Entity
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;

    @Column(name="nameUser", nullable = false)
    private String nameUser;

    @Column(name="country")
    private String country;

    @Column(name="phone", nullable = false)
    private Long phone;

    @Column(name="email")
    @NotBlank
    @Email
    @Size(min = 4, max = 50)
    private String email;

    @Column(name="money", nullable = false)
    private double money;

    @JsonManagedReference // annotation ngan chan stack overflow
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Order> order;

}
