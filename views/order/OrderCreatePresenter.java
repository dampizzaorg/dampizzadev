package com.dampizza.views.order;

import com.dampizza.views.user.*;
import com.dampizza.views.login.*;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import com.dampizza.App;
import com.dampizza.cfg.AppConstants;
import com.dampizza.exception.product.ProductQueryException;
import com.dampizza.logic.dto.ProductDTO;
import com.dampizza.logic.imp.ProductManagerImp;
import com.gluonhq.charm.glisten.control.CharmListCell;
import com.gluonhq.charm.glisten.control.CharmListView;
import com.gluonhq.charm.glisten.control.ListTile;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 * Presenter for order_create.fxml
 *
 * @author Carlos Santos
 */
public class OrderCreatePresenter {

    private ProductManagerImp productManager;
    private ObservableList<ProductDTO> oblPizzas;
    private ObservableList<ProductDTO> oblDrinks;
    private ObservableList<ProductDTO> oblCustom;

    @FXML
    private CharmListView<ProductDTO, ? extends Comparable> lvClassic;
    
    @FXML
    private CharmListView<ProductDTO, ? extends Comparable> lvDrinks;
    
    @FXML
    private CharmListView<ProductDTO, ? extends Comparable> lvCustom;

    @FXML
    private View primary;

    public void initialize() {

        primary.showingProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) {
                AppBar appBar = MobileApplication.getInstance().getAppBar();

                appBar.setVisible(true);

                appBar.setNavIcon(MaterialDesignIcon.MENU.button(e
                        -> MobileApplication.getInstance().showLayer(App.MENU_LAYER)));
                appBar.setTitleText("Create order");

                productManager = new ProductManagerImp();
                initClassic();
            }
        });
    }

    public void initClassic() {


        try {
            //oblProducts = FXCollections.observableArrayList(productManager.getProductByCategory(AppConstants.PRODUCT_PIZZA));
            oblPizzas = FXCollections.observableArrayList(productManager.getProductByCategory(1));
            oblDrinks = FXCollections.observableArrayList(productManager.getProductByCategory(2));
            oblCustom = FXCollections.observableArrayList(productManager.getProductByCategory(3));
            //oblProducts.forEach(p -> System.out.println(p.toString()));

        } catch (ProductQueryException ex) {
            Logger.getLogger(HistoryPresenter.class.getName()).log(Level.SEVERE, null, ex);
        }
        lvClassic.setItems(oblPizzas);
        lvDrinks.setItems(oblDrinks);
        lvCustom.setItems(oblCustom);
        
         lvClassic.setCellFactory(p -> new pizzaList());
         lvDrinks.setCellFactory(p -> new pizzaList());
         lvCustom.setCellFactory(p -> new pizzaList());
    }

    @FXML
    void buttonClick() {

    }

}
