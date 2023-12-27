package com.nf.pricechallenge.service;

import org.openapitools.model.PriceResponse;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface PriceService {

    Optional<PriceResponse> findPrice();
}
