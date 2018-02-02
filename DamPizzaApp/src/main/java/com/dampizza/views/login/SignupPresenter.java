package com.dampizza.views.login;

import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import com.dampizza.App;
import com.dampizza.logic.dto.UserDTO;
import com.dampizza.logic.imp.UserManagerImp;
import com.gluonhq.charm.glisten.control.TextField;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

/**
 * Presenter for signup.fxml
 * 
 * @author Carlos Santos
 */
public class SignupPresenter {
    
    // <editor-fold defaultstate="collapsed" desc="@FXML NODES">
    @FXML
    private View primary;
    @FXML
    private TextField tfUserName;
    @FXML
    private TextField tfName;
    @FXML
    private TextField tfFirstSurName;
    @FXML
    private TextField tfSecondSurName;
    @FXML
    private TextField tfAddress;
    @FXML
    private TextField tfEmail;
    @FXML
    private PasswordField pfPassword;
    @FXML
    private PasswordField pfRepeatPassword;
    
    @FXML
    private Button btnSignUp;
    // </editor-fold>

    public void initialize() {
        
        primary.showingProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) {
                AppBar appBar = MobileApplication.getInstance().getAppBar();
                
                /* Leave on true for testing, change to false when the app is finished */
                appBar.setVisible(true);
                
                appBar.setNavIcon(MaterialDesignIcon.MENU.button(e -> 
                        MobileApplication.getInstance().showLayer(App.MENU_LAYER)));
                appBar.setTitleText("SignUp");
                appBar.getActionItems().add(MaterialDesignIcon.SEARCH.button(e -> 
                        System.out.println("Search")));
                
            }
        });
    }
    
    @FXML
    public void btnActionSignUp() {
        if(formValid()){
            String surname = tfFirstSurName.getText() + " " + tfSecondSurName.getText();
            
            // TODO ENCRYPT PASSWORD
            
            UserDTO user = new UserDTO(tfUserName.getText(),tfName.getText(),surname,tfEmail.getText(),tfAddress.getText());
            new UserManagerImp().createUser(user, pfPassword.getText());
            
            // TODO SEND EMAIL AFTER REGISTRATION
        }
    }
    
    public boolean formValid(){
        boolean valid = true;
        
        // TODO SIGNUP FIELDS VALIDATION
        
        return valid;
    }
    
}
