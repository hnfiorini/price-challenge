package com.nf.pricechallenge.controller.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class PriceRequest {

    private String dateToBeApplied;
    private Long productId;
    private Long brandId;
}
