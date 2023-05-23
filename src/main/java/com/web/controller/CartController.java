package com.web.controller;

import com.web.dto.CartDtoIn;
import com.web.dto.UserDtoIn;
import com.web.repository.CartRepository;
import com.web.service.CartService;
import com.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api")
public class CartController {
    @Autowired
    private CartService cartService;

    @Autowired
    private CartRepository cartRepository;

    @GetMapping("/cart")
    public List<CartDtoIn> getCart(){
        return cartService.getAllCart();
    }

    @GetMapping("/cart/{idCart}")
    private ResponseEntity getCartById(@PathVariable("idCart") Long idCart){
        CartDtoIn cartDtoIn = cartService.getCart(idCart);
        if (cartDtoIn.getIdCart() == null)
            return (ResponseEntity<String>) ResponseEntity.notFound();
        return ResponseEntity.ok(cartDtoIn);
    }

    @PostMapping("/cart")
    public CartDtoIn saveCart(@RequestBody CartDtoIn cartDtoIn){
        cartService.saveCart(cartDtoIn);
        return cartDtoIn;
    }

    @DeleteMapping("/cart/{idCart}")
    public ResponseEntity<String> deleteCartById(@PathVariable("idCart") Long idCart){
        cartService.deleteCartById(idCart);
         // error
        if (idCart == null)
            return (ResponseEntity<String>) ResponseEntity.notFound();
        return ResponseEntity.status(HttpStatus.OK).body("delete cart successfully");
    }
}
/ error delete