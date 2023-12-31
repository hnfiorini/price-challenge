package com.nf.pricechallenge.controller.adapter;

import com.nf.pricechallenge.controller.dto.PriceRequest;
import com.nf.pricechallenge.dto.PriceRequestDTO;

public interface PriceMapper {

    PriceRequestDTO toDTO(PriceRequest priceRequest);

}
