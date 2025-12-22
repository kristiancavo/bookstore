package com.bookstore.book;

import com.bookstore.category.Category;
import com.bookstore.category.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;

    public BookService(BookRepository bookRepository,
                       CategoryRepository categoryRepository) {
        this.bookRepository = bookRepository;
        this.categoryRepository = categoryRepository;
    }


    public Book create(BookRequest request) {

        if (request.getCategoryId() == null) {
            throw new RuntimeException("Category ID is required");
        }

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Book book = new Book();
        book.setTitle(request.getTitle());
        book.setAuthor(request.getAuthor());
        book.setIsbn(request.getIsbn());
        book.setPublishedYear(request.getPublishedYear());
        book.setPrice(request.getPrice());
        book.setStockQuantity(request.getStockQuantity());
        book.setDescription(request.getDescription());
        book.setCategory(category);

        return bookRepository.save(book);
    }


    public List<Book> findAll() {
        return bookRepository.findAll();
    }


    public Book findById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
    }


    public List<Book> search(String title, String author, Long categoryId) {

        if (categoryId != null) {
            return bookRepository.findByCategoryId(categoryId);
        }

        if (title != null && author != null) {
            return bookRepository
                    .findByTitleContainingIgnoreCaseAndAuthorContainingIgnoreCase(
                            title, author);
        }

        if (title != null) {
            return bookRepository.findByTitleContainingIgnoreCase(title);
        }

        if (author != null) {
            return bookRepository.findByAuthorContainingIgnoreCase(author);
        }

        return bookRepository.findAll();
    }
}


