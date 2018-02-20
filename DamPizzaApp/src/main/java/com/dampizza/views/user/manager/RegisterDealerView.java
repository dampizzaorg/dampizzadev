package com.dampizza.views.user.manager;

import com.dampizza.views.login.*;
import com.gluonhq.charm.glisten.mvc.View;
import java.io.IOException;
import javafx.fxml.FXMLLoader;

/**
 * Customer view provider (FXML Loader)
 * @author ???
 */
public class RegisterDealerView {

    private final String name;

    public RegisterDealerView(String name) {
        this.name = name;
    }
    
    /**
     * Load and returns fxml view.
     * @return view object
     */
    public View getView() {
        try {
            View view = FXMLLoader.load(RegisterDealerView.class.getResource("registerDealer.fxml"));
            view.setName(name);
            return view;
        } catch (IOException e) {
            System.out.println("IOException: " + e);
            return new View(name);
        }
    }
}
