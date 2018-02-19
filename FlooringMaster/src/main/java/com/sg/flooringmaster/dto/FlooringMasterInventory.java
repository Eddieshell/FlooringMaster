/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmaster.dto;

import java.math.BigDecimal;

/**
 *
 * @author Eddie
 */
public class FlooringMasterInventory {
    
    private String productType;
    private BigDecimal materialCostPerSqFt = new BigDecimal("0.00");
    private BigDecimal laborCostPerSqFt = new BigDecimal("0.00");
    
    public FlooringMasterInventory(String productType) {
        this.productType = productType;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public BigDecimal getCostPerSqFt() {
        return materialCostPerSqFt;
    }

    public void setCostPerSqFt(BigDecimal costPerSqFt) {
        this.materialCostPerSqFt = costPerSqFt;
    }

    public BigDecimal getLaborCostPerSqFt() {
        return laborCostPerSqFt;
    }

    public void setLaborCostPerSqFt(BigDecimal laborCostPerSqFt) {
        this.laborCostPerSqFt = laborCostPerSqFt;
    }
    
    
    
}
