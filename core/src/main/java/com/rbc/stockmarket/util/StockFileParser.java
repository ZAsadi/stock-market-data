package com.rbc.stockmarket.util;

import com.rbc.stockmarket.dto.StockDto;
import com.rbc.stockmarket.exception.common.ValidatorException;
import com.rbc.stockmarket.exception.storage.FileIsEmptyException;
import com.rbc.stockmarket.exception.storage.FileNotSupportedException;
import com.rbc.stockmarket.model.Stock;
import com.rbc.stockmarket.util.validator.Validator;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class StockFileParser {
    private static final String EXTENSION = "data";

    public static List<Stock> parse(MultipartFile file) {
        List<Stock> result = new ArrayList<>();
        if (file.isEmpty()) {
            throw new FileIsEmptyException("Empty files not allowed for uploading.");
        }
        if (file.getOriginalFilename() != null) {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            String extension = fileName.split("\\.")[1];
            if (extension != null && !extension.isEmpty() && extension.equals(EXTENSION)) {
                try {
                    InputStream inputStream = file.getInputStream();
                    new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8)).lines().forEach(new Consumer<String>() {
                        @Override
                        public void accept(String line) {
                            String[] values = line.split(",");
                            try {
                                result.add(StockMapper.toStock(buildStockDto(values)));
                            } catch (ValidatorException ignore) {
                                //ignore corrupted lines
                            }
                        }
                    });
                } catch (IOException e) {
                    throw new FileNotSupportedException(e.getMessage());
                }
            } else {
                throw new FileNotSupportedException("File with extension: '" + extension + "' not supported for uploading");
            }
        } else {
            throw new FileNotSupportedException("No file has been chosen for uploading.");
        }

        return result;
    }

    private static StockDto buildStockDto(String[] values) {
        try {
            StockDto stockDto = new StockDto();
            stockDto.setQuarter(Integer.valueOf(values[0]))
                    .setStock(values[1])
                    .setDate(values[2])
                    .setOpen(values[3].isEmpty() ? null : values[3])
                    .setHigh(values[4].isEmpty() ? null : values[4])
                    .setLow(values[5].isEmpty() ? null : values[5])
                    .setClose(values[6].isEmpty() ? null : values[6])
                    .setVolume(values[7].isEmpty() ? null : Long.valueOf(values[7]))
                    .setPercentChangePrice(values[8].isEmpty() ? null : Float.valueOf(values[8]))
                    .setPercentChangeVolumeOverLastWeek(values[9].isEmpty() ? null : Float.valueOf(values[9]))
                    .setPreviousWeeksVolume(values[10].isEmpty() ? null : Long.valueOf(values[10]))
                    .setNextWeekOpen(values[11].isEmpty() ? null : values[11])
                    .setNextWeekClose(values[12].isEmpty() ? null : values[12])
                    .setPercentChangeNextWeeksPrice(values[13].isEmpty() ? null : Float.valueOf(values[13]))
                    .setDaysToNextDividend(values[14].isEmpty() ? null : Integer.valueOf(values[14]))
                    .setPercentReturnNextDividend(values[15].isEmpty() ? null : Float.valueOf(values[15]));
            Validator.validateStockDto(stockDto);
            return stockDto;
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new ValidatorException("Some lines in files data is corrupted");
        }
    }
}
