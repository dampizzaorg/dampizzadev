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
import com.dampizza.exception.order.OrderUpdateException;
import com.dampizza.logic.dto.ProductDTO;
import com.dampizza.logic.imp.OrderManagerImp;
import com.dampizza.util.LogicFactory;
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
    
    @FXML
    private View primary;
    
    @FXML
    private TextArea taDatos;
    
    @FXML
    private Button btEntregado;
    
    @FXML
    private CharmListView<ProductDTO, ? extends Comparable> lvOrder;
    
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
                
                oblItems = FXCollections.observableArrayList(App.getCurrentOrder().getProducts());
                lvOrder.setItems(oblItems);
                lvOrder.setCellFactory(p -> new productList());  
            }
        });
      
      
      
      
      
    }
     @FXML
     public void handlerMarkAsDone(){
        try {
            //Se cambia el estado del pedido
            App.getCurrentOrder().setStatus(AppConstants.STATUS_DELIVERED);
            //Llamada la BD para actualizar el estado del pedido
            LogicFactory.getOrderManager().updateOrder(App.getCurrentOrder());
             MobileApplication.getInstance().showMessage("Order updated");
            back();
        } catch (OrderUpdateException ex) {
            Logger.getLogger(DealerOrderPresenter.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
     
     public void back(){
        //Metodo que te lleva a la ventana anterior
        NavigationDrawer.ViewItem Item = new NavigationDrawer.ViewItem("Select", MaterialDesignIcon.HOME.graphic(), DEALER_VIEW, ViewStackPolicy.SKIP);
        DrawerManager drawer = new DrawerManager();
        drawer.updateView(Item);
     }
}
