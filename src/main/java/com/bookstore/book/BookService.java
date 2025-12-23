package com.bookstore.book;



import com.bookstore.stock.StockLog;
import com.bookstore.stock.StockLogRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final StockLogRepository stockLogRepository;

    public BookService(BookRepository bookRepository, StockLogRepository stockLogRepository) {
        this.bookRepository = bookRepository;
        this.stockLogRepository = stockLogRepository;
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public List<Book> findLowStockBooks() {
        return bookRepository.findAll()
                .stream()
                .filter(book -> book.getStockQuantity() <= book.getMinStockLevel())
                .toList();
    }

    @Transactional
    public Book updateStock(Long bookId, int change, String reason) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        int newStock = book.getStockQuantity() + change;

        if (newStock < 0) {
            throw new RuntimeException("Not enough stock available");
        }

        book.setStockQuantity(newStock);

        StockLog log = new StockLog();
        log.setBook(book);
        log.setChangeAmount(change);
        log.setReason(reason);
        log.setTimestamp(LocalDateTime.now());

        stockLogRepository.save(log);

        return bookRepository.save(book);
    }
}