package com.nf.pricechallenge.service.impl;

import com.nf.pricechallenge.dto.DataResponse;
import com.nf.pricechallenge.service.adapter.DataMapper;
import com.nf.pricechallenge.service.dto.DataRequest;
import com.nf.pricechallenge.dto.PriceRequestDTO;
import com.nf.pricechallenge.persistence.Persistence;
import com.nf.pricechallenge.service.PriceService;
import lombok.RequiredArgsConstructor;
import org.openapitools.model.PriceResponse;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PriceServiceImpl implements PriceService {

    private final Persistence persistence;
    private final DataMapper dataMapper;

    @Override
    public Optional<PriceResponse> findPrice(PriceRequestDTO priceRequestDTO) {
        DataRequest dataRequest = DataRequest.builder()
                .brandId(priceRequestDTO.getBrandId())
                .productId(priceRequestDTO.getProductId())
                .dateToBeApplied(priceRequestDTO.getDateToBeApplied())
                .build();
        Optional<DataResponse> priceOptional = persistence.find(dataMapper.toDto(dataRequest));
        if (priceOptional.isPresent()) {
            PriceResponse priceResponse = new PriceResponse();
            DataResponse price = priceOptional.get();
            priceResponse.startDate(price.getStartDate().toString());
            priceResponse.setEndDate(price.getEndDate().toString());
            priceResponse.setPrice(price.getPrice());
            priceResponse.setPriceList(price.getPriceList());
            priceResponse.setProductId(price.getProductId());
            priceResponse.setBrandId(price.getBrandId());
            return Optional.of(priceResponse);
        } else {
            return Optional.empty();
        }
    }
}
