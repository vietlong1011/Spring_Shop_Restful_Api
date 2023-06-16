package com.web.service.impl;

import com.web.convert.OrderConvert;
import com.web.dto.OrderDtoIn;
import com.web.entity.Order;
import com.web.repository.OrderRepository;
import com.web.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderImpl implements OrderService {

    @Autowired
    private OrderConvert orderConvert;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public OrderDtoIn getOrder(Long idOrder) {
        Optional<Order> optionalOrder = orderRepository.findById(idOrder);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            return orderConvert.orderToDto(order);
        } else {
            return null;
        }
    }


    @Override
    public List<OrderDtoIn> getAllOrder() {
        List<Order> orderList = orderRepository.findAll();
        List<OrderDtoIn> orderDtoInList = new ArrayList<>();
        for (Order order : orderList) {
            OrderDtoIn orderDtoIn;
            orderDtoIn = orderConvert.orderToDto(order);
            orderDtoInList.add(orderDtoIn);
        }
        return orderDtoInList;
    }


    @Override
    public OrderDtoIn saveOrder(OrderDtoIn orderDtoIn) {
        Order order;
        order = orderConvert.orderToEntity(orderDtoIn);
        order = orderRepository.save(order);
        return orderConvert.orderToDto(order);
    }

    @Override
    public OrderDtoIn deleteOrderById(Long idOrder) {
        Order order = orderRepository.findById(idOrder).orElseThrow();
        if (order.getIdOrder().equals(idOrder)) {
            orderRepository.delete(order);
        }
        return orderConvert.orderToDto(order);
    }

    @Override
    public List<Object[]> findOrderDetailByOrderId(Long idOrder) {
        return orderRepository.findOrderDetailByOrderId(idOrder);
    }

}
