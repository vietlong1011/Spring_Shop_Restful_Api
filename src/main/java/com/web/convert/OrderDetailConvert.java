package com.web.convert;

import com.web.dto.DtoIn.OrderDetailDtoIn;

import com.web.dto.DtoIn.OrderDetailRatingKeyDto;
import com.web.dto.DtoIn.UserDtoIn;
import com.web.entity.Items;
import com.web.entity.Order;
import com.web.entity.User;
import com.web.entity.composite_key.OrderDetail;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderDetailConvert {

    @Autowired
    private ModelMapper modelMapper;

    // chuyen du lieu tu entity sang dto
    public OrderDetailDtoIn userToDto(OrderDetail orderDetail) {
        OrderDetailDtoIn OrderDetailDtoIn = new OrderDetailDtoIn();
        OrderDetailDtoIn = modelMapper.map(orderDetail, OrderDetailDtoIn.class);
        OrderDetailDtoIn.setIdItems(orderDetail.getItems().getIdItems());
        OrderDetailDtoIn.setIdOrder(orderDetail.getOrder().getIdOrder());
        return OrderDetailDtoIn;
    }

    // chuyen tu dto sang entity
    public OrderDetail userToEntity(OrderDetailDtoIn OrderDetailDtoIn){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail = modelMapper.map(OrderDetailDtoIn, OrderDetail.class); //todo chuong trinh dead here
        return orderDetail;
    }

    // ham convert cac gia tri cua composite key

}
