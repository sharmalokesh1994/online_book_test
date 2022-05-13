package com.rig.book.controllers.webModel;

import com.rig.book.model.BookModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookRequestModel {

    @NotEmpty
    private List<BookModel> bookModels;

}
