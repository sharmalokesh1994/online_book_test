package com.rig.book.service;

import com.rig.book.model.OrderListModel;

public interface OrderService {

    OrderListModel makeOrder(OrderListModel orderListModel);

    OrderListModel getOrder(long id);

}
