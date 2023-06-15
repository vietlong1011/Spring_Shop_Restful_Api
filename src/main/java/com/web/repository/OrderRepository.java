package com.web.repository;

import com.web.entity.Order;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT  o FROM OrderDetail od JOIN od.items i JOIN od.order o WHERE o.idOrder = :orderId")
    List<Object[]> findOrderDetailByOrderId(@Param("orderId") Long orderId);

    @Query("select o from Order o where o.user.idUser = :idUser ")
    Order findOrderByIdUser(Long idUser);

    @Query("select o from Order o where o.idOrder = :idOrder ")
    Order findUserByIdOrder(Long idOrder);

}
