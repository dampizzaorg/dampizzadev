package com.dampizza.views.login;

import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import com.dampizza.App;
import static com.dampizza.App.CUSTOMER_VIEW;
import static com.dampizza.App.RECOVER_VIEW;
import static com.dampizza.App.SIGNUP_VIEW;
import com.dampizza.DrawerManager;
import com.gluonhq.charm.glisten.application.ViewStackPolicy;
import com.gluonhq.charm.glisten.control.Alert;
import com.gluonhq.charm.glisten.control.NavigationDrawer.ViewItem;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import com.gluonhq.charm.glisten.control.TextField;
import java.io.IOException;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Hyperlink;



/**
 * Presenter for login.fxml
 *
 * @author Carlos Santos
 */
public class LoginPresenter {

    @FXML
    private View primary;

    @FXML
    private Label label;

    @FXML
    private Button btnLogin;
    
    @FXML
    private ImageView ivDampizza;

    @FXML
    private TextField tfUsername, tfPassword;
    
    @FXML
    private Hyperlink hyRegistered, hyForgot;

    public void initialize() {

        primary.showingProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) {
                AppBar appBar = MobileApplication.getInstance().getAppBar();

                /* Leave on true for testing, change to false when the app is finished */
                appBar.setVisible(false);

                appBar.setNavIcon(MaterialDesignIcon.MENU.button(e
                        -> MobileApplication.getInstance().showLayer(App.MENU_LAYER)));
                appBar.setTitleText("Login");
                appBar.getActionItems().add(MaterialDesignIcon.SEARCH.button(e
                        -> System.out.println("Search")));
                
                ivDampizza.setImage(new Image("/img/pizza_avatar_128.png"));

            }
        });
    }

    @FXML
    void buttonClick() {
        label.setText("ESKETIT!");
    }

    @FXML
    void login()  {
        if (tfUsername.getText().equals("") || tfPassword.getText().equals("")) {
            Alert alert = new Alert(javafx.scene.control.Alert.AlertType.INFORMATION, "Please write your user and password");
            alert.showAndWait();
        } else {
                 ViewItem loginItem = new ViewItem("Login", MaterialDesignIcon.HOME.graphic(), CUSTOMER_VIEW, ViewStackPolicy.SKIP);
               DrawerManager drawer=new DrawerManager();
               drawer.updateView(loginItem);
               

        }

    }
    
    @FXML
    void recover(){
        
         ViewItem loginItem = new ViewItem("Login", MaterialDesignIcon.HOME.graphic(), RECOVER_VIEW, ViewStackPolicy.SKIP);
               DrawerManager drawer=new DrawerManager();
               drawer.updateView(loginItem);
        
    }
    
     @FXML
    void register(){
         ViewItem loginItem = new ViewItem("Login", MaterialDesignIcon.HOME.graphic(), SIGNUP_VIEW, ViewStackPolicy.SKIP);
               DrawerManager drawer=new DrawerManager();
               drawer.updateView(loginItem);  
    }

}
