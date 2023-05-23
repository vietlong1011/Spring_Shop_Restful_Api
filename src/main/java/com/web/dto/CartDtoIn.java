package com.web.dto;

import com.web.entity.Cart;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class CartDtoIn {
    @NonNull
    private Cart cart;
}
