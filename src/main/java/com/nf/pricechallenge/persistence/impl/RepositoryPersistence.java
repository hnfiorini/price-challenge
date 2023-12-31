package com.nf.pricechallenge.persistence.impl;

import com.nf.pricechallenge.domain.Price;
import com.nf.pricechallenge.dto.DataRequestDTO;
import com.nf.pricechallenge.dto.DataResponse;
import com.nf.pricechallenge.persistence.Persistence;
import com.nf.pricechallenge.persistence.repositories.PriceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class RepositoryPersistence implements Persistence {

    private final PriceRepository priceRepository;

    @Override
    public Optional<DataResponse> find(DataRequestDTO dataRequest) {
        Optional<Price> priceOptional = priceRepository.findPriceByConditions(
                    dataRequest.getDateToBeApplied(),
                    dataRequest.getProductId(),
                    dataRequest.getBrandId());
        if (priceOptional.isPresent()) {
            Price price = priceOptional.get();
            return Optional.of(DataResponse.builder()
                    .brandId(price.getBrandId())
                    .productId(price.getProductId())
                    .startDate(price.getStartDate())
                    .endDate(price.getEndDate())
                    .price(price.getPrice())
                    .priceList(price.getPriceList())
                    .build());
        } else {
            return Optional.empty();
        }
    }
}
