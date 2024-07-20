package com.edzo.database.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.edzo.database.domains.dtos.BookDto;
import com.edzo.database.domains.entities.BookEntity;
import com.edzo.database.mappers.Mapper;
import com.edzo.database.services.BookService;

@RestController
public class BookController {

  @Autowired
  private BookService bookService;

  @Autowired
  private Mapper<BookEntity, BookDto> bookMapper;

  @PutMapping(path = "/books/{isbn}")
  public ResponseEntity<BookDto> createUpdateBook(@PathVariable("isbn") String isbn, @RequestBody BookDto book) {
    BookEntity bookEntity = bookMapper.mapFrom(book);
    boolean bookExists = bookService.isExists(isbn);

    BookEntity savedBook = bookService.createUpdateBook(isbn, bookEntity);
    BookDto savedBookDto = bookMapper.mapTo(savedBook);

    return new ResponseEntity<>(savedBookDto, bookExists ? HttpStatus.OK : HttpStatus.CREATED);

  }

  @PatchMapping(path = "/books/{isbn}")
  public ResponseEntity<BookDto> partialUpdateBook(@PathVariable("isbn") String isbn, @RequestBody BookDto book) {

    if (!bookService.isExists(isbn)) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    BookEntity bookEntity = bookMapper.mapFrom(book);
    BookEntity updatedBook = bookService.partialUpdate(isbn, bookEntity);
    return new ResponseEntity<>(bookMapper.mapTo(updatedBook), HttpStatus.OK);
  }

  @GetMapping(path = "/books")
  public Page<BookDto> listBooks(Pageable pageable) {
    Page<BookEntity> books = bookService.findAll(pageable);

    return books.map(bookMapper::mapTo);
  }

  @GetMapping(path = "/books/{isbn}")
  public ResponseEntity<BookDto> getBook(@PathVariable("isbn") String isbn) {
    Optional<BookEntity> foundBook = bookService.findByIsbn(isbn);

    return foundBook.map(bookEntity -> {
      BookDto bookDto = bookMapper.mapTo(bookEntity);
      return new ResponseEntity<>(bookDto, HttpStatus.OK);
    }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @DeleteMapping(path = "/books/{isbn}")
  public ResponseEntity<Void> deleteBook(@PathVariable("isbn") String isbn) {
    if (!bookService.isExists(isbn)) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    bookService.delete(isbn);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
