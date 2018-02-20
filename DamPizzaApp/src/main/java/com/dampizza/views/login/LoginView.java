package com.dampizza.views.login;

import com.gluonhq.charm.glisten.mvc.View;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Login view provider (FXML Loader)
 *
 * @author ???
 */
public class LoginView {

    private final String name;

    public LoginView(String name) {
        this.name = name;
    }

    /**
     * Load and returns fxml view.
     *
     * @return Login view object
     */
    public View getView() {
        try {
            /*
                The directory "resources" is the base directory where getResources() will look,
                so in order to get the file in the path 
                "DamPizza/DamPizzaApp/src/main/resources/com/dampizza/views/login/login.fxml"
                you will need to write it like "/com/dampizza/views/login/login.fxml"
             */
            View view = FXMLLoader.load(LoginView.class.getResource("/com/dampizza/views/login/login.fxml"));
            view.setName(name);
            return view;
        } catch (IOException e) {
            System.out.println("IOException: " + e);
            return new View(name);
        }
    }
}