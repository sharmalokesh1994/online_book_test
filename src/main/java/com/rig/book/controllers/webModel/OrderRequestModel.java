package com.rig.book.controllers.webModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class OrderRequestModel {

    private int bookId;

    @Min(value = 1, message = "please give valid quantity")
    private int quantity;

}
