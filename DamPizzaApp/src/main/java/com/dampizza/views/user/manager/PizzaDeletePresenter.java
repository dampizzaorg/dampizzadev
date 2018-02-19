/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dampizza.views.user.manager;

import com.dampizza.App;
import com.dampizza.cfg.AppConstants;
import com.dampizza.exception.product.ProductDeleteException;
import com.dampizza.exception.product.ProductQueryException;
import com.dampizza.logic.dto.ProductDTO;
import com.dampizza.util.LogicFactory;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.Alert;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.control.CharmListView;
import com.gluonhq.charm.glisten.control.Toast;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import jdk.nashorn.internal.runtime.regexp.JoniRegExp;

/**
 *
 * @author Gigabyte
 */
public class PizzaDeletePresenter implements Initializable {

    //<editor-fold defaultstate="collapsed" desc="@FXML NODES">
    @FXML
    private View primary;
    @FXML
    private Button btDeletePizza;
    @FXML
    private CharmListView<ProductDTO, ? extends Comparable> clvPizzas;
    //</editor-fold>

    private ObservableList<ProductDTO> pizzas;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        primary.showingProperty().addListener((obs, oldValue, newValue) -> {

            if (newValue) {
                AppBar appBar = MobileApplication.getInstance().getAppBar();

                appBar.setVisible(true);

                appBar.setNavIcon(MaterialDesignIcon.MENU.button(e
                        -> MobileApplication.getInstance().showLayer(App.MENU_LAYER)));
                appBar.setTitleText("Make pizza");
                //Charege the window with some values
                chargeData();
            }
        });
    }

    private void chargeData() {
        try {
            pizzas = FXCollections.observableList(LogicFactory.getProductManager()
                    .getProductByCategory(AppConstants.PRODUCT_PIZZA));
        } catch (ProductQueryException ex) {
            Logger.getLogger(PizzaDeletePresenter.class.getName()).log(Level.SEVERE, null, ex);
        }
        //charge the list with the pizzas
        chargeList();
        //set the button disabled
        btDeletePizza.setDisable(true);
        //set to the list an touch event
        clvPizzas.selectedItemProperty().addListener((obs, ov, nv) -> {
            //on item click set enabeled the button of delete pizza
            btDeletePizza.setDisable(false);
        });
    }

    @FXML
    public void deletePizza() {
        Alert a = new Alert(javafx.scene.control.Alert.AlertType.CONFIRMATION, "Are you sure you want to delete " + clvPizzas.getSelectedItem().getName() + " pizza?");
        Optional<ButtonType> result = a.showAndWait();
        if (!result.get().getButtonData().isCancelButton()) {
            clvPizzas.getSelectedItem().getId();
            try {
                ProductDTO pizzaToDelete = clvPizzas.getSelectedItem();
                LogicFactory.getProductManager().deleteProduct(pizzaToDelete.getId());
                pizzas.remove(pizzaToDelete);
            } catch (ProductDeleteException ex) {
                Logger.getLogger(PizzaDeletePresenter.class.getName()).log(Level.SEVERE, null, ex);
            }
            chargeList();
        }
    }

    private void chargeList() {
        clvPizzas.setItems(pizzas);
        clvPizzas.setCellFactory(p -> new productList());
    }

}
