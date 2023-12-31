package com.nf.pricechallenge.controller;

import com.nf.pricechallenge.controller.adapter.PriceMapper;
import com.nf.pricechallenge.controller.dto.PriceRequest;
import com.nf.pricechallenge.service.PriceService;
import lombok.RequiredArgsConstructor;
import org.openapitools.api.PricesApi;
import org.openapitools.model.PriceResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class PriceController implements PricesApi {

    private final PriceService priceService;
    private final PriceMapper priceMapper;

    @Override
    public ResponseEntity<PriceResponse> findPriceByParams(String dateToBeApplied, Long productId, Long brandId) {
        PriceRequest priceRequest = PriceRequest.builder()
                .brandId(brandId)
                .dateToBeApplied(dateToBeApplied)
                .productId(productId)
                .build();

        Optional<PriceResponse> priceOptional = priceService.findPrice(priceMapper.toDTO(priceRequest));
        if (priceOptional.isPresent()) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            return ResponseEntity.ok().headers(headers).body(priceOptional.get());
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
