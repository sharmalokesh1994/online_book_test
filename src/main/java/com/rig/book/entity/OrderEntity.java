package com.rig.book.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rig.book.entity.converters.BookListConverter;
import com.rig.book.model.BookModel;
import com.rig.book.model.OrderModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private UserEntity user;

    @Column
    private int totalPrice;

    @Column
    @Convert(converter = BookListConverter.class)
    private List<OrderModel> orderList;

    @Temporal(TemporalType.DATE)
    private Date orderDate;

    @Column
    private int totalCount;

}
