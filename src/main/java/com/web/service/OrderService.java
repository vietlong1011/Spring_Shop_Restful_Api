package com.web.service;

import com.web.dto.DtoIn.OrderDtoIn;


import java.util.List;
public interface OrderService {
    OrderDtoIn getOrder(Long idOrder);

    List<OrderDtoIn> getAllOrder() ;

    //CRUD
    OrderDtoIn saveOrder(OrderDtoIn OrderDtoIn);

    OrderDtoIn deleteOrderById(Long idOrder);

    List<Object[]> findOrderDetailByOrderId(Long idOrder);

}
