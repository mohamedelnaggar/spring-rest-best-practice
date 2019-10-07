package com.silicon.payment.resource;

import com.silicon.payment.controller.BookController;
import com.silicon.payment.domain.Book;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class BookResource extends ResourceSupport {

    private final Book book;

    public BookResource(Book book) {
        Long id = book.getId();
        this.book = book;
        this.add(linkTo(methodOn(BookController.class, id).getBookDetails(id)).withSelfRel());
    }

    public Book getBook() {
        return book;
    }

}
