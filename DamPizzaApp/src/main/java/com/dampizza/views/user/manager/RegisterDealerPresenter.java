package com.dampizza.views.user.manager;

// <editor-fold defaultstate="collapsed" desc="Imports">
import com.dampizza.views.login.*;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import com.dampizza.App;
import static com.dampizza.App.LOGIN_VIEW;
import static com.dampizza.App.MANAGER_DEALER_VIEW;
import com.dampizza.DrawerManager;
import com.dampizza.cfg.AppConstants;
import com.dampizza.exception.user.UserCreateException;
import com.dampizza.exception.user.UserQueryException;
import com.dampizza.logic.dto.UserDTO;
import com.dampizza.logic.imp.UserManagerImp;
import com.dampizza.logic.io.UserManagerInterface;
import com.dampizza.util.EncrypterUtil;
import com.dampizza.util.EmailValidator;
import com.dampizza.util.LogicFactory;
import com.dampizza.util.MailUtil;
import com.dampizza.util.PasswordGenerator;
import com.gluonhq.charm.glisten.application.ViewStackPolicy;
import com.gluonhq.charm.glisten.control.Alert;
import com.gluonhq.charm.glisten.control.NavigationDrawer;
import com.gluonhq.charm.glisten.control.NavigationDrawer.ViewItem;
import com.gluonhq.charm.glisten.control.TextArea;
import com.gluonhq.charm.glisten.control.TextField;
import java.io.EOFException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.paint.Color;
//</editor-fold>

/**
 * Presenter for signup.fxml
 *
 * @author Jon Xabier Gimenez
 */
public class RegisterDealerPresenter {

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
    private Button btnSignUp;
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
   
    

    // </editor-fold>
    private UserManagerInterface userManager;

    public void initialize() {
        userManager = new UserManagerImp();
        primary.showingProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) {
                AppBar appBar = MobileApplication.getInstance().getAppBar();

                /* Leave on true for testing, change to false when the app is finished */
                appBar.setVisible(true);

                appBar.setNavIcon(MaterialDesignIcon.MENU.button(e
                        -> MobileApplication.getInstance().showLayer(App.MENU_LAYER)));
                appBar.setTitleText("SignUp");
                appBar.getActionItems().add(MaterialDesignIcon.SEARCH.button(e
                        -> System.out.println("Search")));

            }
        });
        
    }

    @FXML
    public void handlerActionSignUp() throws IOException {
        if (formValid()) {
            //pair the surnames on one string
            
            try {
                //Unify the two surnames and we put % symbol to split them when program present this user
                String surname = tfFirstSurName.getText() + "%" + tfSecondSurName.getText();
                //encrypt the password

                String pw=PasswordGenerator.random();

                //Create and upload the user into the DB with the specified data
                UserDTO user = new UserDTO(tfUserName.getText(), tfName.getText(), surname, tfEmail.getText(), tfAddress.getText());
                //upload the user
                LogicFactory.getUserManager().createUser(user, pw,AppConstants.USER_DEALER);
                //SEND A EMAIL AFTER REGISTRATION FALTA MANDARLE SU USUARIO Y CONTRASEÑA
                String message = "You has request  recover passwod, unfortunaly we "
                                + "cant send you your password.\nThis is yor new password: " + pw + "\n dont forget changing the password before get logged";
                 MailUtil.sendEmail(tfEmail.getText(), "Deliver Man Sign up", message);

            } catch (UserCreateException ex) {
                Logger.getLogger(RegisterDealerPresenter.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UserQueryException ex) {
                Logger.getLogger(RegisterDealerPresenter.class.getName()).log(Level.SEVERE, null, ex);
            }

            goBack();
            clearAll();

        }
    }

    public boolean formValid() {
        boolean valid = true;
        String userName = tfUserName.getText();
        String name = tfName.getText();
        String surname1 = tfFirstSurName.getText();
        String surname2 = tfSecondSurName.getText();
        String address = tfAddress.getText();
        String email = tfEmail.getText();
        //CHECK VALUES ARE NOT NULL
        if (userName.trim().equals("") || name.trim().equals("") || surname1.trim().equals("")
                || surname2.trim().equals("") || address.trim().equals("") || email.trim().equals("")) {
            Alert alert = new Alert(AlertType.WARNING, "Some fields are void");
            alert.showAndWait();
            checkViodFields();
            valid = false;
        }
        try {
            //CHECK USER NAME ONLY IF ALL THE VALUES ARE NOT NULL
            if (valid) {

                valid = userManager.userExists(userName) == 1;
                //IF USER EXIST
                if (valid) {
                    Alert alert = new Alert(AlertType.WARNING, "User name in use");
                    alert.showAndWait();
                    //set user name field void
                    tfUserName.setText("");
                    checkViodFields();
                    valid = false;
                    //IF USER DOES NOT EXIST
                } else {
                    //CHECK EMAIL
                    EmailValidator e = new EmailValidator();
                    if (!e.validate(email)) {
                        //EMAIL ID NOT VALID
                        Alert alert = new Alert(AlertType.WARNING, "wrong email. This is a valid email example: example.email@example.com");
                        alert.showAndWait();
                        //set email void
                        tfEmail.setText("");
                        checkViodFields();
                        valid = false;
                    }
                        
                    
                }
            }
        } catch (UserQueryException ex) {
            Logger.getLogger(RegisterDealerPresenter.class.getName()).log(Level.SEVERE, null, ex);
        }

        return valid;
    }

    private void goBack() {
        ViewItem loginItem = new NavigationDrawer.ViewItem("Login", MaterialDesignIcon.HOME.graphic(), MANAGER_DEALER_VIEW, ViewStackPolicy.SKIP);
        DrawerManager drawer = new DrawerManager();
        drawer.updateView(loginItem);
    }
    
    private void checkViodFields() {
        String userName = tfUserName.getText();
        String name = tfName.getText();
        String surname1 = tfFirstSurName.getText();
        String surname2 = tfSecondSurName.getText();
        String address = tfAddress.getText();
        String email = tfEmail.getText();
       
        //Check if the fields are void or not, if are void, the labels will change to red color and black
        //if they are not void
        if (userName.trim().equals("")) {
            lbUserName.setTextFill(Color.RED);
        } else {
            lbUserName.setTextFill(Color.BLACK);
        }

        if (name.trim().equals("")) {
            lbName.setTextFill(Color.RED);
        } else {
            lbName.setTextFill(Color.BLACK);
        }

        if (surname1.trim().equals("")) {
            lbFirstSurname.setTextFill(Color.RED);
        } else {
            lbFirstSurname.setTextFill(Color.BLACK);
        }

        if (surname2.trim().equals("")) {
            lbSecondSurname.setTextFill(Color.RED);
        } else {
            lbFirstSurname.setTextFill(Color.BLACK);
        }

        if (address.trim().equals("")) {
            lbDirection.setTextFill(Color.RED);
        } else {
            lbDirection.setTextFill(Color.BLACK);
        }

        if (email.trim().equals("")) {
            lbEmail.setTextFill(Color.RED);
        } else {
            lbEmail.setTextFill(Color.BLACK);
        }
    }

    private void clearAll() {
        tfUserName.setText("");
        tfName.setText("");
        tfFirstSurName.setText("");
        tfSecondSurName.setText("");
        tfAddress.setText("");
        tfEmail.setText("");
    }

}
