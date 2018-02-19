/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmaster.dao;

import com.sg.flooringmaster.dto.FlooringMasterInventory;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Eddie
 */
public class FlooringMasterInventoryDaoImpl implements FlooringMasterInventoryDao{
    
    public static final String INVENTORY_FILE = "Products.txt";
    public static final String DELIMITER = ",";
    
    ArrayList<FlooringMasterInventory> inventoryList = new ArrayList<>();

    @Override
    public ArrayList<FlooringMasterInventory> getAllInventoryItems()
    throws FlooringMasterDaoPersistenceExeption{
        loadInventory();
        return inventoryList;
    }

    @Override
    public FlooringMasterInventory getSingleInventoryItem(int index)
    throws FlooringMasterDaoPersistenceExeption{
        loadInventory();
        FlooringMasterInventory itemWanted = inventoryList.get(index);
        return itemWanted;      
               
    }
    
    public void loadInventory() throws FlooringMasterDaoPersistenceExeption{
        Scanner sc = null;
        
        if(inventoryList.size() == 0){
        try {
        sc = new Scanner(
                new BufferedReader(
                        new FileReader(INVENTORY_FILE)));
        }catch(FileNotFoundException e){
            throw new FlooringMasterDaoPersistenceExeption(
            "Unable to load inventory from file, e");
        }
        
        String currentLine;
        String[] currentTokens;
        sc.nextLine();

        while (sc.hasNextLine()) {
            currentLine = sc.nextLine();
            currentTokens = currentLine.split(DELIMITER);
            
            if(currentTokens.length > 2) {
            FlooringMasterInventory currentItem = new FlooringMasterInventory(currentTokens[0]);
            currentItem.setCostPerSqFt(new BigDecimal(currentTokens[1]));
            currentItem.setLaborCostPerSqFt(new BigDecimal(currentTokens[2]));
            inventoryList.add(currentItem);
            }
        }

        sc.close();
        
        }
        
        
        
    }
    
}
