package com.nf.pricechallenge.controller;

import com.nf.pricechallenge.service.PriceService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openapitools.model.PriceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.LinkedMultiValueMap;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PriceController.class)
class PriceControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PriceService priceService;

    @ParameterizedTest
    @CsvSource({
            ", 15, 2",
            "2020-06-15T00:00:00,, 10",
            "2020-06-15T00:00:00, 233,"
    })
    void findPriceByParams_ThrowErrorIfParamsAreNull(String dateToBeApplied, Integer productId, Integer brandId) throws Exception {

        mvc.perform(MockMvcRequestBuilders
                        .get("/prices")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());

    }


    @ParameterizedTest
    @CsvSource({
            "2020-06-14T10:00:00, 35455, 1, 1",
            "2020-06-14T16:00:00, 35455, 1, 1",
            "2020-06-14T21:00:00, 35455, 1, 1",
            "2020-06-15T10:00:00, 35455, 1, 1",
            "2020-06-16T21:00:00, 35455, 1, 1",
    })
    void findPriceByParams_ReturnPriceStatus200(String dateToBeApplied, Integer productId, Integer brandId, String resultExpected) throws Exception {

        PriceResponse priceResponse = new PriceResponse();
        priceResponse.startDate("2020-06-14T00:00:00");
        priceResponse.setEndDate("2020-06-14T00:00:00");
        priceResponse.setPrice(new BigDecimal(666));
        priceResponse.setPriceToBeApplied(new BigDecimal(665));
        priceResponse.setProductId(1234);
        priceResponse.setBrandId(1);

        when(priceService.findPrice()).thenReturn(Optional.of(priceResponse));

        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("dateToBeApplied", dateToBeApplied);
        requestParams.add("productId", productId.toString());
        requestParams.add("brandId", brandId.toString());

        mvc.perform(MockMvcRequestBuilders
                        .get("/prices")
                        .params(requestParams)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.productId").exists());
        //.andExpect(MockMvcResultMatchers.jsonPath("$.employees[*].employeeId").isNotEmpty());

    }

}