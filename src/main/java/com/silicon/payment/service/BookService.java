package com.silicon.payment.service;

import com.silicon.payment.domain.Book;
import com.silicon.payment.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    public Book getBookDetails(Long bookId){
        return bookRepository.findOneById(bookId);
    }

    public Book saveNewBook(Book book){
        return bookRepository.save(book);
    }

    public Book updateBook(Long originalBookId, Book book){
        return bookRepository.save(book);
    }

    public void deleteBook(Long bookId){
        bookRepository.deleteById(bookId);
    }

}
