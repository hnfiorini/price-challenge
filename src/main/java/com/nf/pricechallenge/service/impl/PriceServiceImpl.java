package com.nf.pricechallenge.service.impl;

import com.nf.pricechallenge.entities.Price;
import com.nf.pricechallenge.repositories.PriceRepository;
import com.nf.pricechallenge.service.PriceService;
import lombok.RequiredArgsConstructor;
import org.openapitools.model.PriceResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PriceServiceImpl implements PriceService {

    private final PriceRepository priceRepository;

    @Override
    public Optional<PriceResponse> findPrice(LocalDateTime dateToBeApplied, Long productId, Long brandId) {
        Optional<Price> priceOptional = priceRepository.findPriceByConditions(dateToBeApplied, productId, brandId);
        if (priceOptional.isPresent()) {
            PriceResponse priceResponse = new PriceResponse();
            Price price = priceOptional.get();
            priceResponse.startDate(price.getStartDate().toString());
            priceResponse.setEndDate(price.getEndDate().toString());
            priceResponse.setPrice(price.getPrice());
            priceResponse.setPriceToBeApplied(price.getPrice());
            priceResponse.setProductId(price.getProductId().intValue());
            priceResponse.setBrandId(price.getBrandId().intValue());
            return Optional.of(priceResponse);
        } else {
            return Optional.empty();
        }
    }
}
