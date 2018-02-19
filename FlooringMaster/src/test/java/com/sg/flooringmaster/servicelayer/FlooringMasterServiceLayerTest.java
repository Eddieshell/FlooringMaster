/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmaster.servicelayer;

import com.sg.flooringmaster.dao.FlooringMasterDaoPersistenceExeption;
import com.sg.flooringmaster.dto.FlooringMasterInventory;
import com.sg.flooringmaster.dto.FlooringMasterOrder;
import com.sg.flooringmaster.dto.FlooringMasterStateTax;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Eddie
 */
public class FlooringMasterServiceLayerTest {
    
    ApplicationContext ctx = 
            new ClassPathXmlApplicationContext("applicationContext.xml");
    
    FlooringMasterServiceLayer srv = ctx.getBean("serviceLayer", FlooringMasterServiceLayer.class);
    
    public FlooringMasterServiceLayerTest() {
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
     * Test of getAllOrdersForDate method, of class FlooringMasterServiceLayer.
     */
    @Test
    public void testGetAllOrdersForDate() throws Exception {
        Set<FlooringMasterOrder> results = new HashSet<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy"); 
        String date = "02152018";
        LocalDate checkDate = LocalDate.parse(date, formatter);
        
        // this line test the formatDate method on the servie layer.
        //String stringTest = srv.formatDate(checkDate);
        
        results = srv.getAllOrdersForDate(date);
        
        assertEquals(1, results.size());
        
    }

    /**
     * Test of formatDate method, of class FlooringMasterServiceLayer.
     */
    @Test
    public void testFormatDate() {
    }

    /**
     * Test of createOrderDate method, of class FlooringMasterServiceLayer.
     */
    @Test
    public void testCreateOrderNumber() throws Exception {
        
        String checkDate = srv.formatDate(LocalDate.now());
        checkDate = checkDate+"1";
        
        String orderNumber = srv.createOrderNumber();
        
        assertTrue(orderNumber.equals(checkDate));
        
    }    

    /**
     * Test of addNewOrder method and buildNewOrder method, of class FlooringMasterServiceLayer.
     */
    @Test
    public void testAddNewOrder() throws FlooringMasterDaoPersistenceExeption {
        
        LocalDate date= LocalDate.of(2013,06,01);
        
        FlooringMasterInventory onlyInventory;
        FlooringMasterStateTax onlyTax;
        FlooringMasterOrder onlyOrder;
        
         // create StateTax object
        onlyTax=new FlooringMasterStateTax("OH");
        onlyTax.setTaxRate(new BigDecimal("6.25"));
        
        //Create Invneotry Object
        onlyInventory= new FlooringMasterInventory("Wood");
        onlyInventory.setCostPerSqFt(new BigDecimal("2.75"));
        onlyInventory.setLaborCostPerSqFt(new BigDecimal("4.25"));
        
        //Create Order Object
        onlyOrder=new FlooringMasterOrder("060120132");
        onlyOrder.setCustomerName("Shell");
        onlyOrder.setworkArea(new BigDecimal("100.5"));
        
        srv.addNewOrder(onlyOrder, onlyInventory, onlyTax);
        FlooringMasterOrder testSet= srv.getSingleOrder("060120132",date);
        
                
        
        assertEquals("Shell", testSet.getCustomerName());
        
    }

    /**
     * Test of getAllStateTax method, of class FlooringMasterServiceLayer.
     */
    @Test
    public void testGetAllStateTax() throws Exception {
        ArrayList<FlooringMasterStateTax> testList =
                new ArrayList<>(srv.getAllStateTax());
        assertEquals(4,testList.size());
    }

    /**
     * Test of getAllInventory method, of class FlooringMasterServiceLayer.
     */
    @Test
    public void testGetAllInventory() throws Exception {
        ArrayList<FlooringMasterInventory> testList = 
                new ArrayList<>(srv.getAllInventory());
        
        assertEquals(4, testList.size());
        
    }
   
}
