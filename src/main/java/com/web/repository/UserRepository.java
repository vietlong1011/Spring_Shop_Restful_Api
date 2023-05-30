package com.web.repository;

import com.web.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    @Query("SELECT u  "
            + "FROM OrderDetail od  JOIN  od.items i  JOIN  "
            + "od.order o  JOIN  o.user u "
            + "WHERE o.idOrder = :idOrder And u.idUser = :idUser")
    List<Object[]> findUserDetail(@Param("idOrder") Long idOrder,@Param("idUser") Long idUser);
}

