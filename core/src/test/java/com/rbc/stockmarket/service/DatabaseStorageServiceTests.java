package com.rbc.stockmarket.service;

import com.rbc.stockmarket.exception.storage.FileIsEmptyException;
import com.rbc.stockmarket.exception.storage.FileNotSupportedException;
import com.rbc.stockmarket.service.DatabaseStorageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class DatabaseStorageServiceTests {

    private DatabaseStorageService service;

    @BeforeEach
    void init() {
        service = new DatabaseStorageService();
    }

    @Test
    void emptyFileSaveNotPermitted() {
        assertThrows(FileIsEmptyException.class, () -> {
            service.store(new MockMultipartFile("empty_file.data",
                    "empty_file.data",
                    MediaType.TEXT_PLAIN_VALUE,
                    this.getClass().getResourceAsStream("/files/empty_file.data")));
        });
    }

    @Test
    void badFileSaveNotPermitted() {
        assertThrows(FileNotSupportedException.class, () -> {
            service.store(new MockMultipartFile("bad_file.names",
                    "bad_file.name",
                    MediaType.TEXT_PLAIN_VALUE,
                    this.getClass().getResourceAsStream("/files/bad_file.names")));
        });
    }
}
