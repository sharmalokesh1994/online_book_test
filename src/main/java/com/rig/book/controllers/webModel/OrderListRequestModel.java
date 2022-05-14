package com.rig.book.controllers.webModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class OrderListRequestModel {

    @NotEmpty( message = "order list cannot be empty")
    @Valid
    private List<OrderRequestModel> orderList;

}
