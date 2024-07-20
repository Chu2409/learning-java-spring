package com.edzo.database.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edzo.database.domains.entities.AuthorEntity;
import com.edzo.database.repositories.AuthorRepository;
import com.edzo.database.services.AuthorService;

@Service
public class AuthorServiceImpl implements AuthorService {

  @Autowired
  private AuthorRepository authorRepository;

  @Override
  public AuthorEntity save(AuthorEntity author) {
    return authorRepository.save(author);
  }

  @Override
  public List<AuthorEntity> findAll() {
    return StreamSupport.stream(authorRepository.findAll().spliterator(), false)
        .collect(Collectors.toList());
  }

  @Override
  public Optional<AuthorEntity> findById(Long id) {
    return authorRepository.findById(id);
  }

  @Override
  public boolean isExists(Long id) {
    return authorRepository.existsById(id);
  }

  @Override
  public AuthorEntity partialUpdate(Long id, AuthorEntity authorEntity) {
    authorEntity.setId(id);

    return authorRepository.findById(id).map(existingAuthor -> {
      Optional.ofNullable(authorEntity.getName()).ifPresent(existingAuthor::setName);
      Optional.ofNullable(authorEntity.getAge()).ifPresent(existingAuthor::setAge);
      return authorRepository.save(existingAuthor);
    }).orElseThrow(() -> new RuntimeException("Author not found"));
  }

  @Override
  public void delete(Long id) {
    authorRepository.deleteById(id);

  }
}
