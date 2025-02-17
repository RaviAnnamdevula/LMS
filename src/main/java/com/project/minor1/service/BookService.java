package com.project.minor1.service;

import com.project.minor1.model.*;
import com.project.minor1.repository.AuthorRepository;
import com.project.minor1.repository.BookRepository;
import com.project.minor1.request.BookCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {


    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    public Book createBook(BookCreateRequest bookCreateRequest) {
        // check if author is coming to me from fe is already present in db or not
        // if not present then only I add into db

        //otherwise I will not add one more row inside my  table
        Author authorFromDb = authorRepository.findByEmail(bookCreateRequest.getAuthorEmail());
        if(authorFromDb == null){
            // create a row inside the author table also
            authorFromDb = authorRepository.save(bookCreateRequest.toAuthor());
        }
        // create a row inside my book;
        Book book = bookCreateRequest.toBook();
        book.setAuthor(authorFromDb);
        return bookRepository.save(book);
    }

    public List<Book> filter(FilterType filterBy, Operator operator, String value) {
        switch (operator){
            case EQUALS :
                switch (filterBy){
                    case BOOK_NO :
                        return bookRepository.findByBookNo(value);
                    case AUTHOR_NAME:
                        return bookRepository.findByAuthorName(value);
                    case COST:
                        return bookRepository.findByCost(Integer.valueOf(value));
                    case BOOK_TYPE:
                        return bookRepository.findByType(BookType.valueOf(value));
                }
            case LESS_THAN:
                switch (filterBy){
                    case COST :
                        return bookRepository.findByCostLessThan(Integer.valueOf(value));
                }
            default:
                return new ArrayList<>();
        }
    }

    public  void saveUpdate(Book book){
        bookRepository.save(book);
    }
}
