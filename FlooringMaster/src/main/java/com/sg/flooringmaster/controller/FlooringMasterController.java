/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmaster.controller;

import com.sg.flooringmaster.dao.FlooringMasterDaoPersistenceExeption;
import com.sg.flooringmaster.dto.FlooringMasterInventory;
import com.sg.flooringmaster.dto.FlooringMasterOrder;
import com.sg.flooringmaster.dto.FlooringMasterStateTax;
import com.sg.flooringmaster.servicelayer.FlooringMasterServiceLayer;
import com.sg.flooringmaster.ui.View;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Set;

/**
 *
 * @author Eddie
 */
public class FlooringMasterController {
    
    FlooringMasterServiceLayer srv;
    View view;
    
    public FlooringMasterController(View view, FlooringMasterServiceLayer srv){
        this.view = view;
        this.srv = srv;
    }
    public void run() throws FlooringMasterDaoPersistenceExeption{
        boolean keepRunning = true;
        
        while(keepRunning){
            
            int userAction = getUserAction();
            
            switch(userAction) {
                case 1:
                    startDisplayOrders();
                    break;
                 
                case 2:
                    startAddNewOrder();
                    break;
                    
                case 3: 
                    startEditOrder();
                    break;
                    
                case 4:
                    startRemoveOrder();
                    break;
                    
                case 5:
                    startSaveProgress();
                    break;
                    
                case 6:
                    System.out.println("All work has been saved.");
                    System.out.println("Good-Bye");
                    startSaveProgress();
                    keepRunning = false;
                    break;
                    
                default:
                    System.out.println("UNKOWN COMMAND");
            }
        }
    }
    
    private int getUserAction(){
        int returnItem = view.getUserAction();
        return returnItem;
        
    }
    
    private void startDisplayOrders() throws FlooringMasterDaoPersistenceExeption{
        LocalDate ordersDate = view.getDisplayOrdersDate();
        String formattedDate = srv.formatDate(ordersDate);
        Set<FlooringMasterOrder> orders = srv.getAllOrdersForDate(formattedDate);
        view.displayDisplayOrders(orders);
    }
    
    private void startAddNewOrder() throws FlooringMasterDaoPersistenceExeption{
        String newOrderNumber = srv.createOrderNumber();
        FlooringMasterOrder newOrder = view.getNewOrderInfo(newOrderNumber);
        ArrayList<FlooringMasterStateTax> stateTaxes = new ArrayList<>(srv.getAllStateTax());
        ArrayList<FlooringMasterInventory> productList = new ArrayList<>(srv.getAllInventory());
        FlooringMasterStateTax custState = view.getNewOrderStateTax(stateTaxes);
        FlooringMasterInventory product = view.getNewOrderProduct(productList);
        boolean orderConfirmed = view.confirmNewOrder(newOrder, product, custState);
        if(orderConfirmed == true)
            srv.addNewOrder(newOrder, product, custState);
    }
    
    private void startEditOrder() throws FlooringMasterDaoPersistenceExeption{
        LocalDate orderDate = view.getEditOrderDate();
        String orderNumber = view.getEditOrderNumber();
        
        // Next 3 lines get the order, stateList and Inventory List
        FlooringMasterOrder wantedOrder = srv.getSingleOrder(orderNumber, orderDate);
        ArrayList<FlooringMasterStateTax> stateTaxes = new ArrayList<>(srv.getAllStateTax());
        ArrayList<FlooringMasterInventory> productList = new ArrayList<>(srv.getAllInventory());
        
        //Updates the Inventory and StateTax objects
        wantedOrder = view.editOrderMain(wantedOrder);
        FlooringMasterStateTax updatedTaxes = 
                view.editOrderState(wantedOrder.getState(),stateTaxes);
        FlooringMasterInventory updatedInventory = 
                view.editOrderInventory(
                        wantedOrder.getProductType(),productList);
        
        srv.addNewOrder(wantedOrder, updatedInventory, updatedTaxes);
    }
    
    private void startRemoveOrder() throws FlooringMasterDaoPersistenceExeption{
        LocalDate orderDate = view.getEditOrderDate();
        String orderNumber = view.getEditOrderNumber();
        FlooringMasterOrder wantedOrder = 
                srv.getSingleOrder(orderNumber, orderDate);
        view.displayOrderForRemoval(wantedOrder);
        String confirm = view.confirmOrderRemoval(wantedOrder);
        srv.removeOrder(confirm);
        
    }
    
    private void startSaveProgress() throws FlooringMasterDaoPersistenceExeption{
        srv.writeData();
    }
    
}
