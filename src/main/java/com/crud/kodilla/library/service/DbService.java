package com.crud.kodilla.library.service;

import com.crud.kodilla.library.domain.Book;
import com.crud.kodilla.library.domain.BookCopy;
import com.crud.kodilla.library.domain.BookCopyDto;
import com.crud.kodilla.library.domain.Reader;
import com.crud.kodilla.library.repository.BookCopyRepository;
import com.crud.kodilla.library.repository.BookRepository;
import com.crud.kodilla.library.repository.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DbService {
    @Autowired
    private ReaderRepository readerRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BookCopyRepository bookCopyRepository;

    public Reader getReader(Long id) {
        return readerRepository.findOne(id);
    }

    public Reader saveReader(Reader reader) {
        return readerRepository.save(reader);
    }

    public void deleteReader(Long id) {
        readerRepository.delete(id);
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public BookCopyDto saveBookCopy(BookCopyDto bookCopyDto) {
        Book book = bookRepository.findOne(bookCopyDto.getBookId());
        BookCopy bookCopy = new BookCopy(
                bookCopyDto.getId(),
                bookCopyDto.getStatus(),
                book,
                new ArrayList<>()
        );
        book.getBookCopies().add(bookCopy);

        bookCopy = bookCopyRepository.save(bookCopy);
        return new BookCopyDto(
                bookCopy.getId(),
                bookCopy.getStatus(),
                book.getId()
        );
    }

    public BookCopyDto updateBookCopy(BookCopyDto bookCopyDto) {
        BookCopy bookCopy = bookCopyRepository.findOne(bookCopyDto.getId());
        bookCopy.setStatus(bookCopyDto.getStatus());
        bookCopyRepository.save(bookCopy);

        return new BookCopyDto(
                bookCopy.getId(),
                bookCopy.getStatus(),
                bookCopy.getBook().getId()
        );
    }

    public List<BookCopy> getBookCopies(Long bookId) {
        return bookRepository.findOne(bookId).getBookCopies();
    }
}
