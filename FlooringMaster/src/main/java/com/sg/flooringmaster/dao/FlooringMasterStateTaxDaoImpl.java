/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmaster.dao;

import com.sg.flooringmaster.dto.FlooringMasterStateTax;
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
public class FlooringMasterStateTaxDaoImpl implements FlooringMasterStateTaxDao {

    public static final String STATE_TAX_FILE = "Taxes.txt";
    public static final String DELIMITER = ",";

    ArrayList<FlooringMasterStateTax> stateTaxList = new ArrayList<>();

    @Override
    public ArrayList<FlooringMasterStateTax> getAllStateTax() throws FlooringMasterDaoPersistenceExeption {
        loadStateTax();
        return stateTaxList;
    }

    @Override
    public FlooringMasterStateTax getSingleStateTax(int index) throws FlooringMasterDaoPersistenceExeption {
        loadStateTax();
        FlooringMasterStateTax wantedItem = stateTaxList.get(index);
        return wantedItem;
    }

    public void loadStateTax() throws FlooringMasterDaoPersistenceExeption {
        Scanner scanner;

        if (stateTaxList.size() == 0) {

            try {
                scanner = new Scanner(
                        new BufferedReader(
                                new FileReader(STATE_TAX_FILE)));
            } catch (FileNotFoundException e) {
                throw new FlooringMasterDaoPersistenceExeption(
                        "Unable to load State Tax File", e);
            }

            String currentLine;
            String[] currentTokens;
            scanner.nextLine();

            while (scanner.hasNextLine()) {
                currentLine = scanner.nextLine();
                currentTokens = currentLine.split(DELIMITER);
                
                if (currentTokens.length > 1){
                FlooringMasterStateTax currentItem = new FlooringMasterStateTax(currentTokens[0]);
                currentItem.setTaxRate(new BigDecimal(currentTokens[1]));
                stateTaxList.add(currentItem);
                }

            }
            
            scanner.close();
        }
    }

}
