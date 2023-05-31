package com.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDtoIn {

    private OrderDetailRatingKeyDto id;

    private Long idItems;

    private Long idOrder;

    private Long prince;

    private int quantity;

}
