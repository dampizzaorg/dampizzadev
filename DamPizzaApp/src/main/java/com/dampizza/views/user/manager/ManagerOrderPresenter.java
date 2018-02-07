/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dampizza.views.user.manager;

import com.dampizza.App;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.control.TextArea;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 *
 * @author 2dam
 */
public class ManagerOrderPresenter {
    
    @FXML
    private View primary;
    
    @FXML
    private TextArea taOrder;
    
    @FXML
    private Button btnSelect,btnConfirm;
    
     public void initialize() {
        
      primary.showingProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) {
                AppBar appBar = MobileApplication.getInstance().getAppBar();
                
                appBar.setVisible(true);
                
                appBar.setNavIcon(MaterialDesignIcon.MENU.button(e -> 
                        MobileApplication.getInstance().showLayer(App.MENU_LAYER)));
                appBar.setTitleText("Manager order"); 
            }
        });
      
      editTextArea();
      
      
    }
     
     public void editTextArea(){
         
//         taOrder.setText("sgshjfsghsfjhsgfsghfsghjfsghjsfghsfghsfhg");
         
     }
     
     public void select(){
         
         
         
     }
     
     public void confirm(){
         
         
         
     }
}
