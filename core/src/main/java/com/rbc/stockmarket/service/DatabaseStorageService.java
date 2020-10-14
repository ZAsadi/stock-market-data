package com.rbc.stockmarket.service;

import com.rbc.stockmarket.exception.storage.FileIsEmptyException;
import com.rbc.stockmarket.exception.storage.FileNotSupportedException;
import com.rbc.stockmarket.model.Stock;
import com.rbc.stockmarket.repository.StockRepository;
import com.rbc.stockmarket.util.StockFileParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class DatabaseStorageService implements StorageService {

    @Autowired
    StockRepository stockRepository;

    @Override
    public void store(MultipartFile file) throws FileIsEmptyException, FileNotSupportedException {
        List<Stock> stocks = StockFileParser.parse(file);
        if (stocks.isEmpty()) {
            throw new FileIsEmptyException("Empty files not allowed for uploading.");
        } else {
            stockRepository.insert(stocks);
        }
    }
}
