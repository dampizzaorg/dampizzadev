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
import com.dampizza.util.LogicFactory;
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
 * FXML Controller class for login.fxml
 *
 * @author Ismael
 * 
 * Class that controls the login.fxml.
 *  Class that makes possible for the user
 *  to log in the app.
 */
public class LoginPresenter {

    private static final Logger logger = Logger.getLogger(LoginPresenter.class.getName());
    // <editor-fold defaultstate="collapsed" desc="@FXML NODES">    
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
    //</editor-fold>
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

                ivDampizza.setImage(new Image("/img/pizza_avatar_128.png"));
            }
        });
        
    }

    /**
     * Method 
     */
    @FXML
    void login() {
        Integer value= validateUser();
        //If values is 1 then, the user is correct
         if (value == 1) {
            ViewItem loginItem=null;
            Integer status=LogicFactory.getUserManager().checkStatus(tfUsername.getText());
            //Check what type of user is it and inicialize a item depending on that
            if(status==AppConstants.USER_CUSTOMER){
                logger.info("Customer User");
                loginItem = new ViewItem("Login", MaterialDesignIcon.HOME.graphic(), CUSTOMER_VIEW, ViewStackPolicy.SKIP);
            }else if (status==AppConstants.USER_MANAGER){
                 logger.info("Manager User");
                loginItem = new ViewItem("Login", MaterialDesignIcon.HOME.graphic(),MANAGER_VIEW, ViewStackPolicy.SKIP); 
            }else if (status==AppConstants.USER_DEALER){
                logger.info("Dealer User");
                loginItem = new ViewItem("Login", MaterialDesignIcon.HOME.graphic(), DEALER_VIEW, ViewStackPolicy.SKIP);
            }
            //Inicialize a menu depending on the user.
            DrawerManager drawer = new DrawerManager(status);
            //Clean the fields
            tfUsername.setText("");
            tfPassword.setText("");
            //Update view with previus item
            drawer.updateView(loginItem);
        } else {
            logger.info("Login failed.");
        }
    }
    
    /**
     * Method  sopporting action of  hyForgot hyperLink.
     * It Open the view that handles recover.fxml
     */
    @FXML
    void recover() {
        //Change to recover Password view
        logger.info("Going to recover Password view");
        ViewItem loginItem = new ViewItem("Recover Password", MaterialDesignIcon.HOME.graphic(), RECOVER_VIEW, ViewStackPolicy.SKIP);
        DrawerManager drawer = new DrawerManager();
        drawer.updateView(loginItem);
    }
     /**
     * Method  sopporting action of hyRegistered hyperLink.
     * It Open the view that handles signup.fxml
     */
    @FXML
    void register() {
        //Change to SignUp view
        logger.info("Going to register view");
        ViewItem loginItem = new ViewItem("Sign Up", MaterialDesignIcon.HOME.graphic(), SIGNUP_VIEW, ViewStackPolicy.SKIP);
        DrawerManager drawer = new DrawerManager();
        drawer.updateView(loginItem);
    }

    /**
     * Method that check the user trying to log is correct.Returns
     * a code that indicates the result after this operation.
     * @return Integer value of code after checking credentials
     */
    private Integer validateUser() {
        Integer correct = 1;
        //Check username and password fields are not null
        if (tfUsername.getText().equals("") || tfPassword.getText().equals("")) {
            //Alert informing something is not written
            alert = new Alert(javafx.scene.control.Alert.AlertType.INFORMATION, "Please write your user and password");
            alert.showAndWait();
            correct = 0;
        }else if (correct == 1) {
            //Check username and password are correct.Code 0:Database Error/Code 1:All ok/Code 2:Fields wrong
            logger.info("Checking credentials");
            correct = LogicFactory.getUserManager().checkCredential(tfUsername.getText(), EncrypterUtil.encrypt(tfPassword.getText()));
            logger.info("Check credentials code:"+correct);
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
