package com.web.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
// annotation cho phep cap nhat cac gia tri tai thoi diem duoc thay doi hoac update
@JsonIgnoreProperties(value = {"oderDate"},
        allowGetters = true)
@Table(name = "cart", uniqueConstraints = { @UniqueConstraint(columnNames = { "idUser" })})
// annotation thiet lap khoa ngoai cua table
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCart;

    // thiet lap relationship + tao 1 table clone voi khoa ngoai la idUser
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "idUser", referencedColumnName = "idUser",foreignKey = @ForeignKey(name = "cart_user_fk"))
   // @JsonBackReference // annotation nay ngan chan van de "stackoverflow"
    private User user;

    // y tuong tao mot list items trong cart
//    @OneToMany(targetEntity = Items.class, cascade = CascadeType.ALL,fetch = FetchType.LAZY)
//    @JoinColumn(name = "idItems_Items", referencedColumnName = "idItems")
//    private List<Items> itemsList;

    @Column(name = "quantityItems", nullable = false)
    private int quantityItems;

    @Column(name = "total", nullable = false)
    private double total;


    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date orderDate;

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
}
