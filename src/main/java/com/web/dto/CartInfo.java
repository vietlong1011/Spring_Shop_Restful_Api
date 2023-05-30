package com.web.dto;

import com.web.dto.DtoIn.UserDtoIn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartInfo {
    private Long orderNum;
    private UserDtoIn userDtoIn;
    private final List<CartLineInfo> cartLineInfoList = new ArrayList<>();
}
