package com.rbc.stockmarket.service;

import com.rbc.stockmarket.dto.StockDto;
import com.rbc.stockmarket.repository.StockRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class StockServiceTests {

    @Autowired
    private StockService stockService;
    @Autowired
    private DatabaseStorageService databaseStorageService;
    @Autowired
    private StockRepository stockRepository;

    void prepareDataEntry() throws Exception {
        stockRepository.deleteAll();

        MockMultipartFile multipartFile = new MockMultipartFile("file",
                "dow_jones_index.data",
                MediaType.TEXT_PLAIN_VALUE,
                this.getClass().getResourceAsStream("/files/dow_jones_index.data"));

        databaseStorageService.store(multipartFile);
    }

    @Test
    void shouldReturnAllStocks() throws Exception {
        this.prepareDataEntry();
        List<StockDto> stocksWhenNameIsNull = stockService.find(null);
        List<StockDto> stocksWhenNamesIsEmpty = stockService.find("");

        assertEquals(stocksWhenNameIsNull.size(), 8);
        assertEquals(stocksWhenNamesIsEmpty.size(), 8);
    }

    @Test
    void shouldReturnOneStock() throws Exception {
        this.prepareDataEntry();
        List<StockDto> stockDtos = stockService.find("CC");
        assertEquals(stockDtos.size(), 1);
    }

    @Test
    void shouldReturnNoneStock() throws Exception {
        this.prepareDataEntry();
        List<StockDto> stockDtos = stockService.find("NONE");
        assertEquals(stockDtos.size(), 0);
    }

    @Test
    void shouldInsertStockCorrectly() {
        StockDto stockDto = new StockDto()
                .setStock("THIS_IS_UNIQUE_STOCK")
                .setQuarter(1)
                .setDate("1399/07/20");

        stockService.insert(stockDto);
        StockDto addedStockDto = stockService.find("THIS_IS_UNIQUE_STOCK").get(0);
        assertEquals(stockDto, addedStockDto);
    }
}
