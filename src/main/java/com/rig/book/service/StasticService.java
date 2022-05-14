package com.rig.book.service;

import com.rig.book.model.StasticModel;

public interface StasticService {

    StasticModel getMonthReport(int year, int month);

}
