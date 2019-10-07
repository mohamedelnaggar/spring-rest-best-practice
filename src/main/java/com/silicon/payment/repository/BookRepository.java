package com.silicon.payment.repository;

import com.silicon.payment.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

    Book findOneById(Long id);

}
