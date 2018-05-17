package com.crud.kodilla.library.mapper;

import com.crud.kodilla.library.domain.Reader;
import com.crud.kodilla.library.domain.ReaderDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;

@Component
public class ReaderMapper {
    public Reader mapToReader(ReaderDto readerDto) {
        return new Reader(
                readerDto.getId(),
                readerDto.getFirstName(),
                readerDto.getLastName(),
                new Date(),
                new ArrayList<>()
        );
    }
}
