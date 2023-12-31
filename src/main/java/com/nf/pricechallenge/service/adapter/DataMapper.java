package com.nf.pricechallenge.service.adapter;

import com.nf.pricechallenge.dto.DataRequestDTO;
import com.nf.pricechallenge.service.dto.DataRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DataMapper {

    DataMapper MAPPER = Mappers.getMapper(DataMapper.class);

    DataRequestDTO toDto(DataRequest dataRequest);
}
