package com.edzo.postrgres_connection.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.edzo.postrgres_connection.domain.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, String> {

}
