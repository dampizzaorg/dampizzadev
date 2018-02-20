package com.dampizza.views.login;

import com.gluonhq.charm.glisten.mvc.View;
import java.io.IOException;
import javafx.fxml.FXMLLoader;

/**
 * Recover password view provider (FXML Loader)
 * @author ???
 */
public class RecoverView {
    
 

    private final String name;

    public RecoverView(String name) {
        this.name = name;
    }
    
    /**
     * Load and returns fxml view.
     * @return Recover Password view object
     */
    public View getView() {
        try {
            View view = FXMLLoader.load(RecoverView.class.getResource("/com/dampizza/views/login/recover.fxml"));
            view.setName(name);
            return view;
        } catch (IOException e) {
            System.out.println("IOException: " + e);
            return new View(name);
        }
    }
    
    
}
