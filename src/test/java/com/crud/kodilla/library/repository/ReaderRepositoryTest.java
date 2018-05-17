package com.crud.kodilla.library.repository;

import com.crud.kodilla.library.domain.Reader;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReaderRepositoryTest {

    @Autowired
    ReaderRepository readerRepository;

    @Test
    public void shouldSaveReader() {

        // Given
        Reader reader = new Reader("first_name", "last_name", new Date());

        // When
        readerRepository.save(reader);

        // Then
        Long id = reader.getId();
        Reader readReader = readerRepository.findOne(id);
        assertEquals(id, readReader.getId());

        //CleanUp
        readerRepository.delete(id);
    }
}