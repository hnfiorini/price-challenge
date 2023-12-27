package com.nf.pricechallenge.service.impl;

import com.nf.pricechallenge.entities.Price;
import com.nf.pricechallenge.repositories.PriceRepository;
import com.nf.pricechallenge.service.PriceService;
import lombok.RequiredArgsConstructor;
import org.openapitools.model.PriceResponse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PriceServiceImpl implements PriceService {

    private final PriceRepository priceRepository;

    @Override
    public Optional<PriceResponse> findPrice() {
        Price price = priceRepository.findAll().get(0);
        PriceResponse priceResponse = new PriceResponse();
        priceResponse.startDate(price.getStartDate().toString());
        priceResponse.setEndDate(price.getEndDate().toString());
        priceResponse.setPrice(new BigDecimal(666));
        priceResponse.setPriceToBeApplied(new BigDecimal(665));
        priceResponse.setProductId(1234);
        priceResponse.setBrandId(1);
        return Optional.of(priceResponse);
    }
}
