package com.rig.book.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rig.book.entity.BookEntity;
import com.rig.book.entity.OrderEntity;
import com.rig.book.entity.UserEntity;
import com.rig.book.exceptions.BadRequestException;
import com.rig.book.exceptions.OutOfStockException;
import com.rig.book.model.OrderListModel;
import com.rig.book.model.OrderModel;
import com.rig.book.repos.BookRepository;
import com.rig.book.repos.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private BookRepository bookRepository;

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public OrderListModel makeOrder(OrderListModel orderListModel) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = user.getUsername();
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(username);

        OrderEntity orderEntity = mapper.convertValue(orderListModel,OrderEntity.class);
        orderEntity.setUser(userEntity);

        int totalPrice = 0;

        for(OrderModel orderModel : orderEntity.getOrderList()) {
            Optional<BookEntity> bookEntityOptional = bookRepository.findById((long)orderModel.getBookId());

            if( bookEntityOptional.isEmpty() ) {
                throw new BadRequestException("PLease provide correct book id");
            }

            BookEntity bookEntity = bookEntityOptional.get();
            if( bookEntity.getInventory()< orderModel.getQuantity()) {
                throw new OutOfStockException(orderModel.getBookId() + " is out of stock ");
            }
            totalPrice = totalPrice + bookEntity.getPrice();
            bookEntity.setInventory( bookEntity.getInventory() - orderModel.getQuantity() );
            bookRepository.save(bookEntity);
        }
        orderEntity.setTotalPrice(totalPrice);
        orderRepository.save(orderEntity);
        orderListModel.setId(orderEntity.getId());
        orderListModel.setTotalPrice(totalPrice);
        return orderListModel;

    }

    @Override
    public OrderListModel getOrder(long id) {
        Optional<OrderEntity> optionalOrderEntity = orderRepository.findById(id);
        if( optionalOrderEntity.isEmpty() ) {
            return null;
        }

        OrderEntity orderEntity = optionalOrderEntity.get();

        return mapper.convertValue(orderEntity,OrderListModel.class);
    }


}
