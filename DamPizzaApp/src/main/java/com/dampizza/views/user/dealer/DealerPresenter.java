import com.dampizza.util.LogicFactory;
import com.dampizza.views.custom.orderList;

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

import com.dampizza.exception.order.OrderQueryException;
import com.dampizza.logic.dto.OrderDTO;
import com.gluonhq.charm.glisten.application.ViewStackPolicy;
import com.gluonhq.charm.glisten.control.NavigationDrawer;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;


/**
 * FXML Controller class
 *
 * @author 2dam
 */
public class DealerPresenter implements Initializable {

    private ObservableList<OrderDTO> oblOrders;

    @FXML
    private CharmListView<OrderDTO, ? extends Comparable> lbOrders;
    @FXML
    private View primary;
    
    

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

    private void initClassic() {
        try {
            oblOrders = FXCollections.observableArrayList(LogicFactory.getOrderManager().getAllOrders());
            lbOrders.setItems(oblOrders);
            lbOrders.setCellFactory(p -> new orderList());
            
            lbOrders.selectedItemProperty().addListener((obs,ov,nv) ->{
            //Carga la orden en una constante
            App.setCurrentOrder(lbOrders.getSelectedItem());
            //Va a otra ventana a mostrar la orden en detalle y poder marcarla como hecha
            NavigationDrawer.ViewItem Item = new NavigationDrawer.ViewItem("Select", MaterialDesignIcon.HOME.graphic(), DEALER_ORDER_VIEW, ViewStackPolicy.SKIP);
            DrawerManager drawer = new DrawerManager();
            drawer.updateView(Item);          
        });
        } catch (OrderQueryException ex) {
            Logger.getLogger(DealerPresenter.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }
}
