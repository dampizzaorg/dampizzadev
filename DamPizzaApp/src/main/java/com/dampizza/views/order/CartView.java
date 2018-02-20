package com.dampizza.views.order;

import com.dampizza.App;
import com.dampizza.views.login.*;
import com.gluonhq.charm.glisten.mvc.View;
import java.io.IOException;
import javafx.fxml.FXMLLoader;

/**
 * Cart view provider (FXML Loader)
 *
 * @author Carlos Santos
 */
public class CartView {

    private final String name;

    public CartView(String name) {
        this.name = name;
    }

    /**
     * Load and returns fxml view.
     *
     * @return view object
     */
    public View getView() {
        try {
            View view = FXMLLoader.load(CartView.class.getResource("/com/dampizza/views/order/cart.fxml"), App.getBundle());
            view.setName(name);
            return view;
        } catch (IOException e) {
            System.out.println("IOException: " + e);
            return new View(name);
        }
    }
}
