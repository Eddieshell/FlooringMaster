/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmaster.dao;


import com.sg.flooringmaster.dto.FlooringMasterInventory;
import com.sg.flooringmaster.dto.FlooringMasterOrder;
import com.sg.flooringmaster.dto.FlooringMasterStateTax;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Eddie
 */
public class FlooringMasterOrderDaoStubImpl implements FlooringMasterOrderDao{
    
    private FlooringMasterOrder onlyOrder;
    private FlooringMasterStateTax onlyTax;
    private FlooringMasterInventory onlyInventory;
    
    private HashMap<String,FlooringMasterOrder> orderList = new HashMap<>();
    
    public FlooringMasterOrderDaoStubImpl(){
        // create StateTax object
        onlyTax=new FlooringMasterStateTax("OH");
        onlyTax.setTaxRate(new BigDecimal("6.25"));
        
        //Create Invneotry Object
        onlyInventory= new FlooringMasterInventory("Wood");
        onlyInventory.setCostPerSqFt(new BigDecimal("2.75"));
        onlyInventory.setLaborCostPerSqFt(new BigDecimal("4.25"));
        
        //Create Order Object
        onlyOrder=new FlooringMasterOrder("02152018");
        onlyOrder.setCustomerName("Shell");
        onlyOrder.setworkArea(new BigDecimal("100.5"));
        onlyOrder.setState(onlyTax);
        onlyOrder.setProductType(onlyInventory);
        onlyOrder.setMaterialCost(new BigDecimal("275.00"));
        onlyOrder.setLaborCost(new BigDecimal("405.00"));
        onlyOrder.setOrderTaxsCharged(new BigDecimal("35.35"));
        onlyOrder.setOrderTotalCost(new BigDecimal("710.35"));
    }

    @Override
    public Set<FlooringMasterOrder> listAllOrders(String orderDate) throws FlooringMasterDaoPersistenceExeption {
        Set<FlooringMasterOrder> returnItem = new HashSet<>();
        if(orderDate.equals(onlyOrder.getOrderNumber())){
            returnItem.add(onlyOrder);
            return returnItem;
        } else{
            return null;
        }
    }

    @Override
    public FlooringMasterOrder listSingleOrder(String orderNumber, String orderDate) throws FlooringMasterDaoPersistenceExeption {
        FlooringMasterOrder testOrder = orderList.get(orderNumber);
        return testOrder;
    }

    @Override
    public void removeOrder(String orderNumber) throws FlooringMasterDaoPersistenceExeption {
        if(onlyOrder.getOrderNumber() == orderNumber) {
            onlyOrder.setOrderTotalCost(new BigDecimal("0.00"));
        }
    }

    @Override
    public void editOrder(FlooringMasterOrder order) throws FlooringMasterDaoPersistenceExeption {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addOrder(FlooringMasterOrder order) throws FlooringMasterDaoPersistenceExeption {
        orderList.put(order.getOrderNumber(), order);
    }

    @Override
    public void writeData() throws FlooringMasterDaoPersistenceExeption {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
}
