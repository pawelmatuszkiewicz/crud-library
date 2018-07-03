package com.crud.kodilla.library.service;

import com.crud.kodilla.library.domain.*;
import com.crud.kodilla.library.repository.BookCopyRepository;
import com.crud.kodilla.library.repository.BookRepository;
import com.crud.kodilla.library.repository.LoanRepository;
import com.crud.kodilla.library.repository.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DbService {
    @Autowired
    private ReaderRepository readerRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BookCopyRepository bookCopyRepository;
    @Autowired
    private LoanRepository loanRepository;

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

    public BookCopy saveBookCopy(BookCopy bookCopy) {
        Book book = bookRepository.findOne(bookCopy.getBook().getId());
        BookCopy bookCopyToSave = new BookCopy(
                bookCopy.getId(),
                bookCopy.getStatus(),
                book,
                new ArrayList<>()
        );
        book.getBookCopies().add(bookCopyToSave);

        return bookCopyRepository.save(bookCopyToSave);
    }

    public BookCopy updateBookCopy(BookCopy bookCopy) {
        BookCopy updatedBookCopy = bookCopyRepository.findOne(bookCopy.getId());
        updatedBookCopy.setStatus(bookCopy.getStatus());
        bookCopyRepository.save(updatedBookCopy);

        return updatedBookCopy;
    }

    public List<BookCopy> getBookCopies(Long bookId) {
        return bookRepository.findOne(bookId).getBookCopies();
    }

    public Loan saveLoan(Loan loan) {
        BookCopy bookCopy = bookCopyRepository.findOne(loan.getBookCopy().getId());
        Reader reader = readerRepository.findOne(loan.getReader().getId());

        Loan loanToBeSaved = new Loan(
                null,
                bookCopy,
                reader,
                new Date(),
                null
        );
        bookCopy.getLoans().add(loanToBeSaved);
        reader.getLoans().add(loanToBeSaved);

        return loanRepository.save(loanToBeSaved);
    }

    public Loan updateLoan(Loan loan) {
        return loanRepository.save(loan);
    }

    public Loan getLoan(Long id) {
        return loanRepository.findOne(id);
    }
}
