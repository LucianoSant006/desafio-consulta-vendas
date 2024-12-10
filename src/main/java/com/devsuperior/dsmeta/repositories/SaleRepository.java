package com.devsuperior.dsmeta.repositories;


import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SaleReportDTO;
import com.devsuperior.dsmeta.entities.Sale;
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
}
