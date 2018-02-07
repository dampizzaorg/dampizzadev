/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dampizza.views.user;

import com.dampizza.App;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.control.CharmListView;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author 2dam
 */
public class HistoryViewController implements Initializable {

    @FXML
    private CharmListView<?, ? extends Comparable> lvPedidos;
    @FXML
    private View primary;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       primary.showingProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) {
                AppBar appBar = MobileApplication.getInstance().getAppBar();
                
                appBar.setVisible(true);
                
                appBar.setNavIcon(MaterialDesignIcon.MENU.button(e -> 
                        MobileApplication.getInstance().showLayer(App.MENU_LAYER)));
                appBar.setTitleText("Customer");
                appBar.getActionItems().add(MaterialDesignIcon.SEARCH.button(e -> 
                        System.out.println("Search")));
                
            }
        });
    }  
    
}
