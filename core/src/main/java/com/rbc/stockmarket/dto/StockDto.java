package com.rbc.stockmarket.dto;

import com.rbc.stockmarket.util.validator.NullOrNotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Accessors(chain = true)
@EqualsAndHashCode
public class StockDto {

    private Integer quarter;

    @NotBlank(message = "stock field is mandatory")
    private String stock;

    @NotBlank(message = "date field is mandatory")
    private String date;

    @NullOrNotBlank
    private String open;

    @NullOrNotBlank
    private String high;

    @NullOrNotBlank
    private String low;

    @NullOrNotBlank
    private String close;

    private Long volume;
    private Float percentChangePrice;
    private Float percentChangeVolumeOverLastWeek;
    private Long previousWeeksVolume;

    @NullOrNotBlank
    private String nextWeekOpen;
    @NullOrNotBlank
    private String nextWeekClose;

    private Float percentChangeNextWeeksPrice;
    private Integer daysToNextDividend;
    private Float percentReturnNextDividend;
}
