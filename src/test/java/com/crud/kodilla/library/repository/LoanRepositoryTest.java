package com.crud.kodilla.library.repository;

import com.crud.kodilla.library.domain.Book;
import com.crud.kodilla.library.domain.BookCopy;
import com.crud.kodilla.library.domain.Loan;
import com.crud.kodilla.library.domain.Reader;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class LoanRepositoryTest {

    @Autowired
    BookRepository bookRepository;
    @Autowired
    BookCopyRepository bookCopyRepository;
    @Autowired
    ReaderRepository readerRepository;
    @Autowired
    LoanRepository loanRepository;

    @Test
    public void shouldSaveLoan() {

        // Given
        Reader reader = new Reader("Test", "Reader", new Date());
        Book book = new Book("Test Title", "Great Author", 2010);
        BookCopy copy1 = new BookCopy("in use");
        BookCopy copy2 = new BookCopy("lost");
        book.getBookCopies().add(copy1);
        book.getBookCopies().add(copy2);
        copy1.setBook(book);
        copy2.setBook(book);
        Loan loan = new Loan(new Date());
        copy1.getLoans().add(loan);
        loan.setBookCopy(copy1);
        reader.getLoans().add(loan);
        loan.setReader(reader);

        // When
        loanRepository.save(loan);
        Long id = loan.getId();
        Loan readLoan = loanRepository.findOne(id);

        // Then
        assertEquals(id, readLoan.getId());

        // CleanUp
        loanRepository.delete(id);
        bookCopyRepository.delete(copy1.getId());
        bookCopyRepository.delete(copy2.getId());
        bookRepository.delete(book.getId());
        readerRepository.delete(reader.getId());
    }
}