package com.nf.pricechallenge.service;

import com.nf.pricechallenge.dto.PriceRequestDTO;
import org.openapitools.model.PriceResponse;

import java.util.Optional;

public interface PriceService {

    Optional<PriceResponse> findPrice(PriceRequestDTO priceRequestDTO);
}
