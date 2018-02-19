/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmaster.dao;

import com.sg.flooringmaster.dto.FlooringMasterOrder;
import com.sg.flooringmaster.dto.FlooringMasterStateTax;
import java.math.BigDecimal;
import java.util.Set;
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
public class FlooringMasterOrderDaoImplTest {
    
    FlooringMasterOrderDao dao= new FlooringMasterOrderDaoImpl();
    
    public FlooringMasterOrderDaoImplTest() {
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
     * Test of listAllOrders method, of class FlooringMasterOrderDaoImpl.
     */
    @Test
    public void testListAllOrders() throws Exception {
        Set testItem = dao.listAllOrders("06012013");
        
        assertEquals(1, testItem.size());
    }
    
    @Test
    public void testListAllOrders1() throws Exception {
        Set testItem = dao.listAllOrders("060120138");
        
        assertEquals(0, testItem.size());
    }

    /**
     * Test of listSingleOrder method, of class FlooringMasterOrderDaoImpl.
     */
    @Test
    public void testListSingleOrder() throws Exception {
        FlooringMasterOrder testItem = dao.listSingleOrder("1", "06012013");
        assertEquals("Wood", testItem.getProductType().getProductType());
    }

    /**
     * Test of removeOrder method, of class FlooringMasterOrderDaoImpl.
     */
    @Test
    public void testRemoveOrder() throws Exception {
        FlooringMasterOrder testItem = new FlooringMasterOrder("02152018");
        FlooringMasterStateTax testTax = new FlooringMasterStateTax("OH");
        testTax.setTaxRate(new BigDecimal("6.25"));
        testItem.setCustomerName("Eddie");
        testItem.setworkArea(new BigDecimal("200"));
        testItem.setState(testTax);
        
        dao.addOrder(testItem);
        dao.removeOrder("02152018");
        FlooringMasterOrder wantedItem = dao.listSingleOrder("02152018", "06012013");
        
        assertEquals(new BigDecimal("0.00"), wantedItem.getOrderTotalCost());
    }

    /**
     * Test of editOrder method, of class FlooringMasterOrderDaoImpl.
     */
    @Test
    public void testEditOrder() throws Exception {
        FlooringMasterOrder testItem = new FlooringMasterOrder("02152018");
        FlooringMasterStateTax testTax = new FlooringMasterStateTax("OH");
        testTax.setTaxRate(new BigDecimal("6.25"));
        testItem.setCustomerName("Eddie");
        testItem.setworkArea(new BigDecimal("200"));
        testItem.setState(testTax);
        
        dao.addOrder(testItem);
        FlooringMasterOrder wantedItem = dao.listSingleOrder("02152018", "06012013");
        wantedItem.getState().setCustomerState("WI");
        
        assertEquals("WI", wantedItem.getState().getCustomerState());
    }

    /**
     * Test of addOrder method, of class FlooringMasterOrderDaoImpl.
     */
    @Test
    public void testAddOrder() throws Exception {
        
        FlooringMasterOrder testItem = new FlooringMasterOrder("02152018");
        FlooringMasterStateTax testTax = new FlooringMasterStateTax("OH");
        testTax.setTaxRate(new BigDecimal("6.25"));
        testItem.setCustomerName("Eddie");
        testItem.setworkArea(new BigDecimal("200"));
        testItem.setState(testTax);
        
        dao.addOrder(testItem);
        FlooringMasterOrder wantedItem = dao.listSingleOrder("02152018", "06012013");
        
        assertEquals("OH", wantedItem.getState().getCustomerState());
        
    }

        
}
