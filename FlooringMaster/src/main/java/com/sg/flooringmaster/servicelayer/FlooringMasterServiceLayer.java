/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmaster.servicelayer;

import com.sg.flooringmaster.dao.FlooringMasterDaoPersistenceExeption;
import com.sg.flooringmaster.dao.FlooringMasterInventoryDao;
import com.sg.flooringmaster.dao.FlooringMasterOrderDao;
import com.sg.flooringmaster.dao.FlooringMasterStateTaxDao;
import com.sg.flooringmaster.dto.FlooringMasterInventory;
import com.sg.flooringmaster.dto.FlooringMasterOrder;
import com.sg.flooringmaster.dto.FlooringMasterStateTax;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Set;

/**
 *
 * @author Eddie
 */
public class FlooringMasterServiceLayer {
    
    FlooringMasterInventoryDao inventoryDao;
    FlooringMasterOrderDao orderDao;
    FlooringMasterStateTaxDao stateTaxDao;
    
    public FlooringMasterServiceLayer(FlooringMasterInventoryDao inventoryDao,
            FlooringMasterOrderDao orderDao,
            FlooringMasterStateTaxDao stateTaxDao){
        
        this.inventoryDao = inventoryDao;
        this.orderDao = orderDao;
        this.stateTaxDao = stateTaxDao;
    }
    
    
    
    public Set<FlooringMasterOrder> getAllOrdersForDate(String searchDate) throws FlooringMasterDaoPersistenceExeption{       
    Set<FlooringMasterOrder> orderList = orderDao.listAllOrders(searchDate);
    return orderList;
   }
    
    public String formatDate(LocalDate searchDate){
        DateTimeFormatter formatted = DateTimeFormatter.ofPattern("MMddyyyy");
    String wantedOrdersDate = searchDate.format(formatted);
    return wantedOrdersDate;
        
    }
    
    public String createOrderNumber() throws FlooringMasterDaoPersistenceExeption{
        LocalDate date = LocalDate.now();
        String formattedDate = this.formatDate(date);
        Set<FlooringMasterOrder> listOfOrders = this.getAllOrdersForDate(formattedDate);
        if(listOfOrders != null){
        int numOfOrders = listOfOrders.size() + 1;
        String newOrderNumber = formattedDate + Integer.toString(numOfOrders);
        return newOrderNumber;
        }else{
            return formattedDate+ "1";
        }
    }
    
    public void addNewOrder(FlooringMasterOrder newOrder,
            FlooringMasterInventory newOrderInv,
            FlooringMasterStateTax newOrderTax) throws FlooringMasterDaoPersistenceExeption{
        FlooringMasterOrder orderToAdd = this.buildNewOrder(newOrder, newOrderInv, newOrderTax);
        orderDao.addOrder(newOrder);
    }
    
    public ArrayList<FlooringMasterStateTax> getAllStateTax() throws FlooringMasterDaoPersistenceExeption{
        ArrayList<FlooringMasterStateTax> stateTaxes =
                new ArrayList<>(stateTaxDao.getAllStateTax());
        return stateTaxes;
    }
    
    public ArrayList<FlooringMasterInventory> getAllInventory() throws FlooringMasterDaoPersistenceExeption{
        ArrayList<FlooringMasterInventory> productList = 
                new ArrayList<>(inventoryDao.getAllInventoryItems());
        return productList;
    }
    
    private FlooringMasterOrder buildNewOrder(FlooringMasterOrder orderInfo,
            FlooringMasterInventory inventoryInfo,
            FlooringMasterStateTax taxInfo){
        orderInfo.setState(taxInfo);
        orderInfo.setProductType(inventoryInfo);
        
        //Material Cost Calculations
        BigDecimal materialPriceSQFT = inventoryInfo.getCostPerSqFt();
        orderInfo.setMaterialCost(materialPriceSQFT.multiply(orderInfo.getworkArea()));
        
        //Labor Cost Calculations
        BigDecimal laborCosts = inventoryInfo.getLaborCostPerSqFt();
        orderInfo.setLaborCost(laborCosts.multiply(orderInfo.getworkArea()));
        
        //Set taxes charged
        BigDecimal convertedTaxRate =  new BigDecimal("0.0000");
        convertedTaxRate = taxInfo.getTaxRate().divide(new BigDecimal("100"));
        BigDecimal lineItems = orderInfo.getLaborCost().add(orderInfo.getMaterialCost());
        BigDecimal withTax = lineItems.multiply(convertedTaxRate);
        orderInfo.setOrderTaxsCharged(withTax.setScale(2,RoundingMode.HALF_UP));
                
        
        //Set Total Amount
        orderInfo.setOrderTotalCost(orderInfo.getLaborCost()
                .add(orderInfo.getMaterialCost()
                        .add(orderInfo.getOrderTaxsCharged())));
        
        return orderInfo;
    }
    
    public FlooringMasterOrder getSingleOrder(String orderNumber, LocalDate orderDate) throws FlooringMasterDaoPersistenceExeption{
        String wantedOrderDate = this.formatDate(orderDate);
        FlooringMasterOrder requestedOrder = 
                orderDao.listSingleOrder(orderNumber, wantedOrderDate);
        return requestedOrder;
    }
    
    public void removeOrder(String order) throws FlooringMasterDaoPersistenceExeption{
        if(order != null){
        orderDao.removeOrder(order);
        }
    }
    
    public void writeData() throws FlooringMasterDaoPersistenceExeption{
        orderDao.writeData();
    }
    
}
