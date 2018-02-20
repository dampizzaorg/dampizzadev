package com.dampizza.views.user.manager;

import com.dampizza.views.login.*;
import com.gluonhq.charm.glisten.mvc.View;
import java.io.IOException;
import javafx.fxml.FXMLLoader;

/**
 * Manager view provider (FXML Loader)
 * @author ???
 */
public class ManagerView {

    private final String name;

    public ManagerView(String name) {
        this.name = name;
    }
    
    /**
     * Load and returns fxml view.
     * @return view object
     */
    public View getView() {
        try {

            View view = FXMLLoader.load(ManagerView.class.getResource("/com/dampizza/views/user/manager/manager_main.fxml"));

            view.setName(name);
            return view;
        } catch (IOException e) {
            System.out.println("IOException: " + e);
            return new View(name);
        }
    }
}
