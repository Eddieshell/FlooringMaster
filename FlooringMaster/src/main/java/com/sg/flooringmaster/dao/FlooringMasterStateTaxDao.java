/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmaster.dao;

import com.sg.flooringmaster.dto.FlooringMasterStateTax;
import java.util.ArrayList;

/**
 *
 * @author Eddie
 */
public interface FlooringMasterStateTaxDao {
    
    public ArrayList<FlooringMasterStateTax> getAllStateTax() throws FlooringMasterDaoPersistenceExeption;
    
    public FlooringMasterStateTax getSingleStateTax(int index) throws FlooringMasterDaoPersistenceExeption;
    
}
