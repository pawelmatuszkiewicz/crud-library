package com.crud.kodilla.library.mapper;

import com.crud.kodilla.library.domain.Book;
import com.crud.kodilla.library.domain.BookCopy;
import com.crud.kodilla.library.domain.BookCopyDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class BookCopyMapper {

    public BookCopy mapToBookCopy(BookCopyDto bookCopyDto) {
        return new BookCopy(
                bookCopyDto.getId(),
                bookCopyDto.getStatus(),
                new Book(bookCopyDto.getBookId(),"","", 0, new ArrayList<>()),
                new ArrayList<>()
        );
    }

    public BookCopyDto mapToBookCopyDto(BookCopy bookCopy) {
        return new BookCopyDto(
                bookCopy.getId(),
                bookCopy.getStatus(),
                null
        );
    }
}
