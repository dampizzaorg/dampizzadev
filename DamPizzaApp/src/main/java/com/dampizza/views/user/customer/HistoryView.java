package com.dampizza.views.user.customer;

import com.dampizza.views.login.*;
import com.gluonhq.charm.glisten.mvc.View;
import java.io.IOException;
import javafx.fxml.FXMLLoader;

/**
 * Dealer view provider (FXML Loader)
 *
 * @author ???
 */
public class HistoryView {

    private final String name;

    public HistoryView(String name) {
        this.name = name;
    }

    /**
     * Load and returns fxml view.
     *
     * @return view object
     */
    public View getView() {
        try {
//            View view = FXMLLoader.load(HistoryView.class.getResource("/com/dampizza/views/user/customer/history.fxml"));
            View view = FXMLLoader.load(HistoryView.class.getResource("history.fxml"));
            view.setName(name);
            return view;
        } catch (IOException e) {
            System.out.println("IOException: " + e);
            return new View(name);
        }
    }
}
