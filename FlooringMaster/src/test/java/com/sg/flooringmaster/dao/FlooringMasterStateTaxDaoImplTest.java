/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmaster.dao;

import com.sg.flooringmaster.dto.FlooringMasterStateTax;
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
public class FlooringMasterStateTaxDaoImplTest {
    
    FlooringMasterStateTaxDao dao = new FlooringMasterStateTaxDaoImpl();
    
    public FlooringMasterStateTaxDaoImplTest() {
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
     * Test of getAllStateTax method, of class FlooringMasterStateTaxDaoImpl.
     */
    @Test
    public void testGetAllStateTax() throws Exception {
        ArrayList<FlooringMasterStateTax> testList = dao.getAllStateTax();
        assertEquals(4, testList.size());
    }

    
    @Test
    public void testGetSingleStateTax() throws Exception {
        FlooringMasterStateTax testItem = dao.getSingleStateTax(0);
        
        assertEquals("OH", testItem.getCustomerState());
    }
    
    @Test
    public void testGetSingleStateTax2() throws Exception {
        FlooringMasterStateTax testItem = dao.getSingleStateTax(0);
        
        assertEquals(new BigDecimal("6.25"), testItem.getTaxRate());
    }

    /**
     * Test of loadStateTax method, of class FlooringMasterStateTaxDaoImpl.
     */
    @Test
    public void testLoadStateTax() throws Exception {
        //Tested in the three previous tests no need for an individual test
    }
    
}
