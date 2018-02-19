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
public class FlooringMasterStateTax {
    
    String customerState;
    BigDecimal taxRate = new BigDecimal("0.00");
    
    public FlooringMasterStateTax(String customerState){
        this.customerState = customerState;
    }

    public String getCustomerState() {
        return customerState;
    }

    public void setCustomerState(String customerState) {
        this.customerState = customerState;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }
    
    
    
    
}
