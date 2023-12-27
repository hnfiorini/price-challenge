package com.nf.pricechallenge.controller;

import org.openapitools.api.PricesApi;
import org.openapitools.model.PriceResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;
import java.util.List;

@RestController
public class PriceController implements PricesApi {


    @Override
    public ResponseEntity<PriceResponse> findPriceByParams(String dateToBeApplied, Integer productId, Integer brandId) {
        return null;
    }
}
