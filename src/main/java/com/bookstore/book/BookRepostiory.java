package com.bookstore.book;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByTitleContainingIgnoreCase(String title);

    List<Book> findByAuthorContainingIgnoreCase(String author);

    List<Book> findByCategoryContainingIgnoreCase(String category);

    Book findByIsbn(String isbn);
}
