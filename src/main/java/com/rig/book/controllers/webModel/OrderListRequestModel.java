package com.rig.book.controllers.webModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderListRequestModel {

    @NotEmpty( message = "order list cannot be empty")
    private List<OrderRequestModel> orderList;

}
