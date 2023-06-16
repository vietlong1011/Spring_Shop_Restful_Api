package com.web.service.items_v1;

import com.web.convert.ItemsConvert;
import com.web.entity.Items;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/** Class nay phuc vu cho viec tao ra 1 history table , chua thuc hien**/
@Service
public class Audit {

    @Autowired
    private ItemsConvert itemsConvert;

    @PersistenceContext
    private EntityManager entityManager;
    public Boolean checkAuditTableExist(Long idItems) {
        String sql = "SELECT EXISTS (SELECT 1 FROM web_ban_hang.items_aud WHERE id_items = ?)";
        TypedQuery<Boolean> query = (TypedQuery<Boolean>) entityManager.createNativeQuery(sql, Boolean.class);
        query.setParameter(1, idItems);
        Boolean exists = query.getSingleResult();
        return exists != null && exists;
    }

    public List<Object[]> getItemsAudit(Long idItems) {
        Session session = entityManager.unwrap(Session.class);
        List<Object[]> result = new ArrayList<>();
        Transaction tr = session.beginTransaction();
        try {
            String query = "SELECT id_items, made, name_items, quantity, price, create_date FROM web_ban_hang.items_aud WHERE id_items = :idItems";
            List rows = session.createNativeQuery(query)
                    .setParameter("idItems", idItems)
                    .getResultList();
            result.addAll(rows);
            tr.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
        } finally {
            session.close();
        }
        return (List<Object[]>) itemsConvert.itemsToDto((Items) result);
    }
}
