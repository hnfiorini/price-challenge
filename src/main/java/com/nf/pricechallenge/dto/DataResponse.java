package com.nf.pricechallenge.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DataResponse {
    private Long productId;

    private Long brandId;

    private BigDecimal price;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private Integer priceList;

    private String currency;

    private Integer priority;
}
