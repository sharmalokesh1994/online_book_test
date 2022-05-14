package com.rig.book.controllers.customer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rig.book.controllers.webModel.OrderListRequestModel;
import com.rig.book.model.OrderListModel;
import com.rig.book.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @RequestMapping(value = "/customer/order", method = RequestMethod.POST)
    public ResponseEntity<OrderListModel> makeOrder(@Valid @RequestBody OrderListRequestModel orderList) {
        OrderListModel orderListModel = objectMapper.convertValue(orderList,OrderListModel.class);

        return new ResponseEntity<>(orderService.makeOrder(orderListModel),HttpStatus.CREATED);
    }

    @RequestMapping(value = "/customer/order/{id}", method = RequestMethod.GET)
    public ResponseEntity<OrderListModel> getOrder(@PathVariable long id) {
        return new ResponseEntity<>(orderService.getOrder(id),HttpStatus.OK);
    }

    @RequestMapping(value = "/admin/order/get/{startDate}/{endDate}", method = RequestMethod.GET)
    public ResponseEntity<List<OrderListModel>> getOrders(@PathVariable String startDate, @PathVariable String endDate) throws ParseException {
        startDate = startDate + " 00:00:00.0";
        endDate = endDate + " 00:00:00.0";
        return new ResponseEntity<>(orderService.getOrders(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(startDate),
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(endDate)),HttpStatus.OK);
    }

}
