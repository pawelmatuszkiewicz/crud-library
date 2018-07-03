package com.crud.kodilla.library.mapper;

import com.crud.kodilla.library.domain.Book;
import com.crud.kodilla.library.domain.BookCopy;
import com.crud.kodilla.library.domain.BookCopyDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class BookCopyMapper {

    public BookCopy mapToBookCopy(BookCopyDto bookCopyDto) {
        Book book = new Book();
        book.setId(bookCopyDto.getBookId());
        return new BookCopy(
                bookCopyDto.getId(),
                bookCopyDto.getStatus(),
                book,
                new ArrayList<>()
        );
    }

    public BookCopyDto mapToBookCopyDto(BookCopy bookCopy) {
        return new BookCopyDto(
                bookCopy.getId(),
                bookCopy.getStatus(),
                bookCopy.getBook().getId()
        );
    }
}
