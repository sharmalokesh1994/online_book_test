package com.rig.book.controllers.webModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookUpdateRequestModel {

    private int id;
    private String bookTitle;
    private String description;
    private int inventory;
    private int price;

}
