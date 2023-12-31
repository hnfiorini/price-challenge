package com.nf.pricechallenge.controller;

import com.nf.pricechallenge.controller.adapter.PriceMapper;
import com.nf.pricechallenge.dto.PriceRequestDTO;
import com.nf.pricechallenge.service.PriceService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.openapitools.model.PriceResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PriceControllerTest {

    @InjectMocks
    private PriceController priceController;

    @Mock
    private PriceService priceService;

    @Mock
    private PriceMapper priceMapper;

    @Test
    void findPriceByParams_givenValidValuesReturnOk() {
        String dateToBeApplied = "2020-06-14T10:00:00";
        Long productId = 35455L;
        Long brandId = 1L;

        PriceResponse priceResponse = new PriceResponse();
        priceResponse.setPrice(new BigDecimal("35.4"));
        priceResponse.setStartDate("2020-06-14T00:00:00");
        priceResponse.setEndDate("2020-12-31T23:59:59");
        priceResponse.setProductId(35455L);
        priceResponse.setBrandId(1L);

        PriceRequestDTO priceRequestDTO = PriceRequestDTO.builder()
                .brandId(brandId)
                .dateToBeApplied(LocalDateTime.parse(dateToBeApplied))
                .productId(productId)
                .build();
        when(priceMapper.toDTO(any())).thenReturn(priceRequestDTO);
        when(priceService.findPrice(any())).thenReturn(Optional.of(priceResponse));

        ResponseEntity<PriceResponse> price = priceController.findPriceByParams(dateToBeApplied, productId, brandId);
        assertEquals(price.getStatusCode(), HttpStatus.OK);
        assertNotNull(price.getBody());
        assertEquals(new BigDecimal("35.4"), price.getBody().getPrice());
        assertEquals("2020-06-14T00:00:00", price.getBody().getStartDate());
        assertEquals("2020-12-31T23:59:59", price.getBody().getEndDate());
        assertEquals(35455L, price.getBody().getProductId());
        assertEquals(1L, price.getBody().getBrandId());
    }

    @Test
    void findPriceByParams_givenValidValuesReturnNotFound() {
        String dateToBeApplied = "2020-06-14T10:00:00";
        Long productId = 35456L;
        Long brandId = 2L;
        PriceRequestDTO priceRequestDTO = PriceRequestDTO.builder()
                .brandId(brandId)
                .dateToBeApplied(LocalDateTime.parse(dateToBeApplied))
                .productId(productId)
                .build();
        when(priceMapper.toDTO(any())).thenReturn(priceRequestDTO);
        when(priceService.findPrice(any())).thenReturn(Optional.empty());
        ResponseEntity<PriceResponse> price = priceController.findPriceByParams(dateToBeApplied, productId, brandId);
        assertEquals(price.getStatusCode(), HttpStatus.NOT_FOUND);
    }
}