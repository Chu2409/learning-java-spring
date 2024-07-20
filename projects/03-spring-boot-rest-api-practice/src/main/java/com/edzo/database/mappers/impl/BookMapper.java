package com.edzo.database.mappers.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.edzo.database.domains.dtos.BookDto;
import com.edzo.database.domains.entities.BookEntity;
import com.edzo.database.mappers.Mapper;

@Component
public class BookMapper implements Mapper<BookEntity, BookDto> {

  @Autowired
  private ModelMapper modelMapper;

  @Override
  public BookDto mapTo(BookEntity book) {
    return modelMapper.map(book, BookDto.class);

  }

  @Override
  public BookEntity mapFrom(BookDto book) {
    return modelMapper.map(book, BookEntity.class);
  }

}
