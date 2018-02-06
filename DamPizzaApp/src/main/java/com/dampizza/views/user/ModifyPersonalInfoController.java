/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dampizza.views.user;

import com.dampizza.App;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.control.TextField;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

/**
 * FXML Controller class
 *
 * @author 2dam
 */
public class ModifyPersonalInfoController implements Initializable {

    @FXML
    private Label lbUserName;
    @FXML
    private Label lbName;
    @FXML
    private Label lbFirstSurname;
    @FXML
    private Label lbSecondSurname;
    @FXML
    private Label lbDirection;
    @FXML
    private Label lbEmail;
    @FXML
    private Label lbPassword;
    @FXML
    private Label lbRepeatPassword;
    @FXML
    private Button btnSave;
    @FXML
    private TextField tfUserName;
    @FXML
    private TextField tfName;
    @FXML
    private TextField tfFirstSurName;
    @FXML
    private TextField tfSecondSurName;
    @FXML
    private TextField tfDirection;
    @FXML
    private TextField tfEmail;
    @FXML
    private PasswordField tfPassword;
    @FXML
    private PasswordField tfRepeatPassword;
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

                
