package com.dampizza.views.login;

import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import com.dampizza.App;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Presenter for recover.fxml
 * 
 * @author Carlos Santos
 */
public class RecoverPresenter {

    @FXML
    private View primary;

    @FXML
    private Label label;

    public void initialize() {
        
        primary.showingProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) {
                AppBar appBar = MobileApplication.getInstance().getAppBar();
                
                /* Leave on true for testing, change to false when the app is finished */
                appBar.setVisible(true);
                
                appBar.setNavIcon(MaterialDesignIcon.MENU.button(e -> 
                        MobileApplication.getInstance().showLayer(App.MENU_LAYER)));
                appBar.setTitleText("Recover");
                appBar.getActionItems().add(MaterialDesignIcon.SEARCH.button(e -> 
                        System.out.println("Search")));
                
            }
        });
    }
    
    @FXML
    void buttonClick() {
        label.setText("ESKETIT!");
    }
    
}
