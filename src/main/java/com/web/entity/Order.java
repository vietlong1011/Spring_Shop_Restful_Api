package com.web.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.web.entity.composite_key.OrderDetail;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
// annotation cho phep cap nhat cac gia tri tai thoi diem duoc thay doi hoac update
@JsonIgnoreProperties(value = {"orderDate"},
        allowGetters = false)
@Table(name = "Orders")
//, uniqueConstraints = {@UniqueConstraint(columnNames = {"idUser"})})
// annotation thiet lap khoa ngoai cua table
@ToString(exclude = {"user"})
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOrder;

    // thiet lap relationship + tao 1 table clone voi khoa ngoai la idUser
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "idUser", foreignKey = @ForeignKey(name = "Order_user_fk"))
    @JsonBackReference // annotation nay ngan chan van de "stackoverflow"
    private User user;

    @Column(name = "quantityItems", nullable = true)
    private int quantityItems;

    @Column(name = "total", nullable = true)
    private double total;


    @Column(name = "order_date", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date orderDate;

    // y tuong tao mot list items trong Order
//    @OneToMany(targetEntity = Items.class, cascade = CascadeType.ALL,fetch = FetchType.LAZY)
//    @JoinColumn(name = "idItems_Items", referencedColumnName = "idItems")
//    private List<Items> itemsList;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<OrderDetail> orderDetails;

}
