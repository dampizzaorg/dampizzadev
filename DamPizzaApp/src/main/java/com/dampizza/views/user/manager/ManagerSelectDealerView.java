/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dampizza.views.user.manager;

import com.gluonhq.charm.glisten.mvc.View;
import java.io.IOException;
import javafx.fxml.FXMLLoader;

/**
 *
 * @author 2dam
 */
public class ManagerSelectDealerView {
    

    private final String name;

    public ManagerSelectDealerView(String name) {
        this.name = name;
    }
 /**
     * Load and returns fxml view.
     * @return view object
     */
    public View getView() {
        try {
            View view = FXMLLoader.load(ManagerView.class.getResource("/com/dampizza/views/user/select_dealer.fxml"));
            view.setName(name);
            return view;
        } catch (IOException e) {
            System.out.println("IOException: " + e);
            return new View(name);
        }
    }
}
