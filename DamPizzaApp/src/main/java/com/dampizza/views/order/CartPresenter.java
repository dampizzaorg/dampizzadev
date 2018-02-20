package com.dampizza.views.order;

import com.dampizza.views.custom.PizzaCLV;
import com.dampizza.views.login.*;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import com.dampizza.App;
import com.dampizza.cfg.AppConstants;
import com.dampizza.exception.order.OrderCreateException;
import com.dampizza.exception.order.OrderQueryException;
import com.dampizza.exception.product.ProductQueryException;
import com.dampizza.exception.user.UserQueryException;
import com.dampizza.logic.dto.OrderDTO;
import com.dampizza.logic.dto.ProductDTO;
import com.dampizza.logic.imp.ProductManagerImp;
import com.dampizza.util.LogicFactory;
import com.gluonhq.charm.glisten.control.CharmListCell;
import com.gluonhq.charm.glisten.control.CharmListView;
import com.gluonhq.charm.glisten.control.ListTile;
import com.gluonhq.charm.glisten.control.Toast;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 * Presenter for cart.fxml
 *
 * @author Carlos Santos
 */
public class CartPresenter {

    private OrderDTO cart;
    private ObservableList<ProductDTO> oblCartProducts;

    @FXML
    private CharmListView<ProductDTO, ? extends Comparable> lvCart;
    @FXML
    private Button btnConfirm;
    @FXML
    private Button btnDelete;
    @FXML
    private Label lblTotal;
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

                lvCart.setCellFactory(p -> new PizzaCLV());
                cart = (OrderDTO) LogicFactory.getUserManager().getSession().get("cart");

                initCartView();
                addListeners();
            }
        });
    }

    /**
     * Initializes cart list view.
     */
    public void initCartView() {
        oblCartProducts = FXCollections.observableArrayList(cart.getProducts());
        lvCart.setItems(oblCartProducts);
        lblTotal.setText(String.valueOf(cart.getTotal()) + " €");
    }

    /**
     * Create order as long as the cart is not empty.
     * @throws OrderCreateException
     * @throws OrderQueryException
     * @throws UserQueryException 
     */
    @FXML
    public void btnConfirmAction() throws OrderCreateException, OrderQueryException, UserQueryException {
        if (lvCart.itemsProperty().size()>0) {
            Integer res = LogicFactory.getOrderManager().createOrder(cart);

            if (res == 1) {
                LogicFactory.getUserManager().resetCart();
                cart = (OrderDTO) LogicFactory.getUserManager().getSession().get("cart");
                initCartView();
                new Toast("Pedido realizado exitosamente").show();
            } else {
                new Toast("Ha ocurrido un error").show();
                // TODO show error msg
            }
        }
    }

    /**
     * Remove product from the cart.
     */
    @FXML
    public void btnDeleteAction() {
        ProductDTO selectedProduct = lvCart.getSelectedItem();
        if (selectedProduct != null) {
            cart.getProducts().remove(selectedProduct);
            cart.setTotal(cart.getTotal() - selectedProduct.getPrice());
            initCartView();
            lvCart.refresh();
            new Toast("Producto: " + selectedProduct.getName() + " eliminado.").show();

        } else {
            new Toast("Debes seleccionar un producto primero.").show();
        }

    }

    @FXML
    public void addListeners() {

        // Disable button if tv is empty (Using bindings)
        btnConfirm.disableProperty().bind(lvCart.itemsProperty().emptyProperty());
        //btnDelete.disableProperty().bind(lvCart.itemsProperty().emptyProperty());
        
        // Disable/Enable btnDelete on tableview selection change
        lvCart.selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                btnDelete.setDisable(false);
            } else {
                btnDelete.setDisable(true);
            }
        });

    }

}
