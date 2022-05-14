package com.rig.book.controllers;

import com.rig.book.controllers.customer.OrderController;
import com.rig.book.model.OrderListModel;
import com.rig.book.service.OrderService;
import com.rig.book.service.OrderServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class OrderControllerTest {

    @InjectMocks
    private OrderController orderController;

    @Mock
    private OrderService orderService = new OrderServiceImpl();

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getOrderTest() {
        int id = 1;
        OrderListModel orderListModel = new OrderListModel();
        Mockito.when(orderService.getOrder(id)).thenReturn(orderListModel);
        ResponseEntity<OrderListModel> responseEntity = orderController.getOrder(id);
        Assertions.assertTrue(responseEntity.getStatusCode().value() == HttpStatus.OK.value());
    }

}
