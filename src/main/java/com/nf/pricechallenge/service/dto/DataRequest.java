package com.nf.pricechallenge.service.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
public class DataRequest {

    private LocalDateTime dateToBeApplied;
    private Long productId;
    private Long brandId;
}
