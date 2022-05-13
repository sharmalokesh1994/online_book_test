package com.rig.book.controllers.admin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rig.book.controllers.webModel.BookRequestModel;
import com.rig.book.controllers.webModel.BookUpdateRequestModel;
import com.rig.book.exceptions.BadRequestException;
import com.rig.book.model.BookListModel;
import com.rig.book.model.BookModel;
import com.rig.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    ObjectMapper objectMapper = new ObjectMapper();

    @RequestMapping(value = "/admin/book/add", method = RequestMethod.POST)
    public ResponseEntity<Void> addBookList(@Valid @RequestBody BookRequestModel bookRequestModel) {
        BookListModel bookListModel = objectMapper.convertValue(bookRequestModel,BookListModel.class);
        bookService.addBooks(bookListModel);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/all/book/get/{pageNo}", method = RequestMethod.GET)
    public ResponseEntity<BookListModel> getBookList(@PathVariable int pageNo) {
        if( pageNo<0 ) {
            throw new BadRequestException("pageNo can not be less then 0");
        }
        BookListModel bookListModel = bookService.getAllBooks(pageNo);
        return new ResponseEntity<>(bookListModel,HttpStatus.CREATED);
    }

    @RequestMapping(value = "/admin/book/update", method = RequestMethod.POST)
    public ResponseEntity<Void> updateInventory(@Valid @RequestBody BookUpdateRequestModel bookUpdateRequestModel) {
        BookModel bookModel = objectMapper.convertValue(bookUpdateRequestModel, BookModel.class);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
