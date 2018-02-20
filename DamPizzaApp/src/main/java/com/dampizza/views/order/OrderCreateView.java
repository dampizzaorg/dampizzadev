package com.dampizza.views.order;

import com.dampizza.App;
import com.dampizza.views.login.*;
import com.gluonhq.charm.glisten.mvc.View;
import java.io.IOException;
import javafx.fxml.FXMLLoader;

/**
 * Order view provider (FXML Loader)
 * @author Carlos Santos
 */
public class OrderCreateView {

    private final String name;

    public OrderCreateView(String name) {
        this.name = name;
    }
    
    /**
     * Load and returns fxml view.
     * @return view object
     */
    public View getView() {
        try {
            View view = FXMLLoader.load(OrderCreateView.class.getResource("/com/dampizza/views/order/order_create.fxml"), App.getBundle());
            view.setName(name);
            return view;
        } catch (IOException e) {
            System.out.println("IOException: " + e);
            return new View(name);
        }
    }
}
