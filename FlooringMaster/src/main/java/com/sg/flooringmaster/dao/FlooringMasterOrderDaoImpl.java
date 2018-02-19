/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmaster.dao;

import com.sg.flooringmaster.dto.FlooringMasterInventory;
import com.sg.flooringmaster.dto.FlooringMasterOrder;
import com.sg.flooringmaster.dto.FlooringMasterStateTax;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @author Eddie
 */
public class FlooringMasterOrderDaoImpl implements FlooringMasterOrderDao {

    HashMap<String, FlooringMasterOrder> orderList = new HashMap<>();

    public static final String DELIMITER = ",";

    @Override
    public Set<FlooringMasterOrder> listAllOrders(String orderDate) throws FlooringMasterDaoPersistenceExeption {
        loadOrders(orderDate);
        return orderList.entrySet()
                .stream()
                .filter(t -> t.getValue().getOrderTotalCost().compareTo(BigDecimal.ZERO) > 0)
                .map(Map.Entry::getValue)
                .collect(Collectors.toSet());
    }

    @Override
    public FlooringMasterOrder listSingleOrder(String orderNumber, String orderDate) throws FlooringMasterDaoPersistenceExeption {
        loadOrders(orderDate);
        return orderList.get(orderNumber);
    }

    @Override
    public void removeOrder(String orderNumber) throws FlooringMasterDaoPersistenceExeption {
        FlooringMasterOrder wantedOrder = orderList.get(orderNumber);
        wantedOrder.setOrderTotalCost(new BigDecimal("0.00"));
    }

    @Override
    public void editOrder(FlooringMasterOrder order) throws FlooringMasterDaoPersistenceExeption {
        orderList.put(order.getOrderNumber(), order);
    }

    @Override
    public void addOrder(FlooringMasterOrder order) throws FlooringMasterDaoPersistenceExeption {
        orderList.put(order.getOrderNumber(), order);
    }

    public void loadOrders(String orderDate)
            throws FlooringMasterDaoPersistenceExeption {

        String ORDER_FILE = "Orders_" + orderDate + ".txt";
        File f = new File("Orders_" + orderDate + ".txt");
        if (f.exists()) {

            boolean loadData;
            loadData = verifyToLoad(orderDate);

            if (loadData == false) {
                Scanner scanner;
                try {
                    scanner = new Scanner(new BufferedReader(new FileReader(ORDER_FILE)));
                } catch (FileNotFoundException e) {
                    throw new FlooringMasterDaoPersistenceExeption(
                            "Unable to load Orders Data", e);
                }

                String currentLine;
                String[] currentTokens;

                scanner.nextLine();

                while (scanner.hasNext()) {
                    currentLine = scanner.nextLine();
                    currentTokens = currentLine.split(DELIMITER);

                    if (currentTokens.length == 12) {
                        //Creates the order object
                        FlooringMasterOrder currentItem = new FlooringMasterOrder(currentTokens[0]);

                        currentItem.setCustomerName(currentTokens[1]);

                        //creates the State Tax object to be passed to the Order Object
                        FlooringMasterStateTax currentStateTax = new FlooringMasterStateTax(currentTokens[2]);
                        currentStateTax.setTaxRate(new BigDecimal(currentTokens[3]));

                        //sets  State with the StateTaxObject
                        currentItem.setState(currentStateTax);

                        // creates the Product Object
                        FlooringMasterInventory currentInventoryItem
                                = new FlooringMasterInventory(currentTokens[4]);

                        currentInventoryItem.setCostPerSqFt(new BigDecimal(currentTokens[6]));
                        currentInventoryItem.setLaborCostPerSqFt(new BigDecimal(currentTokens[7]));

                        // sets the Order productType variable to the Inventory Object.
                        currentItem.setProductType(currentInventoryItem);

                        //Sets the remaing OrderVariables
                        currentItem.setworkArea(new BigDecimal(currentTokens[5]));
                        currentItem.setMaterialCost(new BigDecimal(currentTokens[8]));
                        currentItem.setLaborCost(new BigDecimal(currentTokens[9]));
                        currentItem.setOrderTaxsCharged(new BigDecimal(currentTokens[10]));
                        currentItem.setOrderTotalCost(new BigDecimal(currentTokens[11]));

                        orderList.put(currentTokens[0], currentItem);
                    }
                }

                scanner.close();
            }
        }

    }

    @Override
    public void writeData() throws FlooringMasterDaoPersistenceExeption {
        PrintWriter out;
        Set<String> orderDate = new HashSet<>(setDatesToWrite());
        if (orderDate.size() > 0) {
            for (String o : orderDate) {
                String ORDER_FILE = "Orders_" + o + ".txt";

                try {
                    out = new PrintWriter(new FileWriter(ORDER_FILE));
                } catch (IOException e) {
                    throw new FlooringMasterDaoPersistenceExeption(
                            "Could not save order data.", e);
                }

                Set<FlooringMasterOrder> allOrders = new HashSet<>(this.listAllOrders(o));
                for (FlooringMasterOrder order : allOrders) {
                    out.println(order.getOrderNumber() + DELIMITER
                            + order.getCustomerName() + DELIMITER
                            + order.getState().getCustomerState() + DELIMITER
                            + order.getState().getTaxRate() + DELIMITER
                            + order.getProductType().getProductType() + DELIMITER
                            + order.getworkArea() + DELIMITER
                            + order.getProductType().getCostPerSqFt() + DELIMITER
                            + order.getProductType().getLaborCostPerSqFt() + DELIMITER
                            + order.getMaterialCost() + DELIMITER
                            + order.getLaborCost() + DELIMITER
                            + order.getOrderTaxsCharged() + DELIMITER
                            + order.getOrderTotalCost());

                    out.flush();
                }
                out.close();
            }
        }
    }

    private boolean verifyToLoad(String orderDate) {
        return orderList.keySet()
                .stream()
                .anyMatch(k -> k.substring(0, 7).equals(orderDate));

    }

    private Set<String> setDatesToWrite() {
        Set<String> keys = orderList.keySet();
        Set<String> orderDate = new HashSet<>();
        for (String k : keys) {
            k = k.substring(0, 8);
            orderDate.add(k);
        }
        return orderDate;
    }

}
