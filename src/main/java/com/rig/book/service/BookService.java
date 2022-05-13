package com.rig.book.service;

import com.rig.book.model.BookListModel;
import com.rig.book.model.BookModel;

public interface BookService {

    void addBooks(BookListModel bookListModel);

    BookListModel getAllBooks(int pageNo);

    void addBooks(BookModel bookModel);

}
