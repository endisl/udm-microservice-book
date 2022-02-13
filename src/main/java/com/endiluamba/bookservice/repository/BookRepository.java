package com.endiluamba.bookservice.repository;

import com.endiluamba.bookservice.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

}
