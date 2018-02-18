/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dampizza.views.user.manager;

import com.dampizza.App;
import static com.dampizza.App.MANAGER_VIEW;
import static com.dampizza.App.MANAGER_SELECT_DEALER_VIEW;
import static com.dampizza.App.MANAGER_VIEW;
import static com.dampizza.App.ORDER_CREATE_VIEW;
import com.dampizza.DrawerManager;
import com.dampizza.exception.order.OrderUpdateException;
import com.dampizza.exception.user.UserQueryException;
import com.dampizza.logic.dto.UserDTO;
import com.dampizza.logic.dto.ProductDTO;
import com.dampizza.logic.imp.OrderManagerImp;
import com.dampizza.logic.imp.UserManagerImp;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.application.ViewStackPolicy;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.control.CharmListView;
import com.gluonhq.charm.glisten.control.NavigationDrawer;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import java.util.List;
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
public class ManagerOrderPresenter {
    
    private ObservableList<ProductDTO> oblItems;
    
    @FXML
    private View primary;
    
    @FXML
    private TextArea taOrder;
    
    @FXML
    private Button btnSelect,btnConfirm;
    
    @FXML
    private ComboBox<String> cbDealer;
    
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
                
                taOrder.setEditable(false);
//                taOrder.setText(App.getCurrentOrder().getId()+"\n"+  App.getCurrentOrder().getDate()+"\n"+ App.getCurrentOrder().getAddress());
                
//                oblItems = FXCollections.observableArrayList(App.getCurrentOrder().getProducts());
                lvOrder.setItems(oblItems);
                lvOrder.setCellFactory(p -> new productList());
                
                
                
                //Se necesita un metodo que devuelva una List de string nombres + apellido de una lista de UserDTO
                try {
                    List <UserDTO> e= new UserManagerImp().getAllUsers();
                    cbDealer.setItems(FXCollections.observableArrayList(e.get(0).getName().toString()));   
                } catch (UserQueryException ex) {
                    Logger.getLogger(ManagerOrderPresenter.class.getName()).log(Level.SEVERE, null, ex);
                }

                
            }
        });
      
      
      
      
      
    }
     @FXML
     public void confirm(){
//        try {
//            //Llamada la BD para actualizar el estado del pedido
//            //App.getCurrentOrder().setDealer(dealer);
//            new OrderManagerImp().updateOrder(App.getCurrentOrder());
//            back();
//        } catch (OrderUpdateException ex) {
//            Logger.getLogger(ManagerOrderPresenter.class.getName()).log(Level.SEVERE, null, ex);
//        }
     }
     
     public void back(){
        //Metodo que te lleva a la ventana anterior
        NavigationDrawer.ViewItem Item = new NavigationDrawer.ViewItem("Select", MaterialDesignIcon.HOME.graphic(), MANAGER_VIEW, ViewStackPolicy.SKIP);
        DrawerManager drawer = new DrawerManager();
        drawer.updateView(Item);
     }
}
