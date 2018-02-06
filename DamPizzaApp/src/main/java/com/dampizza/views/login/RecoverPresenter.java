 package com.dampizza.views.login;

import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import com.dampizza.App;
import com.dampizza.util.PasswordGenerator;
import com.gluonhq.charm.glisten.control.Alert;
import com.gluonhq.charm.glisten.control.TextField;
import java.util.Optional;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import static javafx.scene.control.ButtonBar.ButtonData.OK_DONE;
import javafx.scene.control.ButtonType;


/**
 * Presenter for recover.fxml
 * 
 * @author Carlos Santos
 */
public class RecoverPresenter {

    @FXML
    private View primary;
    @FXML            
    private TextField tfUserName;

    public void initialize() {
        
        primary.showingProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) {
                AppBar appBar = MobileApplication.getInstance().getAppBar();
                
                /* Leave on true for testing, change to false when the app is finished */
                appBar.setVisible(true);
                
                appBar.setNavIcon(MaterialDesignIcon.MENU.button(e -> 
                        MobileApplication.getInstance().showLayer(App.MENU_LAYER)));
                appBar.setTitleText("Recover");
                appBar.getActionItems().add(MaterialDesignIcon.CASINO.button(e -> 
                        System.out.println("Search")));
                
            }
        });
    }
    
    @FXML
    void recoverPassword() {
         if(tfUserName.getText().equals("")){
            Alert alert = new Alert(AlertType.INFORMATION, "Please write your email before");
            alert.showAndWait();
        }else{
            Alert alert2 = new Alert(AlertType.CONFIRMATION, "Are you sure you want to recover the password");
            Optional<ButtonType> result = alert2.showAndWait();
            if(!result.get().getButtonData().isCancelButton()){
                //Comprobar que el email existe si no salir
                //metodo findbyEmail para la BD
                //if(findbyEmail(tfUsername.getText)){
                PasswordGenerator.generateRandomPassword(tfUserName.getText());
            }
            
            
        }
    }
    
}
