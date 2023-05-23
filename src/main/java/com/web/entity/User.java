package com.web.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
    @JsonManagedReference // annotation nay ngan chan van de "stackoverflow"
    private Cart cart;

}
