package com.dampizza.views.user.customer;

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
import java.util.logging.Logger;
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
 * FXML Controller class for customer.fxml
 * 
 * @author Jon Xabier Gimenez
 * 
 *  Class that controls the customer.fxml.
 *  Class that shows pizza images and have a quick
 *  access to order.fxml.
 */
public class CustomerPresenter {
    // <editor-fold defaultstate="collapsed" desc="@FXML NODES">
    @FXML
    private View primary;

    @FXML
    private Label label;
    
    @FXML
    private ImageView image;
    //</editor-fold>
     private static final Logger logger = Logger.getLogger(CustomerPresenter.class.getName());
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
                //Declaring action that will change the images automatic
                EventHandler<ActionEvent> eventHandler = e -> {
                   image.setImage(new Image(images[i]));
                   i++;
                   if (i == 3)i=0;
                 };
                logger.info("Starting image change every 5 seconds");
                Timeline animation = new Timeline(new KeyFrame(Duration.millis(5000), eventHandler));
                //Infinite loop for images
                animation.setCycleCount(Timeline.INDEFINITE);
                animation.play();
            }
        });
    }
    
    /**
     * Open the view that handles order.fxml
     */
    @FXML
    void handlerMakePedido() {
        logger.info("Opening Create Order view");
         NavigationDrawer.ViewItem Item = new NavigationDrawer.ViewItem("Create Order", MaterialDesignIcon.HOME.graphic(), ORDER_CREATE_VIEW, ViewStackPolicy.SKIP);
         DrawerManager drawer = new DrawerManager();
         drawer.updateView(Item);

        
    }
    
}
