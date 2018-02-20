package com.dampizza.views.user.manager;

import com.dampizza.views.user.*;
import com.dampizza.views.login.*;
import com.gluonhq.charm.glisten.mvc.View;
import java.io.IOException;
import javafx.fxml.FXMLLoader;

/**
 * Customer view provider (FXML Loader)
 * @author Carlos Santos
 */
public class DealerView {

    private final String name;

    public DealerView(String name) {
        this.name = name;
    }
    
    /**
     * Load and returns fxml view.
     * @return view object
     */
    public View getView() {
        try {
            View view = FXMLLoader.load(DealerView.class.getResource("dealer_main.fxml"));
            view.setName(name);
            return view;
        } catch (IOException e) {
            System.out.println("IOException: " + e);
            return new View(name);
        }
    }
}