package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {
    @Query(nativeQuery = true, value = "SELECT tb.seller.name,SUM(tb_sales.amount) AS totalSales "
            + "FROM tb_sales INNER JOIN tb_seller ON tb_seller.id = tb_sales.seller_id "
             +"WHERE UPPER(tb.seller.name) = LIKE UPPER(CONCAT('%',:name,'%')) "
            + "AND tb_sales.date "
            + " BETWEEN STR_TO_DATE(:startDate, '%Y-%m-%d') "
            + "AND STR_TO_DATE(:endDate, '%Y-%m-%d') "
            + "ORDER BY tb_seller.name")
    List<SaleMinDTO> findSummaryBySalesperson(@Param("name") String name,
                                              @Param("startDate")String startDate,
                                              @Param("endDate") String endDate);

}
