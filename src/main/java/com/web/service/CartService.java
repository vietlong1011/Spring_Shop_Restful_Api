package com.web.service;

import com.web.dto.CartDtoIn;


import java.util.List;

public interface CartService {
    CartDtoIn getCart(Long idCart);

    List<CartDtoIn> getAllCart() ;

    //CRUD
    CartDtoIn saveCart(CartDtoIn cartDtoIn);

    CartDtoIn deleteCartById(Long idCart);

   // CartDtoIn updateCartById(CartDtoIn in);
}
