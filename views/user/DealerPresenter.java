package com.dampizza.views.user;

import com.dampizza.views.login.*;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import com.dampizza.App;
import static com.dampizza.App.ORDER_CREATE_VIEW;
import static com.dampizza.App.RECOVER_VIEW;
import com.dampizza.DrawerManager;
import com.gluonhq.charm.glisten.application.ViewStackPolicy;
import com.gluonhq.charm.glisten.control.NavigationDrawer;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Presenter for dealer.fxml
 * 
 * @author Carlos Santos
 */
public class DealerPresenter {

    @FXML
    private View primary;

    @FXML
    private Label label;

    public void initialize() {
        
        primary.showingProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) {
                AppBar appBar = MobileApplication.getInstance().getAppBar();
                
                appBar.setVisible(true);
                
                appBar.setNavIcon(MaterialDesignIcon.MENU.button(e -> 
                        MobileApplication.getInstance().showLayer(App.MENU_LAYER)));
                appBar.setTitleText("Dealer");
                appBar.getActionItems().add(MaterialDesignIcon.SEARCH.button(e -> 
                        System.out.println("Search")));
                
            }
        });
    }
    
    @FXML
    void buttonClick() {
        NavigationDrawer.ViewItem Item = new NavigationDrawer.ViewItem("Login", MaterialDesignIcon.HOME.graphic(), ORDER_CREATE_VIEW, ViewStackPolicy.SKIP);
        DrawerManager drawer = new DrawerManager();
        drawer.updateView(Item);
    }
    
}
