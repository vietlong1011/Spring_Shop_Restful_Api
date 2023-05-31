package com.web.service.impl;

import com.web.service.PayService;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * tao mot cau truy van select ra view thanh toan -> hien thi so du cua khac hang
 **/
@Service
public class PayServiceImpl implements PayService {

    @Override
    public List<Object[]> getViewPay(Long idOrder) throws SQLException {
        List<Object[]> result;
        ConnectJDBC connectJDBC = new ConnectJDBC();
        Connection conn = connectJDBC.connect();
        PreparedStatement stm;
        String query = "SELECT id_user,id_order, pay_user, prince FROM web_ban_hang.pay_view WHERE id_order = ?";
        stm = conn.prepareStatement(query);
        stm.setString(1, String.valueOf(idOrder));
        ResultSet rs = stm.executeQuery();
        result = new ArrayList<>();
        while (rs.next()) {
            Object[] row = new Object[4];
            row[0] = "id_user : " + rs.getLong("id_user");
            row[1] = "id_order : " + rs.getLong("id_order");
            row[2] = "pay_user : " + rs.getDouble("pay_user");
            row[3] = "prince : " + rs.getDouble("prince");
            result.add(row);
        }
        stm.close();
        return result;

    }

}
