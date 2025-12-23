package com.bookstore.stock;


import com.bookstore.book.Book;
import com.bookstore.book.BookService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stock")
public class StockController {

    private final BookService bookService;

    public StockController(BookService bookService) {
        this.bookService = bookService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{bookId}")
    public Book updateStock(
            @PathVariable Long bookId,
            @RequestParam Integer change,
            @RequestParam String reason
    ) {
        return bookService.updateStock(bookId, change, reason);
    }
}

