package com.dampizza.views.user.manager;

import com.dampizza.views.custom.orderList;
import com.dampizza.views.login.*;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import com.dampizza.App;
import static com.dampizza.App.MANAGER_ORDER_VIEW;
import com.dampizza.DrawerManager;
import com.dampizza.exception.order.OrderQueryException;
import com.dampizza.logic.dto.OrderDTO;
import com.dampizza.logic.imp.OrderManagerImp;
import com.dampizza.views.user.customer.HistoryPresenter;
import com.gluonhq.charm.glisten.application.ViewStackPolicy;
import com.gluonhq.charm.glisten.control.CharmListView;
import com.gluonhq.charm.glisten.control.NavigationDrawer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Presenter for manager.fxml
 * 
 * @author ???
 */
public class ManagerPresenter {
    
    private OrderManagerImp orderManager;
    private ObservableList<OrderDTO> oblProducts;
    NavigationDrawer.ViewItem Item;

    @FXML
    private CharmListView<OrderDTO, ? extends Comparable> lbOrders;

    @FXML
    private View primary;

    @FXML
    private Label label;

    public void initialize() {
        
        primary.showingProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) {
                AppBar appBar = MobileApplication.getInstance().getAppBar();
                
                appBar.setVisible(true);
                
                appBar.setNavIcon(MaterialDesignIcon.MENU.button(e -> 
                        MobileApplication.getInstance().showLayer(App.MENU_LAYER)));
                appBar.setTitleText("Manager");
                appBar.getActionItems().add(MaterialDesignIcon.SEARCH.button(e -> 
                        System.out.println("Search")));  
            }
            orderManager = new OrderManagerImp();
            initClassic();
        });
    }
    
    public void initClassic() {


        try {
            oblProducts = FXCollections.observableArrayList(orderManager.getAllOrders());
            oblProducts.forEach(p -> System.out.println(p.toString()));

        } catch (OrderQueryException ex) {
            Logger.getLogger(HistoryPresenter.class.getName()).log(Level.SEVERE, null, ex);
        }
        lbOrders.setItems(oblProducts);
        lbOrders.setCellFactory(p -> new orderList());
        
        lbOrders.selectedItemProperty().addListener((obs,ov,nv) ->{
            //Cargar la order en una constante
            App.setCurrentOrder(lbOrders.getSelectedItem());

            NavigationDrawer.ViewItem Item = new NavigationDrawer.ViewItem("Select", MaterialDesignIcon.HOME.graphic(), MANAGER_ORDER_VIEW, ViewStackPolicy.SKIP);
            DrawerManager drawer = new DrawerManager();
            drawer.updateView(Item);          
        });
         
         
    }
    
    @FXML
    void buttonClick() {
        
    }
    
    
}
