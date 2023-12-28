package com.nf.pricechallenge.repositories;

import com.nf.pricechallenge.entities.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceRepository extends JpaRepository<Price, Long> {

    @Query("SELECT p FROM Price p " +
            "WHERE :dateApplied BETWEEN p.startDate AND p.endDate " +
            "AND p.productId = :idProduct " +
            "AND p.brandId = :idBrand " +
            "AND p.priority = (SELECT MAX(p2.priority) FROM Price p2 " +
            "                  WHERE :dateApplied BETWEEN p2.startDate AND p2.endDate " +
            "                  AND p2.productId = :idProduct " +
            "                  AND p2.brandId = :idBrand)")
    Optional<Price> findPriceByConditions(@Param("dateApplied") LocalDateTime dateApplied,
                                          @Param("idProduct") Long idProduct,
                                          @Param("idBrand") Long idBrand);
}
