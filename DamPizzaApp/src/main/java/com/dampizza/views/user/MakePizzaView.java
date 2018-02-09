/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dampizza.views.user;

import com.gluonhq.charm.glisten.mvc.View;
import java.io.IOException;
import javafx.fxml.FXMLLoader;

/**
 *
 * @author 2dam
 */
public class MakePizzaView {
    
    private final String name;
    
    public MakePizzaView(String name) {
        this.name = name;
    }
    
    /**
     * Load and returns fxml view.
     * @return view object
     */
    public View getView() {
        try {
            View view = FXMLLoader.load(HistoryView.class.getResource("/com/dampizza/views/user/make_pizza.fxml"));
            view.setName(name);
            return view;
        } catch (IOException e) {
            System.out.println("IOException: " + e);
            return new View(name);
        }
    }
    
    
}
