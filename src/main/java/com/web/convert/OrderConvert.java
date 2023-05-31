package com.web.convert;

import com.web.dto.OrderDtoIn;
import com.web.entity.Order;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class OrderConvert {

    @Autowired
    private ModelMapper modelMapper;

    public OrderDtoIn orderToDto(Order order) {
        OrderDtoIn orderDtoIn;
        orderDtoIn = modelMapper.map(order, OrderDtoIn.class);
        orderDtoIn.setOderDate(order.getOrderDate());
        return orderDtoIn;
    }

    public Order orderToEntity(OrderDtoIn orderDtoIn) {
        Order order;
        order = modelMapper.map(orderDtoIn, Order.class);
        order.setOrderDate(new Date());
        return order;
    }

}
