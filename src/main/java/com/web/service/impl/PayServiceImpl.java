package com.web.service.impl;

import com.web.repository.PayRepository;
import com.web.service.PayService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/** tao mot cau truy van select ra view thanh toan -> hien thi so du cua khac hang **/
@Service
public class PayServiceImpl implements PayService {

    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<Object[]> getViewPay(Long idOrder) throws SQLException {
        ConnectJDBC connectJDBC = new ConnectJDBC();
        Connection conn = connectJDBC.connect();
        PreparedStatement stm = null;

        String query = "SELECT id_user,id_order, pay_user, prince FROM web_ban_hang.pay_view WHERE id_order = ?";
        stm = conn.prepareStatement(query);
        stm.setString(1,String.valueOf(idOrder));

        ResultSet rs = stm.executeQuery();
            List<Object[]> result = new ArrayList<>();
            while (rs.next()) {
                Object[] row = new Object[4];
//                row[0] = rs.getLong("id_user");
//                row[1] = rs.getLong("id_order");
//                row[2] = rs.getDouble("pay_user");
//                row[3] = rs.getDouble("prince");
                row[0] = "id_user : " + rs.getLong("id_user");
                row[1] = "id_order : " + rs.getLong("id_order");
                row[2] = "pay_user : " + rs.getDouble("pay_user");
                row[3] = "prince : " + rs.getDouble("prince");
                result.add(row);
            }
            return result;
        }


// todo JPA
//    @Autowired
//    private PayRepository payRepository;
//    @Override
//    public List<Object[]> getViewPay() {
//        return payRepository.getPayViewData();
//    }
}
