package com.silicon.payment.resource;

import com.silicon.payment.controller.BookController;
import com.silicon.payment.domain.Book;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class BooksResource extends ResourceSupport {

    private final List<Book> data;

    public BooksResource(List<Book> books) {
        this.data = books;
        this.add(linkTo(methodOn(BookController.class).getAllBooks()).withSelfRel());
    }

    public List<Book> getData() {
        return data;
    }

}
