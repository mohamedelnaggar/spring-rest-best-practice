package com.silicon.library.repository;

import com.silicon.library.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

    Book findOneById(Long id);

}
