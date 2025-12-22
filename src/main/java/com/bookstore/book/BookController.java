package com.bookstore.book;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    @GetMapping
    public List<Book> getAll() {
        return bookService.findAll();
    }


    @GetMapping("/{id}")
    public Book getById(@PathVariable Long id) {
        return bookService.findById(id);
    }


    @GetMapping("/search")
    public List<Book> search(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) Long categoryId
    ) {
        return bookService.search(title, author, categoryId);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public Book create(@RequestBody BookRequest request) {
        return bookService.create(request);
    }
}



