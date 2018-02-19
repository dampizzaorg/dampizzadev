/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dampizza.views.user;

import com.dampizza.App;
import com.dampizza.DrawerManager;
import com.dampizza.exception.order.OrderQueryException;
import com.dampizza.exception.product.ProductQueryException;
import com.dampizza.logic.dto.OrderDTO;
import com.dampizza.logic.dto.ProductDTO;
import com.dampizza.logic.imp.OrderManagerImp;
import com.dampizza.views.user.manager.orderList;
import com.dampizza.views.user.manager.productList;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.application.ViewStackPolicy;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.control.CharmListView;
import com.gluonhq.charm.glisten.control.NavigationDrawer;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;

import static com.dampizza.App.MANAGER_ORDER_VIEW;
import static com.dampizza.App.HISTORY_VIEW;
import static com.dampizza.App.CUSTOMER_VIEW;
import com.dampizza.util.LogicFactory;
import com.gluonhq.charm.glisten.control.Alert;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author 2dam
 */
public class HistoryPresenterDetail implements Initializable {

    private OrderManagerImp omi;
    private OrderDTO cart;
    private ObservableList<ProductDTO> oblOrders;
    private ObservableList<String> names;

    @FXML
    private CharmListView<ProductDTO, ? extends Comparable> lvOrders;
    @FXML
    private View primary;
    @FXML
    private Button btnRepeat;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

       primary.showingProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) {
                AppBar appBar = MobileApplication.getInstance().getAppBar();
                
                appBar.setVisible(true);
                
                appBar.setTitleText("History Detail");
                appBar.getActionItems().add(MaterialDesignIcon.ARROW_BACK.button(e -> 
                back()));
                
                cart = (OrderDTO) LogicFactory.getUserManager().getSession().get("cart");
                
            }
          
            
        });
       
       omi = new OrderManagerImp();
        oblOrders = FXCollections.observableArrayList(App.getCurrentOrder().getProducts());
       lvOrders.setItems(oblOrders);
       lvOrders.setCellFactory(p -> new productList());
       
      
    }     
    public void back(){
        //Metodo que te lleva a la ventana anterior
        NavigationDrawer.ViewItem Item = new NavigationDrawer.ViewItem("Select", MaterialDesignIcon.HOME.graphic(), HISTORY_VIEW, ViewStackPolicy.SKIP);
        DrawerManager drawer = new DrawerManager();
        drawer.updateView(Item);
     }
    
    public void repeat(){
    	System.out.println("REPETIR PEDIDO");
          for(int i=0;i<oblOrders.size();i++){
             cart.getProducts().add(oblOrders.get(i));
            cart.setTotal(cart.getTotal()+oblOrders.get(i).getPrice());
        }
        NavigationDrawer.ViewItem Item = new NavigationDrawer.ViewItem("Select", MaterialDesignIcon.HOME.graphic(), CUSTOMER_VIEW, ViewStackPolicy.SKIP);
        DrawerManager drawer = new DrawerManager();
        drawer.updateView(Item);
    }
}
