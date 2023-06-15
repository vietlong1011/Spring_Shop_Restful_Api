package com.web.service.impl;

import com.web.service.PayService;
import jakarta.persistence.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class PayServiceImpl implements PayService {

    @PersistenceContext
    private EntityManager entityManager;

    // tao session để lấy thông tin của

    @Override
    public List<Object[]> getViewPay() {
        Session session = entityManager.unwrap(Session.class);
        List<Object[]> result = new ArrayList<>();
        Transaction tr = session.beginTransaction();
        try {
            String query = "SELECT o.items.idItems, o.order.idOrder, o.prince, o.quantity FROM com.web.entity.composite_key.OrderDetail o";
            List<Object[]> rows = session.createQuery(query, Object[].class).getResultList();
            for (Object[] row : rows) {
                Object[] newRow = new Object[5];
                newRow[0] = "id_items: " + row[0];
                newRow[1] = "id_order: " + row[1];
                newRow[2] = "price: " + row[2];
                newRow[3] = "quantity: " + row[3];
                newRow[4] = "status: " + row[4];
                result.add(newRow);
            }
            tr.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
            session.beginTransaction().rollback();
        } finally {
            session.close();
        }
        return result;
    }

}
