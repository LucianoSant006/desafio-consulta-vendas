package com.devsuperior.dsmeta.repositories;


import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SaleReportDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.projections.SaleSummaryMinProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query("SELECT new com.devsuperior.dsmeta.dto.SaleReportDTO(s) "
            + "FROM Sale s " +
            "    WHERE s.date BETWEEN :startDate AND :endDate " +
            "AND UPPER(s.seller.name) LIKE UPPER(CONCAT('%', :name, '%'))")
    Page<SaleReportDTO> findSummaryBySalesperson(@Param("name") String name,
                                                 @Param("startDate")  LocalDate startDate,
                                                 @Param("endDate")  LocalDate endDate,
                                                 Pageable pageable);

    @Query(nativeQuery = true, value =
            "SELECT tb_seller.name AS sellerName, SUM(tb_sales.amount) AS total "
                    + "FROM tb_seller " + "INNER JOIN  tb_sales ON " +
                    "    tb_seller.id = tb_sales.seller_id " +
                    "WHERE  tb_sales.date BETWEEN :minDate AND :maxDate " +
                    "GROUP BY  tb_seller.name" +
                    " ORDER BY tb_seller.name ASC"
    )
    List<SaleSummaryMinProjection>findSalesSummaryBySalesperson(@Param("minDate")LocalDate minDate,@Param("maxDate") LocalDate maxDate);
}
