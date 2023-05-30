package com.web.service.impl;

import com.web.convert.OrderConvert;
import com.web.dto.DtoIn.OrderDtoIn;
import com.web.entity.Order;
import com.web.repository.OrderRepository;
import com.web.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderImpl implements OrderService {


    @Autowired
    private OrderConvert OrderConvert;
    @Autowired
    private OrderRepository OrderRepository;


    // lay ra user theo idUser
    @Override
    public OrderDtoIn getOrder(Long idOrder) {
        Order Order = OrderRepository.findById(idOrder).get();
        OrderDtoIn OrderDtoIn = OrderConvert.OrderToDto(Order);


        return OrderDtoIn;
    }

    // lay ra toan bo danh sach Order
    @Override
    public List<OrderDtoIn> getAllOrder() {
        List<Order> OrderList =  OrderRepository.findAll();
        List<OrderDtoIn> OrderDtoInList = new ArrayList<>();
        for (Order Order : OrderList){
            OrderDtoIn OrderDtoIn = new OrderDtoIn();
            OrderDtoIn =   OrderConvert.OrderToDto(Order);
            OrderDtoInList.add(OrderDtoIn);
        }
        return OrderDtoInList;
    }

    @Override
    public OrderDtoIn saveOrder(OrderDtoIn OrderDtoIn) {
        Order Order = new Order();
        Order = OrderConvert.OrderToEntity(OrderDtoIn);
        Order = OrderRepository.save(Order);
        return OrderConvert.OrderToDto(Order);
    }

    @Override
    public OrderDtoIn deleteOrderById(Long idOrder) {
        Order Order = OrderRepository.findById(idOrder).orElseThrow();
      if (Order.getIdOrder() == idOrder) {
          System.out.println("idOrder : " + Order.getIdOrder());
            OrderRepository.delete(Order);
        }
        return OrderConvert.OrderToDto(Order);
    }

    @Override
    public List<Object[]> findOrderDetailByOrderId(Long idOrder) {
        return OrderRepository.findOrderDetailByOrderId(idOrder);
    }

//    @Override
//    public OrderDtoIn updateOrderById(OrderDtoIn OrderDtoIn) {
//        Order Order = new Order();
//        Order.setIdOrder(OrderDtoIn.getOrder().getIdOrder());
//        Order = OrderConvert.OrderToEntity(OrderDtoIn);
//        Order = OrderRepository.save(Order);
//        return OrderConvert.OrderToDto(Order);
//    }
}
