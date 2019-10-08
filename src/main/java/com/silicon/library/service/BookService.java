package com.silicon.library.service;

import com.silicon.library.domain.Book;
import com.silicon.library.exception.SiliconResourceNotFoundException;
import com.silicon.library.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookDetails(Long bookId) {
        Book book = bookRepository.findOneById(bookId);
        return Optional.ofNullable(book).orElseThrow(() ->
                new SiliconResourceNotFoundException(SiliconResourceNotFoundException.Resource.BOOK, bookId));
    }

    public Book saveNewBook(Book book) {
        return bookRepository.save(book);
    }

    public Book updateBook(Long originalBookId, Book book) {
        return bookRepository.save(book);
    }

    public void deleteBook(Long bookId) {
        bookRepository.deleteById(bookId);
    }

}
