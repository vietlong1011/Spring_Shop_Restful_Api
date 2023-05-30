package com.web.controller;

import com.web.convert.OrderDetailConvert;
import com.web.dto.DtoIn.OrderDetailDtoIn;
import com.web.dto.DtoIn.OrderDetailRatingKeyDto;
import com.web.dto.DtoIn.OrderDtoIn;
import com.web.dto.DtoIn.UserDtoIn;
import com.web.entity.Items;
import com.web.entity.Order;
import com.web.entity.User;
import com.web.entity.composite_key.OrderDetail;
import com.web.entity.composite_key.OrderDetailRatingKey;
import com.web.repository.ItemsRepository;
import com.web.repository.OrderDetailRepository;
import com.web.repository.OrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderDetailController {
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private OrderDetailConvert orderDetailConvert;
    @Autowired
    private ItemsRepository ItemsRepository;
    @Autowired
    private com.web.repository.OrderRepository OrderRepository;
    @GetMapping("/Detail")
    public List<OrderDetailDtoIn> getOderDetail() {
        List<OrderDetail> list = orderDetailRepository.findAll();
        List<OrderDetailDtoIn> orderDetailDtoIns = new ArrayList<>();
        for (OrderDetail orderDetail : list) {
            //  OrderDetailRatingKeyDto orderDetailRatingKeyDto = new OrderDetailRatingKeyDto();
            OrderDetailDtoIn OrderDetailDtoIn = new OrderDetailDtoIn();
            OrderDetailDtoIn = modelMapper.map(orderDetail, OrderDetailDtoIn.class);
            OrderDetailDtoIn.setIdItems(orderDetail.getItems().getIdItems());
            OrderDetailDtoIn.setIdOrder(orderDetail.getOrder().getIdOrder());
            orderDetailDtoIns.add(OrderDetailDtoIn);
        }
        return orderDetailDtoIns;
    }

    /** y tuong : tao ra mot obj cho client nhap thong tin
     * OrderDetail se duoc mapping va set cac gia tri vi no co 2 khoa ngoai duoc 2 table khac tham chieu
     * Cau hinh de lay gia tri cua composite key id **/
    @PostMapping("/Detail")
    public OrderDetailDtoIn saveOrderDetail(@RequestParam Long idItems, @RequestParam Long idOrder) {
        OrderDetailDtoIn orderDetailDtoIn = new OrderDetailDtoIn();
        orderDetailDtoIn.setIdItems(idItems);
        orderDetailDtoIn.setIdOrder(idOrder);
        // lay cac gia tri cho entity
        OrderDetail orderDetail = new OrderDetail();
        orderDetail = orderDetailConvert.userToEntity(orderDetailDtoIn);
        // gan gia tri Items
        Items items = ItemsRepository.findById(idItems).get();
        orderDetail.setItems(items);
        // gan gia tri Order
        Order order = OrderRepository.findById(idOrder).get();
        orderDetail.setOrder(order);
        // cau hinh cho id
        // co the bo orderDetailDtoIn (se la null cua composite key id nhung k anh huong den DB)
        OrderDetailRatingKeyDto key = new OrderDetailRatingKeyDto(idItems,idOrder);
        orderDetailDtoIn.setId(key);
        OrderDetailRatingKey ratingKey = new OrderDetailRatingKey();
        ratingKey.setIdOrder(idOrder);
        ratingKey.setIdItems(idItems);
        orderDetail.setId(ratingKey);
        orderDetailRepository.save(orderDetail);
        return orderDetailDtoIn;
        //todo truyen lenh lay gia tri prince voi quantity
    }

    @GetMapping("/Detail/{idOrder}")
    public OrderDetail findOrderDetailByIdOrder(@PathVariable Long idOrder){
      OrderDetail orderDetail =  orderDetailRepository.findById(idOrder).get();//todo error
       return orderDetail;
    }

}
