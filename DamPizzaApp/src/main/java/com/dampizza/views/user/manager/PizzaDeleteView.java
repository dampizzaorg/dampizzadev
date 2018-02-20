/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dampizza.views.user.manager;

import com.dampizza.views.user.common.PizzaCreateView;
import com.gluonhq.charm.glisten.mvc.View;
import java.io.IOException;
import javafx.fxml.FXMLLoader;

/**
 *
 * @author Ismael
 */
public class PizzaDeleteView {
    private final String name;
    
    public PizzaDeleteView(String name) {
        this.name = name;
    }
    
    /**
     * Load and returns fxml view.
     * @return view object
     */
    public View getView() {
        try {
            View view = FXMLLoader.load(PizzaCreateView.class.getResource("/com/dampizza/views/user/manager/pizza_delete_view.fxml"));
            view.setName(name);
            return view;
        } catch (IOException e) {
            System.out.println("IOException: " + e);
            return new View(name);
        }
    }
}
