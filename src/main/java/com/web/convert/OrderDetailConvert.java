package com.web.convert;

import com.web.dto.OrderDetailDtoIn;
import com.web.entity.composite_key.OrderDetail;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderDetailConvert {

    @Autowired
    private ModelMapper modelMapper;

    public OrderDetailDtoIn userToDto(OrderDetail orderDetail) {
        OrderDetailDtoIn orderDetailDtoIn ;
        orderDetailDtoIn = modelMapper.map(orderDetail, OrderDetailDtoIn.class);
        orderDetailDtoIn.setIdItems(orderDetail.getItems().getIdItems());
        orderDetailDtoIn.setIdOrder(orderDetail.getOrder().getIdOrder());
        return orderDetailDtoIn;
    }

    public OrderDetail userToEntity(OrderDetailDtoIn orderDetailDtoIn){
        OrderDetail orderDetail ;
        orderDetail = modelMapper.map(orderDetailDtoIn, OrderDetail.class);
        return orderDetail;
    }

}
