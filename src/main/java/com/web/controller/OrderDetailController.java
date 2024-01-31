package com.web.controller;

import java.util.*;

import com.web.dto.*;
import com.web.service.ItemsService;
import com.web.service.OrderDetailService;
import com.web.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class OrderDetailController {

    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    private ItemsService itemsService;

    @Autowired
    private OrderService orderService;


    @GetMapping("/detail")
    public List<OrderDetailDtoIn> getOderDetail() {
        return orderDetailService.getOderDetail();
    }

    @PostMapping("/add")
    public ResponseEntity add(@RequestBody OrderRequest orderRequest) {

        // create order
        OrderDtoIn dto = new OrderDtoIn();
        dto.setIdOrder(orderService.maxIdOrder() + 1);
        dto.setIdUser(orderRequest.getUser().getIdUser());
        dto.setStatus("chua thanh toan");
        dto.setTotal((orderDetailService.getPrinceByIdOrder(dto.getIdOrder())));
        orderService.saveOrder(dto);

        // tao orderDetail
        for (ItemsDtoIn i : orderRequest.itemsList) {
            orderDetailService.saveOrderDetail(i.getIdItems(), dto.getIdOrder(),i.getPrice(), i.getQuantity());
            // update items on DB after change
            ItemsDtoIn item = itemsService.getItems(i.getIdItems());
            item.setQuantity(item.getQuantity() - i.getQuantity());
            itemsService.saveItems(item);
        }
        dto.setTotal((orderDetailService.getPrinceByIdOrder(dto.getIdOrder())));
        orderService.saveOrder(dto);
        return new ResponseEntity<>("Hoa don dat hang cua ban", HttpStatus.OK);
    }

}



