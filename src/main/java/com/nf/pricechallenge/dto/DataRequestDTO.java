package com.nf.pricechallenge.dto;

import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DataRequestDTO {

    private LocalDateTime dateToBeApplied;
    private Long productId;
    private Long brandId;
}
