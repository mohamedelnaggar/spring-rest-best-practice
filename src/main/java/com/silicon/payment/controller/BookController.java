package com.silicon.payment.controller;

import com.silicon.payment.domain.Book;
import com.silicon.payment.resource.BookResource;
import com.silicon.payment.resource.BooksResource;
import com.silicon.payment.service.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@Api(tags = {"Book EndPoints"})
public class BookController {

    private final Logger logger = LoggerFactory.getLogger(BookController.class);

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @ApiOperation(value = "Returns a list of all books")
    @GetMapping("/books")
    public ResponseEntity<BooksResource> getAllBooks() {
        logger.info("REST request to get all books");
        List<Book> books = bookService.getAllBooks();
        return ResponseEntity.ok(new BooksResource(books));
    }

    @ApiOperation(value = "Returns the details of a given book by id")
    @GetMapping("/books/{id}")
    public ResponseEntity<BookResource> getBookDetails(@PathVariable("id") Long id) {
        logger.info("REST request to get book details with id {}", id);
        Book book = bookService.getBookDetails(id);
        return ResponseEntity.ok(new BookResource(book));
    }

    @ApiOperation(value = "Create a new book")
    @PostMapping("/books")
    public ResponseEntity<BookResource> saveBook(@Valid @RequestBody Book book) {
        logger.info("REST request to save book with body {}", book);
        Book savedBook = bookService.saveNewBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(new BookResource(savedBook));
    }

    @ApiOperation(value = "Updating an existing book")
    @PutMapping("/books/{id}")
    public ResponseEntity<BookResource> updateBook(@PathVariable("id") Long id, @Valid @RequestBody Book book) {
        logger.info("REST request to update book with id {}, and body {}", id, book);
        Book updatedBook = bookService.updateBook(id, book);
        return ResponseEntity.ok(new BookResource(updatedBook));
    }

    @ApiOperation(value = "Delete a given book by id")
    @DeleteMapping("/books/{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable("id") Long id) {
        logger.info("REST request to delete book with id {}", id);
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "This is a hidden service", hidden = true)
    @DeleteMapping("/books/cache")
    public ResponseEntity clearCache(){
        return ResponseEntity.ok("Hidden service called");
    }

}
