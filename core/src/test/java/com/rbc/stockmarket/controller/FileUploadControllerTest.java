package com.rbc.stockmarket.controller;

import com.rbc.stockmarket.repository.StockRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class FileUploadControllerTest {


    @Autowired
    private MockMvc mvc;

    @Autowired
    private StockRepository stockRepository;

    @BeforeEach
    void init() {
        stockRepository.deleteAll();
    }

    @Test
    void shouldInsertUploadedFileToDatabase() throws Exception {
        MockMultipartFile multipartFile = new MockMultipartFile("file",
                "dow_jones_index.data",
                MediaType.TEXT_PLAIN_VALUE,
                this.getClass().getResourceAsStream("/files/dow_jones_index.data"));

        mvc.perform(multipart("/file/upload").file(multipartFile))
                .andExpect(status().isOk());
    }
}
