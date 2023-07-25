package com.web.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.web.entity.composite_key.OrderDetail;
import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;

@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"orderDate"})
@Table(name = "orders")
@EqualsAndHashCode(exclude = "user")
public class Order implements Serializable {

    @Id
    @Column(name = "id_order",unique = true)
    private Long idOrder;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "idUser", foreignKey = @ForeignKey(name = "Order_user_fk"))
    private User user;


    @Column(name = "total")
    private Double total;

    @Column(name = "order_date", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date orderDate;


    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails;

    @Column(name = "status")
    private String status;

}
