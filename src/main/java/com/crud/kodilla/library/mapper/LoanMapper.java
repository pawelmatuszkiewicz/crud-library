package com.crud.kodilla.library.mapper;

import com.crud.kodilla.library.domain.BookCopy;
import com.crud.kodilla.library.domain.Loan;
import com.crud.kodilla.library.domain.LoanDto;
import com.crud.kodilla.library.domain.Reader;
import org.springframework.stereotype.Component;

@Component
public class LoanMapper {
    public Loan mapToLoan(LoanDto loanDto) {
        return new Loan(
                loanDto.getId(),
                new BookCopy(loanDto.getBookCopyId(), ""),
                new Reader(loanDto.getReaderId()),
                null
        );
    }

    public LoanDto mapToLoanDto(Loan loan) {
        return new LoanDto(
                loan.getId(),
                loan.getReader().getId(),
                loan.getBookCopy().getId(),
                loan.getBorrowed(),
                loan.getReturned()
        );
    }
}
