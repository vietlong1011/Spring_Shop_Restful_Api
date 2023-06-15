package com.web.service.impl;

import com.web.convert.OrderDetailConvert;
import com.web.dto.OrderDetailDtoIn;
import com.web.dto.OrderDetailRatingKeyDto;
import com.web.entity.Items;
import com.web.entity.Order;
import com.web.entity.composite_key.OrderDetail;
import com.web.entity.composite_key.OrderDetailRatingKey;
import com.web.repository.ItemsRepository;
import com.web.repository.OrderDetailRepository;
import com.web.repository.OrderRepository;
import com.web.service.ItemsService;
import com.web.service.OrderDetailService;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderDetailImpl implements OrderDetailService {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderDetailConvert orderDetailConvert;

    @Autowired
    private ItemsRepository itemsRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ItemsService itemsService;

    @Override
    public List<OrderDetailDtoIn> getOderDetail() {
        List<OrderDetail> list = orderDetailRepository.findAll();
        List<OrderDetailDtoIn> orderDetailDtoIns = new ArrayList<>();
        for (OrderDetail orderDetail : list) {
            OrderDetailDtoIn dtoIn = orderDetailConvert.userToDto(orderDetail);
            orderDetailDtoIns.add(dtoIn);
        }
        return orderDetailDtoIns;
    }

    // chuc nang dat hang , luu OrderDetail vao DB
    @Override
    public OrderDetailDtoIn saveOrderDetail(Long idItems, Long idOrder, Integer quantity) {
        OrderDetailDtoIn orderDetailDtoIn = new OrderDetailDtoIn();
        orderDetailDtoIn.setIdItems(idItems);
        orderDetailDtoIn.setIdOrder(idOrder);
        // lay cac gia tri cho entity
        OrderDetail orderDetail;
        orderDetail = orderDetailConvert.userToEntity(orderDetailDtoIn);
        // gan gia tri Items
        Optional<Items> optionalItems = itemsRepository.findById(idItems);
        Items items = optionalItems.orElse(null);
        orderDetail.setItems(items);
        // gan gia tri Order
        Optional<Order> optionalOrder = orderRepository.findById(idOrder);
        Order order = optionalOrder.orElse(null);
        orderDetail.setOrder(order);
        // cau hinh cho id
        // co the bo orderDetailDtoIn (se la null cua composite key id nhung k anh huong den DB)
        OrderDetailRatingKeyDto key = new OrderDetailRatingKeyDto(idItems, idOrder);
        orderDetailDtoIn.setId(key);
        OrderDetailRatingKey ratingKey = new OrderDetailRatingKey();
        ratingKey.setIdOrder(idOrder);
        ratingKey.setIdItems(idItems);
        orderDetail.setId(ratingKey);
        // cap nhat set gia tri cho prince voi quantity
        orderDetail.setPrince((itemsService.getItems(idItems).getPrice() * quantity));
        orderDetail.setQuantity(quantity);
        orderDetailRepository.save(orderDetail);
        return orderDetailDtoIn;
    }

    @Override
    public Long getPrinceByIdOrder(Long idOrder) {
        Session session = entityManager.unwrap(Session.class);
        Long result = null;
        Transaction tr = session.beginTransaction();
        try {
            String query = "select sum(o.prince ) from OrderDetail o where o.order.id = :idOrder";
            result = (Long) session.createQuery(query)
                    .setParameter("idOrder", idOrder)
                    .getSingleResult();
            tr.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
        } finally {
            session.close();
        }
        return result;
    }

}
