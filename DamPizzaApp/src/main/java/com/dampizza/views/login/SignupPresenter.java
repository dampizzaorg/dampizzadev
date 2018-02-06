package com.dampizza.views.login;

// <editor-fold defaultstate="collapsed" desc="Imports">
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import com.dampizza.App;
import static com.dampizza.App.LOGIN_VIEW;
import com.dampizza.DrawerManager;
import com.dampizza.logic.dto.UserDTO;
import com.dampizza.logic.imp.UserManagerImp;
import com.dampizza.logic.io.UserManagerInterface;
import com.dampizza.util.EcrypterUtil;
import com.dampizza.util.EmailValidator;
import com.gluonhq.charm.glisten.application.ViewStackPolicy;
import com.gluonhq.charm.glisten.control.Alert;
import com.gluonhq.charm.glisten.control.NavigationDrawer;
import com.gluonhq.charm.glisten.control.NavigationDrawer.ViewItem;
import com.gluonhq.charm.glisten.control.TextField;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
//</editor-fold>

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
    public void btnActionSignUp() throws IOException {
        if (/*formValid()*/true) {
            //pair the surnames on one string
            String surname = tfFirstSurName.getText() + " " + tfSecondSurName.getText();
            //encrypt the password

            String password = EcrypterUtil.encrypt(pfPassword.getText());

            System.out.println(password);
            //Create and upload the user into the DB with the specified data
            UserDTO user = new UserDTO(tfUserName.getText(), tfName.getText(), surname, tfEmail.getText(), tfAddress.getText());
            //upload the user
            //new UserManagerImp().createUser(user, password);
            //SEND A EMAIL AFTER REGISTRATION
            /*String message = "Welcome to DamPizza. You have been registered  sucesfully.You can now make orders of pizzas in the app. Have a nice day.\n"
                    + "Username: "+tfUserName.getText()+"\nPassword: "+pfPassword.getText();
            MailUtil.sendEmail(tfEmail.getText(), "Regsitration completed", message);*/
            goLogin();
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
        String password = pfPassword.getText();
        String validationPassword = pfRepeatPassword.getText();
        //CHECK VALUES ARE NOT NULL
        if (userName.trim().equals("") || name.trim().equals("") || surname1.trim().equals("")
                || surname2.trim().equals("") || address.trim().equals("") || email.trim().equals("")
                || password.trim().equals("") || validationPassword.trim().equals("")) {
            Alert alert = new Alert(AlertType.WARNING, "Some fields are not defined");
            alert.showAndWait();
            valid = false;
        }

        //CHECK USER NAME ONLY IF ALL THE VALUES ARE NOT NULL
        if (valid) {
            valid = userManager.userExist(userName);
            //IF USER EXIST
            if (valid) {
                Alert alert = new Alert(AlertType.WARNING, "User name in use");
                alert.showAndWait();
                valid = false;
                //IF USER DOES NOT EXIST
            } else {
                //CHECK PASWORD IS VALID ONLY IF USER NAME DOES NOT EXIST
                //IF PASSWORD AND VALIDATION PASWORD DOES NOT MACH
                if (!(password.equals(validationPassword))) {
                    Alert alert = new Alert(AlertType.WARNING, "Paswords are not equals");
                    alert.showAndWait();
                    valid = false;
                } else {
                    //IF PASWORDS ARE EQUALS
                    valid = validPassword(password);
                    if (!valid) {
                        //IF IS NOT AVALID PASWORD
                        Alert alert = new Alert(AlertType.WARNING, "Pasword has to be valid, a valid pasword contains at least : 8 characters, one capital letter"
                                + ", one small letter, one number  and one symbol");
                        alert.showAndWait();
                        valid = false;
                    }else{
                        //CHECK EMAIL
                        EmailValidator e= new EmailValidator();
                        if(!e.validate(email)){
                            //EMAIL ID NOT VALID
                            Alert alert = new Alert(AlertType.WARNING, "wrong email");
                            alert.showAndWait();
                            valid = false;
                        }
                    }
                }
            }
        }

        return valid;
    }

    private boolean validPassword(String password) {
        String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";
        if (password.matches(pattern)) {
            return true;
        } else {
            return false;
        }
    }

    private void goLogin() {
        ViewItem loginItem= new NavigationDrawer.ViewItem("Login", MaterialDesignIcon.HOME.graphic(),LOGIN_VIEW,ViewStackPolicy.SKIP);
        DrawerManager drawer= new DrawerManager();
        drawer.updateView(loginItem);
    }

}
