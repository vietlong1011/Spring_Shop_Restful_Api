package com.web.service;

import com.web.dto.OrderDtoIn;


import java.util.List;
public interface OrderService {
    OrderDtoIn getOrder(Long idOrder);

    OrderDtoIn getOrderByIdUser(Long idUser);

    OrderDtoIn findUserByIdOrder(Long idOrder);


    List<OrderDtoIn> getAllOrder() ;

    OrderDtoIn saveOrder(OrderDtoIn orderDtoIn);

    OrderDtoIn deleteOrderById(Long idOrder);

    List<Object[]> findOrderDetailByOrderId(Long idOrder);

}
