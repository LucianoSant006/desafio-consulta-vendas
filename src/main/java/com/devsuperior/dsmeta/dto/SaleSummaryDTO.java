package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.projections.SaleSummaryMinProjection;

public class SaleSummaryDTO {

    private String SellerName;
    private Double total;

    public SaleSummaryDTO(){

    }

    public SaleSummaryDTO(String sellerName, Double total) {
        SellerName = sellerName;
        this.total = total;
    }
    public SaleSummaryDTO(SaleSummaryMinProjection entity) {
        SellerName = entity.SellerName();
        total = entity.total();
    }

    public String getSellerName() {
        return SellerName;
    }

    public void setSellerName(String sellerName) {
        SellerName = sellerName;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
