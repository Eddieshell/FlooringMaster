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
public class FlooringMasterOrder {
    
    private String orderNumber;
    private String customerName;
    private FlooringMasterStateTax State;
    private FlooringMasterInventory productType;
    private BigDecimal workArea = new BigDecimal("0.00");       
    private BigDecimal orderLaborCost = new BigDecimal("0.00");
    private BigDecimal orderMaterialCost = new BigDecimal("0.00");
    private BigDecimal orderTaxsCharged = new BigDecimal("0.00");
    private BigDecimal orderTotalCost = new BigDecimal("0.00");
    
    public FlooringMasterOrder(String orderNumber){
        this.orderNumber = orderNumber;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public FlooringMasterStateTax getState() {
        return State;
    }

    public void setState(FlooringMasterStateTax State) {
        this.State = State;
    }

    public FlooringMasterInventory getProductType() {
        return productType;
    }

    public void setProductType(FlooringMasterInventory productType) {
        this.productType = productType;
    }

    public BigDecimal getworkArea() {
        return workArea;
    }

    public void setworkArea(BigDecimal area) {
        this.workArea = area;
    }
    
    public BigDecimal getLaborCost() {
        return orderLaborCost;
    }

    public void setLaborCost(BigDecimal laborCost) {
        this.orderLaborCost = laborCost;
    }

    public BigDecimal getMaterialCost() {
        return orderMaterialCost;
    }

    public void setMaterialCost(BigDecimal materialCost) {
        this.orderMaterialCost = materialCost;
    }

    public BigDecimal getOrderTaxsCharged() {
        return orderTaxsCharged;
    }

    public void setOrderTaxsCharged(BigDecimal tax) {
        this.orderTaxsCharged = tax;
    }

    public BigDecimal getOrderTotalCost() {
        return orderTotalCost;
    }

    public void setOrderTotalCost(BigDecimal totalCost) {
        this.orderTotalCost = totalCost;
    }
    
    
    
}
