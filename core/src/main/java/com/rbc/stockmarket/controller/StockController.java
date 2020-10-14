package com.rbc.stockmarket.controller;

import com.rbc.stockmarket.dto.StockDto;
import com.rbc.stockmarket.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/stock")
@RestController
public class StockController {

    @Autowired
    private StockService stockService;

    @GetMapping("")
    public List<StockDto> search(@RequestParam(required = false) String stock) {
        return stockService.find(stock);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.OK)
    public void insert(@Valid @RequestBody StockDto stockDto) {
        stockService.insert(stockDto);
    }
}
