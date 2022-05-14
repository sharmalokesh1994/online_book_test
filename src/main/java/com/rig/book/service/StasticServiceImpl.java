package com.rig.book.service;

import com.rig.book.entity.OrderEntity;
import com.rig.book.model.StasticModel;
import com.rig.book.repos.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class StasticServiceImpl implements StasticService{

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public StasticModel getMonthReport(int month, int year) {

        String startDate = year +"-";
        if(month<10) {
            startDate = startDate+"0"+month+"-01";
        }
        String endDate;
        if( month==12 ) {
            endDate = (year+1)+"-"+"01"+"-00";
        } else if( month<9 ) {
            endDate = (year)+"-"+"0"+(month+1)+"-00";
        } else {
            endDate = (year)+"-"+"0"+(month+1)+"-00";
        }

        startDate = startDate + " 00:00:00.0";
        endDate = endDate + " 00:00:00.0";

        try {
            List<OrderEntity> orderEntityList =
                    orderRepository.getAllBetweenDates(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(startDate),
                    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(endDate));

            int totalSoldBookInMonth = 0;
            int totalEarnedAmount = 0;

            for( OrderEntity orderEntity : orderEntityList ) {
                totalEarnedAmount = totalEarnedAmount + orderEntity.getTotalPrice();
                totalSoldBookInMonth = totalSoldBookInMonth + orderEntity.getTotalCount();
            }

            return StasticModel.builder().month(month).year(year).totalBook(totalSoldBookInMonth)
                    .totalPrice(totalEarnedAmount).build();

        } catch (ParseException e) {
            //e.printStackTrace();
        }


        return null;
    }
}
