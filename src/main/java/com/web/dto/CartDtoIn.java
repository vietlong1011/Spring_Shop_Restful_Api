package com.web.dto;

import com.web.entity.Cart;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class CartDtoIn {
//    @NonNull
//    private Cart cart;
    private Long idCart;
    private Long idUser;
    private Date oderDate;
    private Long total;
    private int quantityItems;
}
