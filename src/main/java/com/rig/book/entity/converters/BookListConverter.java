package com.rig.book.entity.converters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rig.book.model.OrderModel;

import javax.persistence.AttributeConverter;
import java.util.ArrayList;
import java.util.List;

public class BookListConverter implements AttributeConverter<List<OrderModel>,String> {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(List<OrderModel> bookModels) {

        String convertedValue = "";

        try {
            convertedValue = objectMapper.writeValueAsString(bookModels);
        } catch (JsonProcessingException e) {
            //e.printStackTrace();
        }
        return  convertedValue;
    }

    @Override
    public List<OrderModel> convertToEntityAttribute(String s) {
        List<OrderModel> bookModels = new ArrayList<>();
        try {
            bookModels = objectMapper.readValue(s,bookModels.getClass());
        } catch (JsonProcessingException e) {
            //e.printStackTrace();
        }

        return bookModels;
    }

}
