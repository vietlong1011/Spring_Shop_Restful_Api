package com.web.service;

import java.sql.SQLException;
import java.util.List;

public interface PayService {
    List<Object[]> getViewPay(Long idOrder) throws SQLException;
}
