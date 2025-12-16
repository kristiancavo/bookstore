package com.bookstore.book;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    public Book getById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
    }

    public Book create(BookRequest request) {
        Book book = new Book();
        applyRequestToBook(request, book);
        return bookRepository.save(book);
    }

    public Book update(Long id, BookRequest request) {
        Book book = getById(id);
        applyRequestToBook(request, book);
        return bookRepository.save(book);
    }

    public void delete(Long id) {
        Book book = getById(id);
        bookRepository.delete(book);
    }

    public List<Book> search(String title, String author, String category) {
        if (title != null && !title.isBlank()) {
            return bookRepository.findByTitleContainingIgnoreCase(title);
        }
        if (author != null && !author.isBlank()) {
            return bookRepository.findByAuthorContainingIgnoreCase(author);
        }
        if (category != null && !category.isBlank()) {
            return bookRepository.findByCategoryContainingIgnoreCase(category);
        }
        return bookRepository.findAll();
    }

    private void applyRequestToBook(BookRequest request, Book book) {
        book.setTitle(request.getTitle());
        book.setAuthor(request.getAuthor());
        book.setIsbn(request.getIsbn());
        book.setCategory(request.getCategory());
        book.setPublishedYear(request.getPublishedYear());
        book.setPrice(request.getPrice());
        book.setStockQuantity(request.getStockQuantity());
        book.setDescription(request.getDescription());
    }
}
