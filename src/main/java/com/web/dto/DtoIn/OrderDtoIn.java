package com.web.dto.DtoIn;

import com.web.entity.Order;
import com.web.entity.composite_key.OrderDetailRatingKey;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class OrderDtoIn {
//    @NonNull
//    private Order Order;

    private Long idOrder;
    private Long idUser;
    private Date oderDate;
    private Long total;
    private int quantityItems;
}
