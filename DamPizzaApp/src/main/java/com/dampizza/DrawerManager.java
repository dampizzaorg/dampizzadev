package com.dampizza;

import static com.dampizza.App.CART_VIEW;
import static com.dampizza.App.CUSTOMER_VIEW;
import static com.dampizza.App.DEALER_VIEW;
import static com.dampizza.App.HISTORY_VIEW;
import com.gluonhq.charm.down.Platform;
import com.gluonhq.charm.down.Services;
import com.gluonhq.charm.down.plugins.LifecycleService;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.application.ViewStackPolicy;
import com.gluonhq.charm.glisten.control.Avatar;
import com.gluonhq.charm.glisten.control.NavigationDrawer;
import com.gluonhq.charm.glisten.control.NavigationDrawer.Item;
import com.gluonhq.charm.glisten.control.NavigationDrawer.ViewItem;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import static com.dampizza.App.MENU_LAYER;
import static com.dampizza.App.LOGIN_VIEW;
import static com.dampizza.App.MANAGER_VIEW;
import static com.dampizza.App.MANAGER_ORDER_VIEW;
import static com.dampizza.App.ORDER_CREATE_VIEW;
import static com.dampizza.App.PROFILE_VIEW;
import static com.dampizza.App.RECOVER_VIEW;
import static com.dampizza.App.SIGNUP_VIEW;
import static com.dampizza.App.MANAGER_DEALER_VIEW;
import static com.dampizza.App.PIZZA_CREATE_VIEW;
import static com.dampizza.App.PIZZA_DELETE_VIEW;
import com.dampizza.cfg.AppConstants;
import javafx.scene.Node;
import javafx.scene.image.Image;

/**
 * Drawer Manager. Manage drawer items and behavior.
 *
 * @author Carlos Santos
 */
public class DrawerManager {

    private final NavigationDrawer drawer;
    private static Integer usu = 1;

    public DrawerManager(Integer status) {
        this.drawer = new NavigationDrawer();
        usu = status;
    }

    public DrawerManager() {
        this.drawer = new NavigationDrawer();

        NavigationDrawer.Header header = new NavigationDrawer.Header("DamPizza",
                "Mobile App",
                new Avatar(21, new Image(DrawerManager.class.getResourceAsStream("/img/pizza_avatar_128.png"))));
        drawer.setHeader(header);

        /* CREATE NEW DRAWER ITEMS HERE*/
        /* CAMBIAR MENU DEPENDIENDO DEL USUARIO*/
        if (usu == AppConstants.USER_CUSTOMER) {
            final Item customerItem = new ViewItem("Customer", MaterialDesignIcon.ACCOUNT_CIRCLE.graphic(), CUSTOMER_VIEW);
            final Item orderItem = new ViewItem("Create Order", MaterialDesignIcon.ACCOUNT_CIRCLE.graphic(), ORDER_CREATE_VIEW);
            final Item historyItem = new ViewItem("History Orders", MaterialDesignIcon.ACCOUNT_CIRCLE.graphic(), HISTORY_VIEW);
            final Item profileItem = new ViewItem("Profile", MaterialDesignIcon.ACCOUNT_CIRCLE.graphic(), PROFILE_VIEW);
            final Item logoutItem = new ViewItem("Logout", MaterialDesignIcon.ACCOUNT_CIRCLE.graphic(), LOGIN_VIEW);
            final Item cartItem = new ViewItem("Carrito", MaterialDesignIcon.ACCOUNT_CIRCLE.graphic(), CART_VIEW);
            final Item ownPizzaCreate= new ViewItem("Create Own Pizza ", MaterialDesignIcon.ACCOUNT_CIRCLE.graphic(), PIZZA_CREATE_VIEW);

            /* REMEMBER TO ADD ITEMS TO THE DRAWER */
            drawer.getItems().addAll(customerItem, orderItem, ownPizzaCreate, historyItem, profileItem, cartItem, logoutItem);
        } else if (usu == AppConstants.USER_MANAGER) {
            final Item managerItem = new ViewItem("Manager", MaterialDesignIcon.ACCOUNT_CIRCLE.graphic(), MANAGER_VIEW);
            final Item managerOrderItem = new ViewItem("Manager Order", MaterialDesignIcon.ACCOUNT_CIRCLE.graphic(), MANAGER_ORDER_VIEW);
            final Item registerItem = new ViewItem("Register a dealer", MaterialDesignIcon.ACCOUNT_CIRCLE.graphic(), SIGNUP_VIEW);
            final Item managerDealerItem = new ViewItem("Delete a dealer", MaterialDesignIcon.ACCOUNT_CIRCLE.graphic(), MANAGER_DEALER_VIEW);
            final Item profileItem = new ViewItem("Profile", MaterialDesignIcon.ACCOUNT_CIRCLE.graphic(), PROFILE_VIEW);
            final Item logoutItem = new ViewItem("Logout", MaterialDesignIcon.ACCOUNT_CIRCLE.graphic(), LOGIN_VIEW);
            final Item pizzaCreateItem= new ViewItem("Create Pizza", MaterialDesignIcon.ACCOUNT_CIRCLE.graphic(), PIZZA_CREATE_VIEW);
            final Item pizzaDeleteItem = new ViewItem("Delete Pizza", MaterialDesignIcon.ACCOUNT_CIRCLE.graphic(), PIZZA_DELETE_VIEW);
            
            drawer.getItems().addAll(managerItem, managerOrderItem,pizzaCreateItem ,pizzaDeleteItem,registerItem, profileItem,managerDealerItem,logoutItem);

        } else if (usu == AppConstants.USER_DEALER){
            final Item profileItem = new ViewItem("Profile", MaterialDesignIcon.ACCOUNT_CIRCLE.graphic(), PROFILE_VIEW);
            final Item dealerItem = new ViewItem("Dealer", MaterialDesignIcon.ACCOUNT_CIRCLE.graphic(), DEALER_VIEW);
            final Item logoutItem = new ViewItem("Logout", MaterialDesignIcon.ACCOUNT_CIRCLE.graphic(), LOGIN_VIEW);
            drawer.getItems().addAll(dealerItem, profileItem,logoutItem);
        }

        if (Platform.isDesktop()) {
//            final Item quitItem = new Item("Quit", MaterialDesignIcon.EXIT_TO_APP.graphic());
//            quitItem.selectedProperty().addListener((obs, ov, nv) -> {
//                if (nv) {
//                    Services.get(LifecycleService.class).ifPresent(LifecycleService::shutdown);
//                }
//            });
            //drawer.getItems().add(quitItem);
        }

        drawer.addEventHandler(NavigationDrawer.ITEM_SELECTED,
                e -> MobileApplication.getInstance().hideLayer(MENU_LAYER));

        MobileApplication.getInstance().viewProperty().addListener((obs, oldView, newView) -> updateItem(newView.getName()));
        //updateItem(LOGIN_VIEW);
    }

    /**
     * Update item
     *
     * @param viewName
     */
    private void updateItem(String viewName) {
        for (Node item : drawer.getItems()) {
            if (item instanceof ViewItem && ((ViewItem) item).getViewName().equals(viewName)) {
                drawer.setSelectedItem(item);
                break;
            }
        }
    }

    /**
     * Update view
     *
     * @param Item
     */
    public void updateView(Item Item) {
        drawer.setSelectedItem(Item);

    }

    /**
     * Returns Drawer
     *
     * @return NavigationDrawer object
     */
    public NavigationDrawer getDrawer() {
        return drawer;
    }
}
