package com.javaspring.java_spring.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaspring.java_spring.model.Book;
import com.javaspring.java_spring.service.BookService;

@RestController
@RequestMapping("/api/books")
public class BookController {
  private final BookService bookService;

  public BookController(BookService bookService) {
    this.bookService = bookService;
  }

  // Create a new book
  @PostMapping
  public ResponseEntity<Book> createBook(@RequestBody Book book) {
    Book createdBook = bookService.createBook(book);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
  }

  // Get all books
  @GetMapping
  public ResponseEntity<List<Book>> getAllBooks() {
    List<Book> books = bookService.getAllBooks();
    return ResponseEntity.ok(books);
  }

  // Get book by ID
  @GetMapping("/{id}")
  public ResponseEntity<Book> getBookById(@PathVariable Long id) {
    Optional<Book> book = bookService.getBookById(id);
    return book.map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  // Update book
  @PutMapping("/{id}")
  public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book) {
    Optional<Book> updatedBook = bookService.updateBook(id, book);
    return updatedBook.map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  // Delete book
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
    boolean deleted = bookService.deleteBook(id);
    return deleted ? ResponseEntity.noContent().build()
        : ResponseEntity.notFound().build();
  }
}
