package com.dampizza.views.order;

import com.dampizza.views.user.customer.HistoryPresenter;
import com.dampizza.views.custom.PizzaCLV;
import com.dampizza.views.login.*;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import com.dampizza.App;
import com.dampizza.cfg.AppConstants;
import com.dampizza.exception.product.ProductQueryException;
import com.dampizza.logic.dto.OrderDTO;
import com.dampizza.logic.dto.ProductDTO;
import com.dampizza.logic.imp.ProductManagerImp;
import com.dampizza.util.LogicFactory;
import com.gluonhq.charm.glisten.control.CharmListCell;
import com.gluonhq.charm.glisten.control.CharmListView;
import com.gluonhq.charm.glisten.control.ListTile;
import com.gluonhq.charm.glisten.control.Toast;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.image.ImageView;

/**
 * Presenter for order_create.fxml
 *
 * @author Carlos Santos
 */
public class OrderCreatePresenter {

    private ProductManagerImp productManager;
    private OrderDTO cart;
    private ObservableList<ProductDTO> oblClassicPizzas;
    private ObservableList<ProductDTO> oblDrinks;
    private ObservableList<ProductDTO> oblCustomPizzas;

    @FXML
    private CharmListView<ProductDTO, ? extends Comparable> lvClassic;
    @FXML
    private CharmListView<ProductDTO, ? extends Comparable> lvDrinks;
    @FXML
    private CharmListView<ProductDTO, ? extends Comparable> lvCustom;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnDetails;
    @FXML
    private Tab tabClassic;
    @FXML
    private Tab tabDrinks;
    @FXML
    private Tab tabCustom;

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

                productManager = LogicFactory.getProductManager();
                cart = (OrderDTO) LogicFactory.getUserManager().getSession().get("cart");
                loadClassicPizzas();
                addListeners();
            }
        });
    }

    @FXML
    void buttonClick() {

    }

    /**
     * Populate classic pizza list
     */
    @FXML
    public void loadClassicPizzas() {
        try {
            oblClassicPizzas = FXCollections.observableArrayList(productManager.getProductByCategory(AppConstants.PRODUCT_PIZZA));

        } catch (ProductQueryException ex) {
            Logger.getLogger(HistoryPresenter.class.getName()).log(Level.SEVERE, null, ex);
        }
        lvClassic.setCellFactory(p -> new PizzaCLV());
        lvClassic.setItems(oblClassicPizzas);
    }

    /**
     * Populate drink's list
     */
    @FXML
    public void loadDrinks() {
        try {
            oblDrinks = FXCollections.observableArrayList(productManager.getProductByCategory(AppConstants.PRODUCT_DRINK));

        } catch (ProductQueryException ex) {
            Logger.getLogger(HistoryPresenter.class.getName()).log(Level.SEVERE, null, ex);
        }
        lvDrinks.setCellFactory(p -> new PizzaCLV());
        lvDrinks.setItems(oblDrinks);
    }

    /**
     * Populate user custom pizzas list
     */
    @FXML
    public void loadCustomPizzas() {
        try {
            Long userid = (Long) LogicFactory.getUserManager().getSession().get("id");
            System.out.println(String.valueOf(userid));
            if (userid > 0) {
                oblCustomPizzas = FXCollections.observableArrayList(productManager.getProductsByUserId(userid));
            }
        } catch (ProductQueryException ex) {
            Logger.getLogger(HistoryPresenter.class.getName()).log(Level.SEVERE, null, ex);
        }
        lvCustom.setCellFactory(p -> new PizzaCLV());
        lvCustom.setItems(oblCustomPizzas);
    }

    
    public void addListeners() {
        /**
         * Enable/disable buttons on listview selected item change.
         */
        lvClassic.selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                btnAdd.setDisable(false);
                btnDetails.setDisable(false);
            }else{
                btnAdd.setDisable(true);
                btnDetails.setDisable(true);
            }
        });
        
        /**
         * Enable/disable buttons on listview selected item change.
         */
        lvDrinks.selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                btnAdd.setDisable(false);
                btnDetails.setDisable(false);
            }else{
                btnAdd.setDisable(true);
                btnDetails.setDisable(true);
            }
        });
        
        /**
         * Enable/disable buttons on listview selected item change.
         */
        lvCustom.selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                btnAdd.setDisable(false);
                btnDetails.setDisable(false);
            }else{
                btnAdd.setDisable(true);
                btnDetails.setDisable(true);
            }
        });
    }
    
    /**
     * Add product to order(cart)
     */
    @FXML
    public void btnAddAction(){
        ProductDTO selectedProduct = null;
        if(tabClassic.isSelected()){
            selectedProduct = lvClassic.getSelectedItem();
        }else if(tabDrinks.isSelected()){
            selectedProduct = lvDrinks.getSelectedItem();
        }else if(tabCustom.isSelected()){
            selectedProduct = lvCustom.getSelectedItem();
        }
        
        if(selectedProduct != null){
            cart.getProducts().add(selectedProduct);
            cart.setTotal(cart.getTotal()+selectedProduct.getPrice());
            //LogicFactory.getUserManager().getSession().replace("cart", cart);
            new Toast("Producto: "+selectedProduct.getName()+" añadido.").show();     
        }else{
            new Toast("Debes seleccionar un producto primero.").show();
        }
    }
    
    /**
     * Show product details
     */
    @FXML
    public void btnDetailsAction(){
        new Toast("Mostrar detalles del producto.").show();
        // TODO SHOW PRODUCT DETAILS (Dialog?)
    }

}
