package com.nf.pricechallenge.repositories;

import com.nf.pricechallenge.entities.Price;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceRepository extends JpaRepository<Price, Long> {
}
