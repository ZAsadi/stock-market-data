package com.rbc.stockmarket.util;

import com.rbc.stockmarket.dto.StockDto;
import com.rbc.stockmarket.model.Stock;

public class StockMapper {

    public static Stock toStock(StockDto stockDto) {
        return new Stock(stockDto.getQuarter(), stockDto.getStock(), stockDto.getDate())
                .setOpen(stockDto.getOpen())
                .setHigh(stockDto.getHigh())
                .setLow(stockDto.getLow())
                .setClose(stockDto.getClose())
                .setVolume(stockDto.getVolume())
                .setPercentChangePrice(stockDto.getPercentChangePrice())
                .setPercentChangeVolumeOverLastWeek(stockDto.getPercentChangeVolumeOverLastWeek())
                .setPreviousWeeksVolume(stockDto.getPreviousWeeksVolume())
                .setNextWeekOpen(stockDto.getNextWeekOpen())
                .setNextWeekClose(stockDto.getNextWeekClose())
                .setPercentChangeNextWeeksPrice(stockDto.getPercentChangeNextWeeksPrice())
                .setDaysToNextDividend(stockDto.getDaysToNextDividend())
                .setPercentReturnNextDividend(stockDto.getPercentReturnNextDividend());
    }

    public static StockDto toStockDto(Stock stock) {
        return new StockDto()
                .setQuarter(stock.getQuarter())
                .setStock(stock.getStock())
                .setDate(stock.getDate())
                .setOpen(stock.getOpen())
                .setHigh(stock.getHigh())
                .setLow(stock.getLow())
                .setClose(stock.getClose())
                .setVolume(stock.getVolume())
                .setPercentChangePrice(stock.getPercentChangePrice())
                .setPercentChangeVolumeOverLastWeek(stock.getPercentChangeVolumeOverLastWeek())
                .setPreviousWeeksVolume(stock.getPreviousWeeksVolume())
                .setNextWeekOpen(stock.getNextWeekOpen())
                .setNextWeekClose(stock.getNextWeekClose())
                .setPercentChangeNextWeeksPrice(stock.getPercentChangeNextWeeksPrice())
                .setDaysToNextDividend(stock.getDaysToNextDividend())
                .setPercentReturnNextDividend(stock.getPercentReturnNextDividend());
    }
}
