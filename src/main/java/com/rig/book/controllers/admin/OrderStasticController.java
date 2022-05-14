package com.rig.book.controllers.admin;

import com.rig.book.model.OrderListModel;
import com.rig.book.model.StasticModel;
import com.rig.book.service.StasticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@RestController
@Validated
public class OrderStasticController {

    @Autowired
    private StasticService stasticService;

    @RequestMapping(value = "/admin/order/states/{month}/{year}", method = RequestMethod.GET)
    public ResponseEntity<StasticModel> getMonthReport(@PathVariable("month") @Min(1) @Max(12) int month,
                                                             @PathVariable("year") @Min(1000) int year) {

        return new ResponseEntity<>(stasticService.getMonthReport(month,year), HttpStatus.ACCEPTED);
    }
}
