package com.rbc.stockmarket.repository;

import com.rbc.stockmarket.model.Stock;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface StockRepository extends MongoRepository<Stock, String> {

    @Query("{'stock': ?0}")
    public List<Stock> findAllByStock(String stock);

}
