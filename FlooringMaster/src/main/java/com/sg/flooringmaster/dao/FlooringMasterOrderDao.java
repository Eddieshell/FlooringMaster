/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmaster.dao;

import com.sg.flooringmaster.dto.FlooringMasterOrder;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Eddie
 */
public interface FlooringMasterOrderDao {
    
    public Set<FlooringMasterOrder> listAllOrders(String orderDate) throws FlooringMasterDaoPersistenceExeption;
    
    public FlooringMasterOrder listSingleOrder(String orderNumber, String orderDate) throws FlooringMasterDaoPersistenceExeption;
    
    public void removeOrder(String orderNumber) throws FlooringMasterDaoPersistenceExeption;
    
    public void editOrder(FlooringMasterOrder order) throws FlooringMasterDaoPersistenceExeption;
    
    public void addOrder(FlooringMasterOrder order) throws FlooringMasterDaoPersistenceExeption;
    
    public void writeData() throws FlooringMasterDaoPersistenceExeption;
    
}
