package com.nf.pricechallenge.dto;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PriceRequestDTO {
    private LocalDateTime dateToBeApplied;
    private Long productId;
    private Long brandId;
}
