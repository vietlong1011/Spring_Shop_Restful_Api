package com.web.service;

import com.web.dto.OrderDetailDtoIn;

import java.util.List;

public interface OrderDetailService {

    List<OrderDetailDtoIn> getOderDetail();

    OrderDetailDtoIn saveOrderDetail(Long idItems, Long idOrder, Integer quantity);

    Long getPrinceByIdOrder(Long idOrder);

}
