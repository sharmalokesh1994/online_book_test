package com.rig.book.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rig.book.constatnts.Constants;
import com.rig.book.entity.BookEntity;
import com.rig.book.exceptions.BadRequestException;
import com.rig.book.model.BookListModel;
import com.rig.book.model.BookModel;
import com.rig.book.repos.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService{

    @Autowired
    private BookRepository bookRepository;

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public void addBooks(BookListModel bookListModel) {
        List<BookEntity> bookEntities = new ArrayList<>();

        for(BookModel bookModel : bookListModel.getBookModels()) {
            BookEntity bookEntity = mapper.convertValue(bookModel,BookEntity.class);
            bookEntities.add(bookEntity);
        }
        bookRepository.saveAll(bookEntities);
    }

    @Override
    public BookListModel getAllBooks(int pageNo) {

        Pageable pageable = PageRequest.of(pageNo, Constants.BOOK_PAGE_SIZE);
        Page<BookEntity> page = bookRepository.findAll(pageable);
        List<BookEntity> bookEntities = page.getContent();

        List<BookModel> bookModels = new ArrayList<>();

        for(BookEntity bookEntity : bookEntities) {
            BookModel bookModel = mapper.convertValue(bookEntity,BookModel.class);
            bookModels.add(bookModel);
        }
        return BookListModel.builder().bookModels(bookModels).build();
    }

    @Override
    public void addBooks(BookModel bookModel) {
        Optional<BookEntity> bookEntityOptional = bookRepository.findById(bookModel.getId());
        if( bookEntityOptional.isEmpty() ) {
            throw new BadRequestException("Please provide valid id");
        }

        BookEntity bookEntity = bookEntityOptional.get();
        bookEntity.setInventory(bookEntity.getInventory()+bookModel.getInventory());
        bookRepository.save(bookEntity);

    }

}
