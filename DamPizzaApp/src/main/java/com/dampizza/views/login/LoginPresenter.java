package com.dampizza.views.login;

import com.dampizza.App;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import static com.dampizza.App.CUSTOMER_VIEW;
import static com.dampizza.App.DEALER_VIEW;
import static com.dampizza.App.MANAGER_DEALER_VIEW;
import static com.dampizza.App.MANAGER_VIEW;
import static com.dampizza.App.PIZZA_CREATE_VIEW;
import static com.dampizza.App.RECOVER_VIEW;
import static com.dampizza.App.SIGNUP_VIEW;
import com.dampizza.DrawerManager;
import com.dampizza.cfg.AppConstants;
import com.dampizza.logic.imp.UserManagerImp;
import com.dampizza.logic.io.UserManagerInterface;
import com.dampizza.util.EncrypterUtil;
import com.dampizza.util.MailUtil;
import com.gluonhq.charm.glisten.application.ViewStackPolicy;
import com.gluonhq.charm.glisten.control.Alert;
import com.gluonhq.charm.glisten.control.CharmListView;
import com.gluonhq.charm.glisten.control.NavigationDrawer.ViewItem;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import com.gluonhq.charm.glisten.control.TextField;
import java.util.logging.Logger;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;

/**
 * Presenter for login.fxml
 *
 * @author ???
 */
public class LoginPresenter {

    private static final Logger logger = Logger.getLogger(LoginPresenter.class.getName());

    @FXML
    private View primary;

    @FXML
    private Label label;

    @FXML
    private Button btnLogin;

    @FXML
    private ImageView ivDampizza;

    @FXML
    private TextField tfUsername;

    @FXML
    private PasswordField tfPassword;

    @FXML
    private Hyperlink hyRegistered, hyForgot;

    private UserManagerInterface userManager;

    private Alert alert;

    public void initialize() {

        primary.showingProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) {
                AppBar appBar = MobileApplication.getInstance().getAppBar();

                /* Leave on true for testing, change to false when the app is finished */
                appBar.setVisible(false);

                appBar.setNavIcon(MaterialDesignIcon.ARROW_BACK.button(e
                        -> System.out.println("Search")));
                appBar.setTitleText("Login");
                //appBar.getActionItems().add(MaterialDesignIcon.ARROW_BACK.button(e
                // -> System.out.println("Search")));

                ivDampizza.setImage(new Image("/img/pizza_avatar_128.png"));
            }
        });
        userManager = new UserManagerImp();
        
    }

    @FXML
    void buttonClick() {
       
    }
    

    @FXML
    void login() {

        
        Integer value= validateUser();



        //If values is 1 then, the user is correct
        if (value == 1) {
            ViewItem loginItem=null;
            Integer status=userManager.checkStatus(tfUsername.getText());
            if(status==AppConstants.USER_CUSTOMER){
                loginItem = new ViewItem("Login", MaterialDesignIcon.HOME.graphic(), CUSTOMER_VIEW, ViewStackPolicy.SKIP);
            }else if (status==AppConstants.USER_MANAGER){
                loginItem = new ViewItem("Login", MaterialDesignIcon.HOME.graphic(),MANAGER_VIEW, ViewStackPolicy.SKIP);
                //loginItem = new ViewItem("Login", MaterialDesignIcon.HOME.graphic(),CREATE, ViewStackPolicy.SKIP);PIZZA_CREATE_VIEW
            }else if (status==AppConstants.USER_DEALER){
                loginItem = new ViewItem("Login", MaterialDesignIcon.HOME.graphic(), DEALER_VIEW, ViewStackPolicy.SKIP);
            }
            DrawerManager drawer = new DrawerManager(status);
            tfUsername.setText("");
            tfPassword.setText("");
            drawer.updateView(loginItem);
            //MobileApplication.getInstance().switchView("HOME_VIEW");
            //If the value is 0 then the user is not correct
        } else {
            logger.info("Login failed.");
        }
    }

    @FXML
    void recover() {
        ViewItem loginItem = new ViewItem("Login", MaterialDesignIcon.HOME.graphic(), RECOVER_VIEW, ViewStackPolicy.SKIP);
        DrawerManager drawer = new DrawerManager();
        drawer.updateView(loginItem);

    }

    @FXML
    void register() {
        ViewItem loginItem = new ViewItem("Login", MaterialDesignIcon.HOME.graphic(), SIGNUP_VIEW, ViewStackPolicy.SKIP);
        DrawerManager drawer = new DrawerManager();
        drawer.updateView(loginItem);
    }

    private Integer validateUser() {
        //Boolean correct=true;
        Integer correct = 1;
        //Check username and password fields are not null
        if (tfUsername.getText().equals("") || tfPassword.getText().equals("")) {
            System.out.println(tfUsername.getText());
            System.out.println(tfPassword.getText());
            alert = new Alert(javafx.scene.control.Alert.AlertType.INFORMATION, "Please write your user and password");
            alert.showAndWait();
            correct = 0;
            //Check username and password are correct 

        } else if (correct == 1) {
            correct = userManager.checkCredential(tfUsername.getText(), EncrypterUtil.encrypt(tfPassword.getText()));
            if (correct == 2) {
                alert = new Alert(javafx.scene.control.Alert.AlertType.INFORMATION, "Some field values are not correct");
                alert.showAndWait();
            } else if (correct == 0) {
                alert = new Alert(javafx.scene.control.Alert.AlertType.INFORMATION, "Has ocurred an error");
                alert.showAndWait();
            }
        }
        return correct;
    }
}
