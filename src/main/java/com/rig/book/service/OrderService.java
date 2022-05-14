package com.rig.book.service;

import com.rig.book.model.OrderListModel;

import java.util.Date;
import java.util.List;

public interface OrderService {

    OrderListModel makeOrder(OrderListModel orderListModel);

    OrderListModel getOrder(long id);

    List<OrderListModel> getOrders( Date startDate, Date endDate );

}
