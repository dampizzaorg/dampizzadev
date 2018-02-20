package com.dampizza;

import com.dampizza.cfg.AppConstants;
import com.dampizza.exception.order.OrderQueryException;
import com.dampizza.exception.order.OrderUpdateException;
import com.dampizza.logic.dto.OrderDTO;
import com.dampizza.model.entity.UserEntity;
import com.dampizza.util.LogicFactory;
import com.dampizza.util.TestUtil;
import com.dampizza.views.login.LoginView;
import com.dampizza.views.login.RecoverView;
import com.dampizza.views.login.SignupView;
import com.dampizza.views.order.CartView;
import com.dampizza.views.user.customer.CustomerView;
import com.dampizza.views.user.dealer.DealerView;
import com.dampizza.views.user.dealer.DealerOrderView;
import com.dampizza.views.user.customer.HistoryView;
import com.dampizza.views.user.customer.HistoryViewDetail;
import com.dampizza.views.user.manager.ManagerView;
import com.dampizza.views.user.manager.ManagerOrderView;
import com.dampizza.views.user.manager.ManagerDealerView;
import com.dampizza.views.user.manager.RegisterDealerView;
import com.dampizza.views.user.common.ModifyPersonalInfoView;
import com.dampizza.views.order.OrderCreateView;
import com.dampizza.views.user.common.PizzaCreateView;
import com.dampizza.views.user.manager.PizzaDeleteView;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.layout.layer.SidePopupView;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.Swatch;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * DamPizzaApp Main Class
 *
 * Pizza place mobile app. * Customers can order pizzas. * Managers con manage
 * orders. * Dealers can view and set orders as delivered.
 *
 * @author Carlos Santos
 */
public class App extends MobileApplication {

    private static final Logger logger = Logger.getLogger(App.class.getName());
    public static Locale locale;
    private static ResourceBundle bundle;

    private static OrderDTO currentOrder = null;

    private static TestUtil test = null;

    public static final String LOGIN_VIEW = HOME_VIEW;
    public static final String SIGNUP_VIEW = "SignUp view";
    public static final String RECOVER_VIEW = "Recover Password view";
    public static final String PROFILE_VIEW = "Profile view";
    public static final String CUSTOMER_VIEW = "Customer Password view";
    public static final String MANAGER_VIEW = "Manager view";
    public static final String MANAGER_ORDER_VIEW = "Manager order view";
    public static final String MANAGER_DEALER_VIEW = "Manager Dealer view";
    public static final String MANAGER_ADD_DEALER_VIEW = "Manager Add Dealer view";
    public static final String DEALER_VIEW = "Dealer  view";
    public static final String DEALER_ORDER_VIEW = "Dealer order view";
    public static final String HISTORY_VIEW = "History view";
    public static final String HISTORY_DETAIL = "History detail";
    public static final String MENU_LAYER = "Side Menu";
    public static final String PIZZA_CREATE_VIEW = "Create pizza view";
    public static final String PIZZA_DELETE_VIEW ="Delete pizza view";

    /*
     * ORDER OPERATIONS VIEWS (Create order, order detail)
     * These views are common for all user types.
     */
    public static final String ORDER_CREATE_VIEW = "Create order view";
    public static final String ORDER_DETAIL_VIEW = "Order details view";
    public static final String CART_VIEW = "Shopping cart view";

    @Override
    public void init() {
        // Init ResourceBundle
        //locale = new Locale("es");
        bundle = ResourceBundle.getBundle("properties.MessageString");

        // Implementation example
        //App.getBundle().getString("dampizza.views.login.btnlogin")
        logger.info("Init App");
        /* ADD VIEWS TO VIEW FACTORY */
        addViewFactory(MANAGER_ORDER_VIEW, () -> {
            ManagerOrderView managerOrderView = new ManagerOrderView(MANAGER_ORDER_VIEW);
            return (View) managerOrderView.getView();
        });

        addViewFactory(LOGIN_VIEW, () -> new LoginView(LOGIN_VIEW).getView());
        addViewFactory(SIGNUP_VIEW, () -> new SignupView(SIGNUP_VIEW).getView());
        addViewFactory(RECOVER_VIEW, () -> new RecoverView(RECOVER_VIEW).getView());
        addViewFactory(PROFILE_VIEW, () -> new ModifyPersonalInfoView(PROFILE_VIEW).getView());
        addViewFactory(CUSTOMER_VIEW, () -> new CustomerView(CUSTOMER_VIEW).getView());
        addViewFactory(MANAGER_VIEW, () -> new ManagerView(MANAGER_VIEW).getView());
        //addViewFactory(MANAGER_ORDER_VIEW, () -> new ManagerOrderView(MANAGER_ORDER_VIEW).getView());
        addViewFactory(MANAGER_DEALER_VIEW, () -> new ManagerDealerView(MANAGER_DEALER_VIEW).getView());
        addViewFactory(MANAGER_ADD_DEALER_VIEW, () -> new RegisterDealerView(MANAGER_ADD_DEALER_VIEW).getView());
        addViewFactory(DEALER_VIEW, () -> new DealerView(DEALER_VIEW).getView());
        addViewFactory(DEALER_ORDER_VIEW, () -> new DealerOrderView(DEALER_ORDER_VIEW).getView());
        addViewFactory(ORDER_CREATE_VIEW, () -> new OrderCreateView(ORDER_CREATE_VIEW).getView());
        addViewFactory(HISTORY_VIEW, () -> new HistoryView(HISTORY_VIEW).getView());
        addViewFactory(HISTORY_DETAIL, () -> new HistoryViewDetail(HISTORY_DETAIL).getView());
        addViewFactory(PIZZA_CREATE_VIEW, () -> new PizzaCreateView(PIZZA_CREATE_VIEW).getView());
        addViewFactory(PIZZA_DELETE_VIEW, () -> new PizzaDeleteView(PIZZA_DELETE_VIEW).getView());
        addViewFactory(CART_VIEW, () -> new CartView(CART_VIEW).getView());

        addLayerFactory(MENU_LAYER, () -> new SidePopupView(new DrawerManager().getDrawer()));

        //testHibernate();
    }

    @Override
    public void postInit(Scene scene) {
        Swatch.INDIGO.assignTo(scene);

        scene.getStylesheets().add(App.class.getResource("style.css").toExternalForm());
        //((Stage) scene.getWindow()).getIcons().add(new Image(App.class.getResourceAsStream("/icon.png")));
        ((Stage) scene.getWindow()).getIcons().add(new Image(App.class.getResourceAsStream("/img/pizza_avatar_sm_128.png")));
    }

    public void testHibernate() {
        logger.info("Testing database operations");
        test = new TestUtil();

        // TEST USER
        test.testCreateUsers();
        test.testUpdateUsers();
        test.testDeleteUser();
        test.testGetUsers();
        test.testGetUsersByType();

        // TEST INGREDIENT
        test.testCreateIngredients();
        test.testUpdateIngredients();
        test.testDeleteIngredient();
        test.testGetIngredients();

        // TEST PRODUCT
        test.testCreateProducts();
        test.testUpdateProducts();
        test.testDeleteProduct();
        test.testGetProducts();

        // TEST ORDER
        test.testCreateOrder();
        test.testGetOrders();

    }

    /**
     * @return the bundle
     */
    public static ResourceBundle getBundle() {
        return bundle;
    }

    /**
     * @return the currentOrder
     */
    public static OrderDTO getCurrentOrder() {
        return currentOrder;
    }

    /**
     * @param aCurrentOrder the currentOrder to set
     */
    public static void setCurrentOrder(OrderDTO aCurrentOrder) {
        currentOrder = aCurrentOrder;
    }

}
