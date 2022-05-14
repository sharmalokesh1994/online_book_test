package com.rig.book.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StasticModel {

    private int month;
    private int year;
    private int totalBook;
    private int totalPrice;

}
