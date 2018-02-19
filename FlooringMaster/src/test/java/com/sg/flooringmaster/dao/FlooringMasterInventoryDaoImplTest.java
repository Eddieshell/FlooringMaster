/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmaster.dao;

import com.sg.flooringmaster.dto.FlooringMasterInventory;
import java.math.BigDecimal;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Eddie
 */
public class FlooringMasterInventoryDaoImplTest {
    
    FlooringMasterInventoryDao dao = new FlooringMasterInventoryDaoImpl();
    
    public FlooringMasterInventoryDaoImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getAllInventoryItems method, of class FlooringMasterInventoryDaoImpl.
     */
    @Test
    public void testGetAllInventoryItems() throws Exception {
        ArrayList<FlooringMasterInventory> testList = dao.getAllInventoryItems();
        assertEquals(4, testList.size()); 
    }

    /**
     * Test of getSingleInventoryItem method, of class FlooringMasterInventoryDaoImpl.
     */
    @Test
    public void testGetSingleInventoryItem() throws Exception {
        FlooringMasterInventory itemTested = dao.getSingleInventoryItem(0);
        assertEquals("Carpet", itemTested.getProductType());
    }
    
    @Test
    public void testGetSingleInventoryItem2() throws Exception {
        FlooringMasterInventory itemTested = dao.getSingleInventoryItem(1);
        assertEquals(new BigDecimal("1.75"), itemTested.getCostPerSqFt());
    }
    
    @Test
    public void testGetSingleInventoryItem3() throws Exception {
        FlooringMasterInventory itemTested = dao.getSingleInventoryItem(2);
        assertEquals(new BigDecimal("4.15"), itemTested.getLaborCostPerSqFt());
    }

    /**
     * Test of loadInventory method, of class FlooringMasterInventoryDaoImpl.
     */
    @Test
    public void testLoadInventory() throws Exception {
    }
    
}
