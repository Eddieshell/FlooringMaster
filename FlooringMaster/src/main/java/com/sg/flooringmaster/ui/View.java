/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmaster.ui;

import com.sg.flooringmaster.dto.FlooringMasterInventory;
import com.sg.flooringmaster.dto.FlooringMasterOrder;
import com.sg.flooringmaster.dto.FlooringMasterStateTax;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Set;

/**
 *
 * @author Eddie
 */
public class View {
    
    UserIO io;
    
    /**
     *
     * @param io = UserIOImpl
     */
    public View(UserIO io){
        this.io = io;
    }
    
    public int getUserAction(){
       io.print("**********************************");
       io.print("* <<Flooring Program>>");
       io.print("*1. Display Orders");
       io.print("*2. Add an Order");
       io.print("*3. Edit an Order");
       io.print("*4. Remove an Order");
       io.print("*5. Save Current Work");
       io.print("*6. Quit");
       io.print("*");
       io.print("**********************************");
       int choice = io.readInt("Enter selection from above menu.", 1,6);
       return choice;
               
    }
    
    public LocalDate getDisplayOrdersDate(){
        String orderDate = io.readString("=== Enter the date of the orders you want displayed.===");
        LocalDate returnItem = LocalDate.parse(orderDate, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        return returnItem;
    } 
    
    public void displayDisplayOrders(Set<FlooringMasterOrder> orders){
        if(orders.size()>0){
        orders.stream()
                .forEach(o -> System.out.println("Order Number:  "+o.getOrderNumber() +"\n" +
                        "Customer Name:         "+o.getCustomerName() + "\n" +
                        "State:                 "+o.getState().getCustomerState() + "\n" +
                        "Tax Rate:              "+o.getState().getTaxRate() + "\n" +
                        "Coverage Area:         "+o.getworkArea() + "\n" +
                        "Product:               "+o.getProductType().getProductType() + "\n" +
                        "Product Cost per Sqft: "+o.getProductType().getCostPerSqFt() + "\n" +
                        "Labor CostPer Sqft:    "+o.getProductType().getLaborCostPerSqFt() + "\n" +
                        "Total Materials Cost:  "+o.getMaterialCost() + "\n" +
                        "Total Labor Cost:      "+o.getLaborCost() + "\n" +
                        "State Taxes:           "+o.getOrderTaxsCharged() + "\n" +
                        "Totle OrderAmount:     "+o.getOrderTotalCost()));
        io.print("");
        io.readString("Press <ENTER> to return to main menu.");
    } else {
            System.out.println("There are no orders for selected date.");
            io.readString("Press <ENTER> to return to main menu.");
        }
    }
    
    public FlooringMasterOrder getNewOrderInfo(String newOrderNumber){
        FlooringMasterOrder newOrder = new FlooringMasterOrder(newOrderNumber);
        String custName=io.readString("Enter the customers name.");       
        String area=io.readString("Enter amount of flooring ordered in SQFT.");        
        newOrder.setCustomerName(custName);
        newOrder.setworkArea(new BigDecimal(area));
        return newOrder;        
    }
    
    public FlooringMasterStateTax getNewOrderStateTax(ArrayList<FlooringMasterStateTax> taxList){
        
        for( int i = 0; i < taxList.size(); i++){
            String state= taxList.get(i).getCustomerState();
            io.print((i+1)+". " + state);
        }
        int selectedState = io.readInt("Select a State from Menu.", 1, 4);
        FlooringMasterStateTax custState = taxList.get(selectedState -1);
        return custState;        
    }
    
    public FlooringMasterInventory getNewOrderProduct(ArrayList<FlooringMasterInventory> productsList){
        
        for(int i = 0; i < productsList.size(); i++){
            String product = productsList.get(i).getProductType();
            io.print((i+1)+". " + product);
        }
        
        int selectedProduct = io.readInt("Select product ordered from menu.", 1, 4);
        FlooringMasterInventory orderedProduct = productsList.get(selectedProduct-1);
        return orderedProduct;
    }
    
    public boolean confirmNewOrder(FlooringMasterOrder newOrder,
            FlooringMasterInventory productOrdered,
            FlooringMasterStateTax state){
        boolean orderConfirmed;
        io.print("=== Order Summary ===");        
        io.print("Customer Name: " + newOrder.getCustomerName());
        io.print("Customer State: " + state.getCustomerState());
        io.print("Product Ordered: " + productOrdered.getProductType());
        io.print("Coverage Area in SQFT: "+newOrder.getworkArea());
        String commit = io.readString("Commit Order (Y/N)");
        orderConfirmed = commit.equalsIgnoreCase("Y");
        return orderConfirmed;       
              
    }
    
    public LocalDate getEditOrderDate(){
        String orderDate = io.readString("=== Enter the date of the order.===");
        LocalDate returnItem = LocalDate.parse(orderDate,
                DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        return returnItem;
    }
    
    public String getEditOrderNumber(){
        String orderNumber = io.readString(
                "=== Enter the order number.===");
        return orderNumber;
    }
    
    public FlooringMasterOrder editOrderMain(FlooringMasterOrder orderInfo){
        String editName = io.readString("Enter customer name(" 
                + orderInfo.getCustomerName()+")");
        String area=io.readString("Enter area("+orderInfo.getworkArea()+")"); 
        if(editName.equals("")) {
        } else {
            orderInfo.setCustomerName(editName);
        }
        if(area.equals("")){            
        }else{
       orderInfo.setworkArea(new BigDecimal(area));
        }
       return orderInfo;
    }
    
    public FlooringMasterStateTax editOrderState(FlooringMasterStateTax taxInfo,
            ArrayList<FlooringMasterStateTax> taxList){
        io.print("===EDIT CUSTOMER STATE===");
        for( int i = 0; i < taxList.size(); i++){
            String state= taxList.get(i).getCustomerState();
            io.print((i+1)+". " + state);
        }
        String editedState = io.readString("Enter customer's state(" + taxInfo.getCustomerState()+")");
        
        if(editedState.equals("")){
            return taxInfo;
        }else{
            int findState = Integer.parseInt(editedState);
            taxInfo.setCustomerState(taxList.get(findState-1).getCustomerState());
            return taxInfo;
        }        
    }
    
    public FlooringMasterInventory editOrderInventory(FlooringMasterInventory inventoryInfo,
            ArrayList<FlooringMasterInventory> productsList){
        
        io.print("===EDIT PRODUCT ORDERED===");
                
        for(int i = 0; i < productsList.size(); i++){
            String product = productsList.get(i).getProductType();
            io.print((i+1)+". " + product);
        }
        
        String editedProduct = io.readString("Enter product ordered(" 
                + inventoryInfo.getProductType()+")");
        
        if(editedProduct.equals("")){
            return inventoryInfo;
        }else{
            int findInventory = Integer.parseInt(editedProduct);
            inventoryInfo.setProductType(productsList.get(findInventory-1)
                    .getProductType());
            return inventoryInfo;
        }
    }
    
    public void displayOrderForRemoval(FlooringMasterOrder o){
        if(o != null){
        System.out.println("Order Number:  "+o.getOrderNumber() +"\n" +
                        "Customer Name:         "+o.getCustomerName() + "\n" +
                        "State:                 "+o.getState().getCustomerState() + "\n" +
                        "Tax Rate:              "+o.getState().getTaxRate() + "\n" +
                        "Coverage Area:         "+o.getworkArea() + "\n" +
                        "Product:               "+o.getProductType().getProductType() + "\n" +
                        "Product Cost per Sqft: "+o.getProductType().getCostPerSqFt() + "\n" +
                        "Labor CostPer Sqft:    "+o.getProductType().getLaborCostPerSqFt() + "\n" +
                        "Total Materials Cost:  "+o.getMaterialCost() + "\n" +
                        "Total Labor Cost:      "+o.getLaborCost() + "\n" +
                        "State Taxes:           "+o.getOrderTaxsCharged() + "\n" +
                        "Totle OrderAmount:     "+o.getOrderTotalCost());
        io.print("");
        
    } else {
            System.out.println("There are no orders for selected date.");
            io.readString("Press <ENTER> to return to main menu.");
        }
    }
    
    public String confirmOrderRemoval(FlooringMasterOrder requestedOrder){
       String confirm = io.readString(
               "Confirm Deletion, this can not be undone! (Y/N)");
        
       if(confirm.equalsIgnoreCase("Y")){
           String returnItem = requestedOrder.getOrderNumber();
           io.print("ORDER HAS BEEN REMOVED");
           return returnItem;
       }else{
           io.print("DELETION CANCELED");
           return null;
       }
       
    }
}
