package com.nf.pricechallenge.service;

import org.openapitools.model.PriceResponse;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceService {

    Optional<PriceResponse> findPrice(LocalDateTime dateToBeApplied, Long productId, Long brandId);
}
