/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dampizza.views.user.manager;

import com.dampizza.App;
import static com.dampizza.App.MANAGER_ORDER_VIEW;
import com.dampizza.DrawerManager;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.application.ViewStackPolicy;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.control.NavigationDrawer;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

/**
 *
 * @author 2dam
 */
public class ManagerSelectDealerPresenter {
    
    @FXML
    private View primary;
    
   
    
     public void initialize() {
        
      primary.showingProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) {
                AppBar appBar = MobileApplication.getInstance().getAppBar();
                
                appBar.setVisible(true);
                
                appBar.getActionItems().add(MaterialDesignIcon.ARROW_BACK.button(e -> 
                        navegacion()));
                appBar.setTitleText("Manager order"); 
            }
        });
      
      
    }
     
    public void navegacion(){
          NavigationDrawer.ViewItem Item = new NavigationDrawer.ViewItem("Select", MaterialDesignIcon.HOME.graphic(), MANAGER_ORDER_VIEW, ViewStackPolicy.SKIP);
          DrawerManager drawer = new DrawerManager();
          drawer.updateView(Item);
      }
     
 
}

