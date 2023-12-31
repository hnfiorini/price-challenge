package com.nf.pricechallenge.persistence;

import com.nf.pricechallenge.dto.DataRequestDTO;
import com.nf.pricechallenge.dto.DataResponse;

import java.util.Optional;

public interface Persistence {

    Optional<DataResponse> find(DataRequestDTO dataRequest);
}
