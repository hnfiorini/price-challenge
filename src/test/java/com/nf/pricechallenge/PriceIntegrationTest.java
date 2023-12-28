package com.nf.pricechallenge;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.LinkedMultiValueMap;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PriceIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @ParameterizedTest
    @CsvSource({
            ", 15, 2",
            "2020-06-15T00:00:00,, 10",
            "2020-06-15T00:00:00, 233,"
    })
    void findPriceByParams_ThrowErrorIfParamsAreNull(String dateToBeApplied, Long productId, Long brandId) throws Exception {

        mvc.perform(MockMvcRequestBuilders
                        .get("/prices")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());

    }


    @ParameterizedTest
    @CsvSource({
            "2020-06-14T10:00:00, 35455, 1, 35.5",
            "2020-06-14T16:00:00, 35455, 1, 25.45",
            "2020-06-14T21:00:00, 35455, 1, 35.5",
            "2020-06-15T10:00:00, 35455, 1, 30.5",
            "2020-06-16T21:00:00, 35455, 1, 38.95",
    })
    void findPriceByParams_ReturnPriceStatus200(String dateToBeApplied, Long productId, Long brandId, String resultExpected) throws Exception {

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
                .andExpect(MockMvcResultMatchers.jsonPath("$.productId").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(resultExpected));

    }

    @ParameterizedTest
    @CsvSource({
            "2020-06-14T10:00:00, 35444, 1",
            "2020-06-14T16:00:00, 35455, 2",
            "2021-06-14T21:00:00, 35455, 1",
    })
    void findPriceByParams_ReturnNotFound404(String dateToBeApplied, Integer productId, Integer brandId) throws Exception {

        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("dateToBeApplied", dateToBeApplied);
        requestParams.add("productId", productId.toString());
        requestParams.add("brandId", brandId.toString());

        mvc.perform(MockMvcRequestBuilders
                        .get("/prices")
                        .params(requestParams)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());

    }

}