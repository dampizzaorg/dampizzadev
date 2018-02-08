package com.dampizza.views.user;

import com.dampizza.views.login.*;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import com.dampizza.App;
import static com.dampizza.App.ORDER_CREATE_VIEW;
import com.dampizza.DrawerManager;
import com.gluonhq.charm.glisten.application.ViewStackPolicy;
import com.gluonhq.charm.glisten.control.NavigationDrawer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import javax.management.timer.Timer;

/**
 * Presenter for customer.fxml
 * 
 * @author Jon Xabier Gimenez
 */
public class CustomerPresenter {

    @FXML
    private View primary;

    @FXML
    private Label label;
    
    @FXML
    private ImageView image;
    
    
    String[] images = { "img/pizza_margarita.png", "img/pizza_queso_bacon.png", "img/pizza_pepperoni.png" };
    Integer i=1;


    public void initialize() {
        
        
        primary.showingProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) {
                AppBar appBar = MobileApplication.getInstance().getAppBar();
                
                appBar.setVisible(true);
                
                appBar.setNavIcon(MaterialDesignIcon.MENU.button(e -> 
                        MobileApplication.getInstance().showLayer(App.MENU_LAYER)));
                appBar.setTitleText("DAMPIZZA 2018");
                EventHandler<ActionEvent> eventHandler = e -> {
                   image.setImage(new Image(images[i]));
                   i++;
                   if (i == 3)i=0;
                 };
                
                Timeline animation = new Timeline(new KeyFrame(Duration.millis(5000), eventHandler));

                animation.setCycleCount(Timeline.INDEFINITE);
                animation.play();
          
            }
        });
    }
    
    @FXML

    void handlerMakePedido() {
         NavigationDrawer.ViewItem Item = new NavigationDrawer.ViewItem("Login", MaterialDesignIcon.HOME.graphic(), ORDER_CREATE_VIEW, ViewStackPolicy.SKIP);
         DrawerManager drawer = new DrawerManager();
         drawer.updateView(Item);

        
    }
    
}
