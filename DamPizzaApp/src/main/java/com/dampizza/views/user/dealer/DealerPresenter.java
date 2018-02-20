/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dampizza.views.user.dealer;

import com.dampizza.util.LogicFactory;

import com.gluonhq.charm.glisten.control.CharmListView;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;

import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import com.dampizza.App;
import static com.dampizza.App.DEALER_ORDER_VIEW;
import static com.dampizza.App.MANAGER_ORDER_VIEW;
import com.dampizza.DrawerManager;
import com.dampizza.cfg.AppConstants;

import com.dampizza.exception.order.OrderQueryException;
import com.dampizza.logic.dto.OrderDTO;
import com.dampizza.views.custom.orderList;
import com.gluonhq.charm.glisten.application.ViewStackPolicy;
import com.gluonhq.charm.glisten.control.NavigationDrawer;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;


/**
 * FXML Controller class for dealer_main.fxml
 *
 * @author Jon Xabier Gimenez
 * 
 *  Class that controls the dealer_main.fxml.
 *  Class that shows a list of orders of the user logged In.
 */
public class DealerPresenter implements Initializable {
    private static final Logger logger = Logger.getLogger(DealerPresenter.class.getName());
    private ObservableList<OrderDTO> oblOrders;
    // <editor-fold defaultstate="collapsed" desc="@FXML NODES">   
    @FXML
    private CharmListView<OrderDTO, ? extends Comparable> lbOrders;
    @FXML
    private View primary;
    //</editor-fold>
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

       primary.showingProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) {
                AppBar appBar = MobileApplication.getInstance().getAppBar();
                
                appBar.setVisible(true);
                
                appBar.setNavIcon(MaterialDesignIcon.MENU.button(e -> 
                        MobileApplication.getInstance().showLayer(App.MENU_LAYER)));
                appBar.setTitleText("Current Orders");
                
                initClassic();
                
            }
        });
 
    }     

    /**
     * Method that load the ChamList and adds a Listener to it with an action.
     */
    private void initClassic() {
        try {
            //Recogo todas las ordenes del Dealer y las filtro con las que estan en proceso
            oblOrders = FXCollections.observableArrayList(LogicFactory.getOrderManager().getAllOrders().stream().filter(g -> g.getStatus()==AppConstants.STATUS_ONDELIVER).collect(Collectors.toList()));
            //oblOrders = FXCollections.observableArrayList(LogicFactory.getOrderManager().getAllOrders().stream().filter(g -> g.getStatus()==AppConstants.STATUS_ONDELIVER).collect(Collectors.toList()));
            //Asigno la lista al ChamList
            lbOrders.setItems(oblOrders);
            //Le doy formato 
            lbOrders.setCellFactory(p -> new orderList());
            logger.info("List loaded");
            lbOrders.selectedItemProperty().addListener((obs,ov,nv) ->{
            //Carga la orden en una constante
            App.setCurrentOrder(lbOrders.getSelectedItem());
            logger.info("Order loaded");
            //Va a otra ventana a mostrar la orden en detalle y poder marcarla como hecha
            NavigationDrawer.ViewItem Item = new NavigationDrawer.ViewItem("Order detail", MaterialDesignIcon.HOME.graphic(), DEALER_ORDER_VIEW, ViewStackPolicy.SKIP);
            DrawerManager drawer = new DrawerManager();
            drawer.updateView(Item);          
        });
        } catch (OrderQueryException ex) {
            Logger.getLogger(DealerPresenter.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }
}