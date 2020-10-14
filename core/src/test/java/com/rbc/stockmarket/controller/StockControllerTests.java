package com.rbc.stockmarket.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rbc.stockmarket.dto.StockDto;
import com.rbc.stockmarket.repository.StockRepository;
import com.rbc.stockmarket.service.DatabaseStorageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class StockControllerTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private DatabaseStorageService databaseStorageService;

    private void prepareDataEntry() throws Exception {
        stockRepository.deleteAll();

        MockMultipartFile multipartFile = new MockMultipartFile("file",
                "dow_jones_index.data",
                MediaType.TEXT_PLAIN_VALUE,
                this.getClass().getResourceAsStream("/files/dow_jones_index.data"));

        databaseStorageService.store(multipartFile);
    }

    @Test
    void shouldFetchWork() throws Exception {
        this.prepareDataEntry();
        mvc.perform(get("/stock").param("stock", "AA"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldInsertWork() throws Exception {
        StockDto stockDto = new StockDto()
                .setStock("BB")
                .setQuarter(1)
                .setDate("1399/07/20");
        mvc.perform(post("/stock").contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(stockDto)))
                .andExpect(status().isOk());
    }
}
