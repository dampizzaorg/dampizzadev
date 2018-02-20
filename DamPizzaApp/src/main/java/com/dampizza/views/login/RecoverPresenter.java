package com.dampizza.views.login;

import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import com.dampizza.App;
import static com.dampizza.App.LOGIN_VIEW;
import com.dampizza.DrawerManager;
import com.dampizza.exception.user.UserQueryException;
import com.dampizza.logic.imp.UserManagerImp;
import com.dampizza.logic.io.UserManagerInterface;
import com.dampizza.util.MailUtil;
import com.dampizza.util.PasswordGenerator;
import com.gluonhq.charm.glisten.application.ViewStackPolicy;
import com.gluonhq.charm.glisten.control.Alert;
import com.gluonhq.charm.glisten.control.NavigationDrawer;
import com.gluonhq.charm.glisten.control.TextField;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import static javafx.scene.control.ButtonBar.ButtonData.OK_DONE;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

/**
 * Presenter for recover.fxml
 *
 * @author ???
 */
public class RecoverPresenter {
    // <editor-fold defaultstate="collapsed" desc="@FXML NODES">
    @FXML
    private View primary;
    @FXML
    private TextField tfEmail;
    @FXML
    private Label lbEmail;
    @FXML
    private ImageView imgBackground;
    //</editor-fold>
    private UserManagerInterface userManager;
    private Alert alert;

    public void initialize() {
        userManager = new UserManagerImp();
        imgBackground.setImage(new Image("/img/pizza_avatar_128.png"));
        primary.showingProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) {
                AppBar appBar = MobileApplication.getInstance().getAppBar();

                /* Leave on true for testing, change to false when the app is finished */
                appBar.setVisible(true);

                appBar.setNavIcon(MaterialDesignIcon.MENU.button(e
                        -> MobileApplication.getInstance().showLayer(App.MENU_LAYER)));
                appBar.setTitleText("Recover");
                /*appBar.getActionItems().add(MaterialDesignIcon.CASINO.button(e
                        -> System.out.println("Search")));*/

            }
        });
    }

    /**
     * Method to change change the password if the user forget it
     */
    @FXML
    void recoverPassword() {
        if (tfEmail.getText().equals("")) {
            //The field email is void
            checkViodFields();
            alert = new Alert(AlertType.INFORMATION, "Please write your email before");
            alert.showAndWait();
        } else {
            //The field is not void
            try {
                //Check the email exist
                Integer exist = checkEmail();
                if (exist == 1) {
                    //the email exist, then ask if the user wnt to recover the password
                    alert = new Alert(AlertType.CONFIRMATION, "Are you sure you want to recover the password");
                    Optional<ButtonType> result = alert.showAndWait();
                    if (!result.get().getButtonData().isCancelButton()) {
                        ////If the user click on the accept button, generate a new password and send an email
                        PasswordGenerator.generateRandomPassword(tfEmail.getText());
                        alert= new Alert(AlertType.INFORMATION,"Your password has change, we send you an email with the new password");
                        alert.showAndWait();
                        tfEmail.setText("");
                        goLogin();
                    }else{
                        alert= new Alert(AlertType.INFORMATION, "Your password will not change");
                    }
                } else {
                    //if the email dosent exist
                    alert = new Alert(AlertType.WARNING, "Wrong email, this email dosent exist on the DB");
                    alert.showAndWait();
                }
            } catch (UserQueryException ex) {
                Logger.getLogger(RecoverPresenter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    /**
     * Method to check if the email exist on the DB
     * @return 1 if the email exist, 2 if the email does not exist
     * @throws UserQueryException 
     */
    private Integer checkEmail() throws UserQueryException {
        return userManager.emailExist(tfEmail.getText());
    }
    
    private void checkViodFields(){
        String email= tfEmail.getText().trim();
        
        if(email.equals("")){
            lbEmail.setTextFill(Color.RED);
        }else{
            lbEmail.setTextFill(Color.BLACK);
        }
    }
    /**
     * Method to go to the login
     */
    private void goLogin() {
        NavigationDrawer.ViewItem loginItem = new NavigationDrawer.ViewItem("Login", MaterialDesignIcon.HOME.graphic(), LOGIN_VIEW, ViewStackPolicy.SKIP);
        DrawerManager drawer = new DrawerManager();
        drawer.updateView(loginItem);
    }

}
