package com.crud.kodilla.library.mapper;

import com.crud.kodilla.library.domain.Book;
import com.crud.kodilla.library.domain.BookDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class BookMapper {

    public Book mapToBook(BookDto bookDto) {
        return new Book(
                bookDto.getId(),
                bookDto.getTitle(),
                bookDto.getAuthor(),
                bookDto.getYear(),
                new ArrayList<>()
        );
    }
}
