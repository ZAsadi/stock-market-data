package com.rbc.stockmarket.model;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;

@Data
@Accessors(chain = true)
public class Stock {

    @Id
    public String id;

    @NonNull
    @Setter(AccessLevel.NONE)
    private Integer quarter;

    @NonNull
    @Setter(AccessLevel.NONE)
    private String stock;

    @NonNull
    @Setter(AccessLevel.NONE)
    private String date;

    private String open;
    private String high;
    private String low;
    private String close;
    private Long volume;
    private Float percentChangePrice;
    private Float percentChangeVolumeOverLastWeek;
    private Long previousWeeksVolume;
    private String nextWeekOpen;
    private String nextWeekClose;
    private Float percentChangeNextWeeksPrice;
    private Integer daysToNextDividend;
    private Float percentReturnNextDividend;
}
