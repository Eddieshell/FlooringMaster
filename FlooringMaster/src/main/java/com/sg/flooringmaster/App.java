/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmaster;

import com.sg.flooringmaster.controller.FlooringMasterController;
import com.sg.flooringmaster.dao.FlooringMasterDaoPersistenceExeption;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Eddie
 */
public class App {
    public static void main(String[] args) throws FlooringMasterDaoPersistenceExeption {
        
        ApplicationContext ctx = 
                new ClassPathXmlApplicationContext("applicationContext.xml");
        FlooringMasterController controller =
                ctx.getBean("controller", FlooringMasterController.class);
        controller.run();
        
    }
    
}
