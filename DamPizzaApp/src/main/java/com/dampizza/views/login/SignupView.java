package com.dampizza.views.login;

import com.gluonhq.charm.glisten.mvc.View;
import java.io.IOException;
import javafx.fxml.FXMLLoader;

/**
 * Login view provider (FXML Loader)
 * @author ???
 */
public class SignupView {

    private final String name;

    public SignupView(String name) {
        this.name = name;
    }
    
    /**
     * Load and returns fxml view.
     * @return Signup view object
     */
    public View getView() {
        try {
            /*
                The directory "resources" is the base directory where getResources() will look,
                so in order to get the file in the path 
                "DamPizza/DamPizzaApp/src/main/resources/com/dampizza/views/login/login.fxml"
                you will need to write it like "/com/dampizza/views/login/login.fxml"
            */
            //View view = FXMLLoader.load(SignupView.class.getResource("/com/dampizza/views/login/signup.fxml"));
            View view = FXMLLoader.load(LoginView.class.getResource("/com/dampizza/views/login/signup.fxml"));
            view.setName(name);
            return view;
        } catch (IOException e) {
            System.out.println("IOException: " + e);
            e.printStackTrace();
            return new View(name);
        }
    }
}
