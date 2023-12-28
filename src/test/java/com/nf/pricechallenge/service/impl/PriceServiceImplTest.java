package com.nf.pricechallenge.service.impl;

import com.nf.pricechallenge.entities.Price;
import com.nf.pricechallenge.repositories.PriceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.openapitools.model.PriceResponse;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PriceServiceImplTest {

    @InjectMocks
    private PriceServiceImpl priceService;

    @Mock
    private PriceRepository priceRepository;

    @Test
    void findPrice_existPrice() {
        String dateToBeApplied = "2020-06-14T10:00:00";
        Long productId = 35455L;
        Long brandId = 1L;

        Price priceEntity = Price.builder()
                .id(1L)
                .brandId(brandId)
                .productId(productId)
                .price(new BigDecimal("35.5"))
                .startDate(LocalDateTime.parse("2020-06-14T00:00:00"))
                .endDate(LocalDateTime.parse("2020-12-31T23:59:59"))
                .priceList(1)
                .currency("EUR")
                .priority(0)
                .build();
        when(priceRepository.findPriceByConditions(any(), any(), any())).thenReturn(Optional.of(priceEntity));

        Optional<PriceResponse> price = priceService.findPrice(LocalDateTime.parse(dateToBeApplied), productId, brandId);

        assertTrue(price.isPresent());
        assertEquals(priceEntity.getPrice(), price.get().getPrice());
        assertEquals(priceEntity.getStartDate().toString(), price.get().getStartDate());
        assertEquals(priceEntity.getEndDate().toString(), price.get().getEndDate());
        assertEquals(priceEntity.getProductId(), price.get().getProductId());
        assertEquals(priceEntity.getBrandId(), price.get().getBrandId());
    }

    @Test
    void findPrice_noExistPrice() {
        String dateToBeApplied = "2020-06-14T10:00:00";
        Long productId = 35455L;
        Long brandId = 2L;

        when(priceRepository.findPriceByConditions(any(), any(), any())).thenReturn(Optional.empty());

        Optional<PriceResponse> price = priceService.findPrice(LocalDateTime.parse(dateToBeApplied), productId, brandId);

        assertTrue(price.isEmpty());
    }
}