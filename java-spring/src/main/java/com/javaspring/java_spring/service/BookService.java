package com.javaspring.java_spring.service;

import com.javaspring.java_spring.model.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class BookService {
    private final List<Book> books = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    // Create
    public Book createBook(Book book) {
        book.setId(idGenerator.getAndIncrement());
        books.add(book);
        return book;
    }

    // Read All
    public List<Book> getAllBooks() {
        return new ArrayList<>(books);
    }

    // Read by ID
    public Optional<Book> getBookById(Long id) {
        return books.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst();
    }

    // Update
    public Optional<Book> updateBook(Long id, Book updatedBook) {
        return books.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst()
                .map(book -> {
                    book.setTitle(updatedBook.getTitle());
                    book.setAuthor(updatedBook.getAuthor());
                    book.setIsbn(updatedBook.getIsbn());
                    book.setPublicationYear(updatedBook.getPublicationYear());
                    return book;
                });
    }

    // Delete
    public boolean deleteBook(Long id) {
        return books.removeIf(book -> book.getId().equals(id));
    }
}

