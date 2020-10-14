package com.rbc.stockmarket.service;

import com.rbc.stockmarket.dto.StockDto;
import com.rbc.stockmarket.model.Stock;
import com.rbc.stockmarket.repository.StockRepository;
import com.rbc.stockmarket.util.StockMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StockService {

    @Autowired
    StockRepository stockRepository;

    public List<StockDto> find(@Nullable String stockName) {
        List<Stock> stocks;
        if (stockName == null || stockName.isEmpty()) {
            stocks = stockRepository.findAll();
        } else {
            stocks = stockRepository.findAllByStock(stockName);
        }
        List<StockDto> stockDtos = new ArrayList<>(stocks.size());
        for (Stock stock : stocks) {
            stockDtos.add(StockMapper.toStockDto(stock));
        }
        return stockDtos;
    }

    public void insert(StockDto stockDto) {
        Stock stock = StockMapper.toStock(stockDto);
        stockRepository.insert(stock);
    }
}
