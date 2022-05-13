package com.rig.book.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookModel {

    private long id;
    private String bookTitle;
    private String description;
    private int inventory;
    private int price;

}
