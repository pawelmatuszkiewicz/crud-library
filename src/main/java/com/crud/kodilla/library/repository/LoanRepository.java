package com.crud.kodilla.library.repository;

import com.crud.kodilla.library.domain.Loan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LoanRepository extends CrudRepository<Loan, Long> {
    @Override
    List<Loan> findAll();

    @Override
    Loan findOne(Long id);

    @Override
    Loan save(Loan loan);

    @Override
    void delete(Long id);
}
