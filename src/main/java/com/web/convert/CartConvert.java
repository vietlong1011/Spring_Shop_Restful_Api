package com.web.convert;

import com.web.dto.CartDtoIn;
import com.web.entity.Cart;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CartConvert {
    @Autowired
    private ModelMapper modelMapper;

    // chuyen du lieu tu entity sang dto
    public CartDtoIn CartToDto(Cart Cart) {
        CartDtoIn CartDtoIn = new CartDtoIn();
        CartDtoIn = modelMapper.map(Cart, CartDtoIn.class);
        return CartDtoIn;
    }

    // chuyen tu dto sang entity
    public Cart CartToEntity(CartDtoIn CartDtoIn){
        Cart Cart = new Cart();
        Cart = modelMapper.map(CartDtoIn, Cart.class);
        return Cart;
    }
}
