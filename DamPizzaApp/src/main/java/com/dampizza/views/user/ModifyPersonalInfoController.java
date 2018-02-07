/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dampizza.views.user;

import com.dampizza.App;
import com.dampizza.exception.user.UserQueryException;
import com.dampizza.exception.user.UserUpdateException;
import com.dampizza.logic.dto.UserDTO;
import com.dampizza.logic.imp.UserManagerImp;
import com.dampizza.util.EmailValidator;
import com.dampizza.util.EncrypterUtil;
import com.dampizza.util.MailUtil;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.Alert;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.control.TextField;
import com.gluonhq.charm.glisten.control.Toast;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import com.gluonhq.impl.charm.a.b.b.s;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    private static final Logger logger =Logger.getLogger(ModifyPersonalInfoController.class.getName());

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
               
                //Puts a prompt text on password fields
                tfPassword.setPromptText("Password here");
                tfRepeatPassword.setPromptText("Repeat here");
            
                //Load the textField with the current user information.
                tfUserName.setText(App.userLoggedIn.getCredential().getUsername());
                tfName.setText(App.userLoggedIn.getName());
                String[] surnames = App.userLoggedIn.getSurnames().split("%");
                tfFirstSurName.setText(surnames[0]);
                tfSecondSurName.setText(surnames[1]);
                tfDirection.setText(App.userLoggedIn.getAddress());
                tfEmail.setText(App.userLoggedIn.getEmail());           
            }    
     });
    }
    
  
    
    @FXML
    void handlerModifyProfile(){
        //Checks if any field is empty so it can show an alert to the user.
        if(tfName.getText().equals("")||tfFirstSurName.getText().equals("")||
           tfSecondSurName.getText().equals("")||tfDirection.getText().equals("")||
           tfEmail.getText().equals("")){
           //Alert that notice the user that at least oen field is empty
            Alert alert = new Alert(javafx.scene.control.Alert.AlertType.INFORMATION, "Please write your email before");
            alert.showAndWait();
        }else{
            try {
                //Checks if the email is correct
                if(new EmailValidator().validate(tfEmail.getText())){
                    //load a UserDTO class with the current information in the fields
                    UserDTO user= new UserDTO(tfUserName.getText(),tfName.getText(),
                            tfFirstSurName.getText()+"%"+tfSecondSurName.getText(),
                            tfEmail.getText(), tfDirection.getText());
                    //Call to the DB to change the user information
                    new UserManagerImp().updateUser(user);
                    logger.info(tfName.getText() + " user updated");
                    //Checks that the fields are not empty, have same value and matches with pattern,
                    if((!(tfPassword.getText().equals("")||tfRepeatPassword.getText().equals(""))) &&
                            tfPassword.getText().equals(tfRepeatPassword.getText())/* &&
                            PATRONCONTRASEÑAS*/){
                        //Update user password
                        new UserManagerImp().changePassword(tfName.getText(),EncrypterUtil.encrypt(tfPassword.getText()));
                        logger.info(tfName.getText() + " password changed");
                        //Send an email to the user saying that the password has been changed
                        MailUtil.sendEmail(tfEmail.getText(), "Password changed", "Your password have changed, if you didn´t do it"
                                + " put in contact with our customer service.");
                    //Check that passwords got same value
                    }else if(!tfPassword.getText().equals(tfRepeatPassword.getText())){
                          Alert alert = new Alert(javafx.scene.control.Alert.AlertType.INFORMATION, "Please write same password");
                          alert.showAndWait();
                    //Password are the same but dont match with pattern
                    }/*else if (no coincide patron){
                        Alert alert = new Alert(javafx.scene.control.Alert.AlertType.WARNING, "Pasword has to be valid, a valid pasword contains at least : 8 characters, one capital letter"
                                + ", one small letter, one number  and one symbol");
                        alert.showAndWait();
                    }*/
                }
            } catch (UserUpdateException ex) {
                //Put in the logger the information of the error
                logger.severe(ex.getMessage());
                //Alert with the current error
                Alert alert = new Alert(javafx.scene.control.Alert.AlertType.ERROR, ex.getMessage());
                alert.showAndWait();
            }
        }
    }
}

                
