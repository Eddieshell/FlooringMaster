/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmaster.dao;

import com.sg.flooringmaster.dto.FlooringMasterInventory;
import java.util.ArrayList;

/**
 *
 * @author Eddie
 */
public interface FlooringMasterInventoryDao {
    
    public ArrayList<FlooringMasterInventory> getAllInventoryItems() throws FlooringMasterDaoPersistenceExeption;
    
    public FlooringMasterInventory getSingleInventoryItem(int index)throws FlooringMasterDaoPersistenceExeption;
    
}
