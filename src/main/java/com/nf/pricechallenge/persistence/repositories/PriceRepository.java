package com.nf.pricechallenge.persistence.repositories;

import com.nf.pricechallenge.domain.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {

    @Query(nativeQuery = true,
    value = "SELECT *\n" +
            "        FROM prices\n" +
            "        WHERE product_id = :idProduct\n" +
            "        AND brand_id = :idBrand\n" +
            "        AND brand_id = :idBrand\n" +
            "        AND (:dateApplied BETWEEN start_date AND end_date)\n" +
            "        ORDER BY priority DESC\n" +
            "        LIMIT 1")
    Optional<Price> findPriceByConditions(@Param("dateApplied") LocalDateTime dateApplied,
                                          @Param("idProduct") Long idProduct,
                                          @Param("idBrand") Long idBrand);
}
