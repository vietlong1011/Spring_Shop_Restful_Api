package com.web.service.impl;

import com.web.entity.composite_key.OrderDetail;
import com.web.service.PayService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * tao mot cau truy van select ra view thanh toan -> hien thi so du cua khac hang
 **/
@Service
public class PayServiceImpl implements PayService {

    @PersistenceContext
    private EntityManager entityManager;

    // tao session để lấy thông tin của

    @Override
    public List<Object[]> getViewPay() throws SQLException {
        Session session = entityManager.unwrap(Session.class);
        session.beginTransaction();
        List<Object[]> result = new ArrayList<>();
        Transaction tr = session.beginTransaction();
        try {
            String query = "SELECT o.items.idItems, o.order.idOrder, o.prince, o.quantity, o.status FROM com.web.entity.composite_key.OrderDetail o";
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
            if (tr != null) {
                tr.rollback();
            }
        } return  result;
    }

//    String query = "SELECT od.idItems, od.idOrder, od.price, od.quantity, od.status FROM OrderDetail od";
//    TypedQuery<Object[]> typedQuery = entityManager.createQuery(query, Object[].class);
//    List<Object[]> rows = typedQuery.getResultList();
//    List<Object[]> result = new ArrayList<>();
//    for (Object[] row : rows) {
//        Object[] newRow = new Object[4];
//        newRow[0] = "id_items : " + row[0];
//        newRow[1] = "id_order : " + row[1];
//        newRow[2] = "price : " + row[2];
//        newRow[3] = "quantity : " + row[3];
//        newRow[4] = "status : " + row[4];
//        result.add(newRow);
//    }

//    @Override
//    public List<Object[]> getViewPay(Long idOrder) throws SQLException {
//        Session session = entityManager.unwrap(Session.class);
//        List<Object[]> result;
//        try {
//
//            session.beginTransaction();
//            session.createQuery("SELECT id_user,id_order, pay_user, prince FROM web_ban_hang.pay_view WHERE id_order = ?");
//            PreparedStatement stm = null;
//            stm.setString(1, String.valueOf(idOrder));
//            ResultSet rs = stm.executeQuery();
//            result = new ArrayList<>();
//            while (rs.next()) {
//                Object[] row = new Object[4];
//                row[0] = "id_user : " + rs.getLong("id_user");
//                row[1] = "id_order : " + rs.getLong("id_order");
//                row[2] = "pay_user : " + rs.getDouble("pay_user");
//                row[3] = "prince : " + rs.getDouble("prince");
//                result.add(row);
//            }
//            session.getTransaction().commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//            if (session != null) {
//                session.getTransaction().rollback();
//            }
//            throw e;
//        } finally {
//            session.flush();
//            session.close();
//        }
//        return result;
//    }
//        ConnectJDBC connectJDBC = new ConnectJDBC();
//        Connection conn = connectJDBC.connect();
//        PreparedStatement stm;
//        String query = "SELECT id_user,id_order, pay_user, prince FROM web_ban_hang.pay_view WHERE id_order = ?";
//        stm = conn.prepareStatement(query);
//        stm.setString(1, String.valueOf(idOrder));
//        ResultSet rs = stm.executeQuery();
//        result = new ArrayList<>();
//        while (rs.next()) {
//            Object[] row = new Object[4];
//            row[0] = "id_user : " + rs.getLong("id_user");
//            row[1] = "id_order : " + rs.getLong("id_order");
//            row[2] = "pay_user : " + rs.getDouble("pay_user");
//            row[3] = "prince : " + rs.getDouble("prince");
//            result.add(row);
//        }
//        stm.close();
//        return result;
        //   }

        // cách dùng entityManager nhưng đang bị lỗi Query query = entityManager.createQuery(sql);
//    @Override
//    @Transactional
//    public List<Object[]> getViewPay(Long idOrder) {
//        String sql = "SELECT p.id_user, p.id_order, p.pay_user, p.prince FROM web_ban_hang.pay_view p WHERE p.id_order = :idOrder";
//        Query query = entityManager.createQuery(sql);
//        query.setParameter("idOrder", idOrder);
//        List<Object[]> results = query.getResultList();
//        return results;
//    }

    }
