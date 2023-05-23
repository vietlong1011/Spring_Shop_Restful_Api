package com.web.convert;

import com.web.dto.CartDtoIn;
import com.web.entity.Cart;
import com.web.entity.User;
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
    public Cart CartToEntity(CartDtoIn cartDtoIn){
//        Cart Cart = new Cart();
//        Cart = modelMapper.map(CartDtoIn, Cart.class);//error
//        return Cart;
        Cart cart = new Cart();
        cart.setIdCart(cartDtoIn.getIdCart());
        cart.setQuantityItems(cartDtoIn.getQuantityItems());
        cart.setTotal(cartDtoIn.getTotal());
        cart.setOrderDate(cartDtoIn.getOderDate());

        User user = new User();
        user.setIdUser(cartDtoIn.getIdUser());
        cart.setUser(user);

        return cart;
    }
}
