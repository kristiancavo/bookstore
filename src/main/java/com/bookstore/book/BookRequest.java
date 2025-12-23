package com.bookstore.book;

import com.bookstore.category.Category;

import java.math.BigDecimal;

public class BookRequest {

    private String title;
    private String author;
    private String isbn;
    private Integer publishedYear;
    private BigDecimal price;
    private Integer stockQuantity;
    private Integer minStockLevel;
    private String description;
    private Category category;

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public Integer getPublishedYear() {
        return publishedYear;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public Integer getMinStockLevel() {
        return minStockLevel;
    }

    public String getDescription() {
        return description;
    }

    public Category getCategory() {
        return category;
    }
}
