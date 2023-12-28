package com.nf.pricechallenge.controller;

import com.nf.pricechallenge.service.PriceService;
import lombok.RequiredArgsConstructor;
import org.openapitools.api.PricesApi;
import org.openapitools.model.PriceResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class PriceController implements PricesApi {

    private final PriceService priceService;


    @Override
    public ResponseEntity<PriceResponse> findPriceByParams(String dateToBeApplied, Integer productId, Integer brandId) {

        Optional<PriceResponse> priceOptional = priceService.findPrice(LocalDateTime.parse(dateToBeApplied), Long.valueOf(productId), Long.valueOf(brandId));
        if (priceOptional.isPresent()) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            return ResponseEntity.ok().headers(headers).body(priceOptional.get());
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
