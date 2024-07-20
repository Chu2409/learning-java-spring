package com.edzo.database.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.edzo.database.domains.dtos.AuthorDto;
import com.edzo.database.domains.entities.AuthorEntity;
import com.edzo.database.mappers.Mapper;
import com.edzo.database.services.AuthorService;

@RestController
public class AuthorController {

  @Autowired
  private AuthorService authorService;

  @Autowired
  private Mapper<AuthorEntity, AuthorDto> authorMapper;

  @PostMapping(path = "/authors")
  public ResponseEntity<AuthorDto> createAuthor(@RequestBody AuthorDto author) {
    AuthorEntity authorEntity = authorMapper.mapFrom(author);
    AuthorEntity savedAuthorEntity = authorService.save(authorEntity);

    return new ResponseEntity<>(authorMapper.mapTo(savedAuthorEntity), HttpStatus.CREATED);
  }

  @GetMapping(path = "/authors")
  public List<AuthorDto> getAuthors() {
    List<AuthorEntity> authors = authorService.findAll();

    return authors.stream().map(authorMapper::mapTo).collect(Collectors.toList());
  }

  @GetMapping(path = "/authors/{id}")
  public ResponseEntity<AuthorDto> getAuthor(@PathVariable("id") Long id) {
    Optional<AuthorEntity> authorFound = authorService.findById(id);

    return authorFound.map(authorEntity -> {
      AuthorDto authorDto = authorMapper.mapTo(authorEntity);
      return new ResponseEntity<>(authorDto, HttpStatus.OK);
    }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @PutMapping(path = "/authors/{id}")
  public ResponseEntity<AuthorDto> fullUpdateAuthor(@PathVariable("id") Long id, @RequestBody AuthorDto authorDto) {
    if (!authorService.isExists(id)) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    authorDto.setId(id);
    AuthorEntity authorEntity = authorMapper.mapFrom(authorDto);

    AuthorEntity authorEntityUpdated = authorService.save(authorEntity);

    return new ResponseEntity<>(authorMapper.mapTo(authorEntityUpdated), HttpStatus.OK);
  }

  @PatchMapping(path = "/authors/{id}")
  public ResponseEntity<AuthorDto> partialUpdate(@PathVariable("id") Long id, @RequestBody AuthorDto authorDto) {
    if (!authorService.isExists(id)) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    AuthorEntity authorEntity = authorMapper.mapFrom(authorDto);
    AuthorEntity updatedAuthor = authorService.partialUpdate(id, authorEntity);

    return new ResponseEntity<>(authorMapper.mapTo(updatedAuthor), HttpStatus.OK);
  }

  @DeleteMapping(path = "/authors/{id}")
  public ResponseEntity<Void> deleteAuthor(@PathVariable("id") Long id) {
    authorService.delete(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
