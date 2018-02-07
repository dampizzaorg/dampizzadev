package com.dampizza.views.user;

import com.dampizza.views.login.*;
import com.gluonhq.charm.glisten.mvc.View;
import java.io.IOException;
import javafx.fxml.FXMLLoader;

/**
 * Manager view provider (FXML Loader)
 * @author Carlos Santos
 */
public class OrderView {

    private final String name;

    public OrderView(String name) {
        this.name = name;
    }
    
    /**
     * Load and returns fxml view.
     * @return view object
     */
    public View getView() {
        try {
            View view = FXMLLoader.load(OrderView.class.getResource("/com/dampizza/views/user/order.fxml"));
            view.setName(name);
            return view;
        } catch (IOException e) {
            System.out.println("IOException: " + e);
            return new View(name);
        }
    }
}
