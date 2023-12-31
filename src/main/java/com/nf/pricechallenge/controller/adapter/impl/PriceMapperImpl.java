package com.nf.pricechallenge.controller.adapter.impl;

import com.nf.pricechallenge.controller.adapter.PriceMapper;
import com.nf.pricechallenge.controller.dto.PriceRequest;
import com.nf.pricechallenge.dto.PriceRequestDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class PriceMapperImpl implements PriceMapper {

    @Override
    public PriceRequestDTO toDTO(PriceRequest priceRequest) {
        return PriceRequestDTO.builder()
                .productId(priceRequest.getProductId())
                .brandId(priceRequest.getBrandId())
                .dateToBeApplied(LocalDateTime.parse(priceRequest.getDateToBeApplied()))
                .build();
    }
}
