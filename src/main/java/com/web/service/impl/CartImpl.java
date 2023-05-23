package com.web.service.impl;

import com.web.convert.CartConvert;
import com.web.convert.UserConvert;
import com.web.dto.CartDtoIn;
import com.web.dto.UserDtoIn;
import com.web.entity.Cart;
import com.web.entity.Items;
import com.web.entity.User;
import com.web.repository.CartRepository;
import com.web.repository.UserRepository;
import com.web.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartImpl implements CartService {


    @Autowired
    private CartConvert cartConvert;
    @Autowired
    private CartRepository cartRepository;


    // lay ra user theo idUser
    @Override
    public CartDtoIn getCart(Long idCart) {
        Cart cart = cartRepository.getById(idCart);
        CartDtoIn cartDtoIn = cartConvert.CartToDto(cart);
        return cartDtoIn;
    }

    // lay ra toan bo danh sach Cart
    @Override
    public List<CartDtoIn> getAllCart() {
        List<Cart> cartList =  cartRepository.findAll();
        List<CartDtoIn> cartDtoInList = new ArrayList<>();
        for (Cart cart : cartList){
            CartDtoIn cartDtoIn = new CartDtoIn();
            cartDtoIn =   cartConvert.CartToDto(cart);
            cartDtoInList.add(cartDtoIn);
        }
        return cartDtoInList;
    }

    @Override
    public CartDtoIn saveCart(CartDtoIn cartDtoIn) {
        Cart cart = new Cart();
        cart = cartConvert.CartToEntity(cartDtoIn);
        cart = cartRepository.save(cart);
        return cartConvert.CartToDto(cart);
    }

    @Override
    public CartDtoIn deleteCartById(Long idCart) {
        Cart cart = cartRepository.findById(idCart).orElseThrow();
//        if (cart.getIdCart() == idCart) {
//            cartRepository.delete(cart);
//        }
        cartRepository.delete(cart);
        return cartConvert.CartToDto(cart);
    }

//    @Override
//    public CartDtoIn updateCartById(CartDtoIn cartDtoIn) {
//        Cart cart = new Cart();
//        cart.setIdCart(cartDtoIn.getCart().getIdCart());
//        cart = cartConvert.CartToEntity(cartDtoIn);
//        cart = cartRepository.save(cart);
//        return cartConvert.CartToDto(cart);
//    }
}
