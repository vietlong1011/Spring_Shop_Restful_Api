package com.web.convert;

import com.web.dto.DtoIn.OrderDtoIn;
import com.web.entity.Order;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class OrderConvert {
    @Autowired
    private ModelMapper modelMapper;

    // chuyen du lieu tu entity sang dto
    public OrderDtoIn OrderToDto(Order Order) {
        OrderDtoIn OrderDtoIn = new OrderDtoIn();
        OrderDtoIn = modelMapper.map(Order, OrderDtoIn.class);
        OrderDtoIn.setOderDate(Order.getOrderDate());
        return OrderDtoIn;
    }

    // chuyen tu dto sang entity
    public Order OrderToEntity(OrderDtoIn OrderDtoIn) {
        Order Order = new Order();
        Order = modelMapper.map(OrderDtoIn, Order.class);
        Order.setOrderDate(new Date());
        return Order;
    }
}
