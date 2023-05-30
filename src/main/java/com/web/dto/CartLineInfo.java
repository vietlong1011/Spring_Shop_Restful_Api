package com.web.dto;

import com.web.dto.DtoIn.ItemsDtoIn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
// chua biet
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartLineInfo {
    private ItemsDtoIn itemsDtoIn;
    private int quantity;
}
