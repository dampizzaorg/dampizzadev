/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dampizza.views.user.dealer;

import com.dampizza.views.user.manager.*;
import com.dampizza.App;
import static com.dampizza.App.DEALER_VIEW;

import com.dampizza.DrawerManager;
import com.dampizza.cfg.AppConstants;
import com.dampizza.exception.order.OrderQueryException;
import com.dampizza.exception.order.OrderUpdateException;
import com.dampizza.logic.dto.ProductDTO;
import com.dampizza.logic.imp.OrderManagerImp;
import com.dampizza.util.LogicFactory;
import com.dampizza.views.custom.productList;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.application.ViewStackPolicy;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.control.CharmListView;
import com.gluonhq.charm.glisten.control.NavigationDrawer;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;


/**
 *
 * @author 2dam
 */
public class DealerOrderPresenter {
    
    private ObservableList<ProductDTO> oblItems;
     private static final Logger logger = Logger.getLogger(DealerOrderPresenter.class.getName());
    // <editor-fold defaultstate="collapsed" desc="@FXML NODES">   
    @FXML
    private View primary;
    
    @FXML
    private TextArea taDatos;
    
    @FXML
    private Button btEntregado;
    
    @FXML
    private CharmListView<ProductDTO, ? extends Comparable> lvOrder;
    //</editor-fold>
    
     public void initialize() {
        
      primary.showingProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) {
                AppBar appBar = MobileApplication.getInstance().getAppBar();
                
                appBar.setVisible(true);
                
                appBar.getActionItems().add(MaterialDesignIcon.ARROW_BACK.button(e -> 
                        back()));
                appBar.setTitleText("Manager order"); 
                
                taDatos.setEditable(false);
                taDatos.setText(App.getCurrentOrder().getId()+"\n"+  App.getCurrentOrder().getDate()+"\n"+ App.getCurrentOrder().getAddress());
                logger.info("textArea loaded");
                oblItems = FXCollections.observableArrayList(App.getCurrentOrder().getProducts());
                lvOrder.setItems(oblItems);
                lvOrder.setCellFactory(p -> new productList());  
                logger.info("Producs loaded");
            }
        });
}
     
     /**
      * Metho
      * @throws OrderQueryException 
      */
     @FXML
     public void handlerMarkAsDone() throws OrderQueryException{
        try {
            //Se cambia el estado del pedido
            App.getCurrentOrder().setStatus(AppConstants.STATUS_DELIVERED);
            //Llamada la BD para actualizar el estado del pedido
            LogicFactory.getOrderManager().updateStatus(App.getCurrentOrder().getId(), AppConstants.STATUS_DELIVERED);
            MobileApplication.getInstance().showMessage("Order updated");
            back();
        } catch (OrderUpdateException ex) {
            logger.severe(ex.getMessage());
            logger.severe("Could not be updated order");
        }
     }
     
     public void back(){
        //Metodo que te lleva a la ventana anterior
        logger.info("Going to main dealer view");
        NavigationDrawer.ViewItem Item = new NavigationDrawer.ViewItem("Dealer view", MaterialDesignIcon.HOME.graphic(), DEALER_VIEW, ViewStackPolicy.SKIP);
        DrawerManager drawer = new DrawerManager();
        drawer.updateView(Item);
     }
}