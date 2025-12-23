package com.bookstore.book;

import com.bookstore.stock.StockUpdateRequest;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookService.findAll());
    }

    @GetMapping("/low-stock")
    public ResponseEntity<List<Book>> getLowStockBooks() {
        return ResponseEntity.ok(bookService.findLowStockBooks());
    }

    @PutMapping("/{id}/stock")
    public ResponseEntity<Book> updateBookStock(
            @PathVariable Long id,
            @RequestBody StockUpdateRequest request) {

        return ResponseEntity.ok(
                bookService.updateStock(id, request.getChange(), request.getReason())
        );
    }
}




