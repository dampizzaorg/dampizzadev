package com.dampizza;

import com.dampizza.exception.ingredient.IngredientCreateException;
import com.dampizza.exception.ingredient.IngredientDeleteException;
import com.dampizza.exception.ingredient.IngredientQueryException;
import com.dampizza.exception.ingredient.IngredientUpdateException;
import com.dampizza.exception.user.UserCreateException;
import com.dampizza.exception.user.UserDeleteException;
import com.dampizza.exception.user.UserQueryException;
import com.dampizza.exception.user.UserUpdateException;
import com.dampizza.logic.dto.IngredientDTO;
import com.dampizza.logic.dto.ProductDTO;
import com.dampizza.logic.dto.UserDTO;
import com.dampizza.logic.imp.IngredientManagerImp;
import com.dampizza.logic.imp.ProductManagerImp;
import com.dampizza.logic.imp.UserManagerImp;
import com.dampizza.model.entity.CredentialEntity;
import com.dampizza.model.entity.UserEntity;
import com.dampizza.util.TestUtil;
import com.dampizza.views.login.LoginView;
import com.dampizza.views.login.RecoverView;
import com.dampizza.views.login.SignupView;
import com.dampizza.views.user.CustomerView;
import com.dampizza.views.user.DealerView;
import com.dampizza.views.user.HistoryView;
import com.dampizza.views.user.manager.ManagerView;
import com.dampizza.views.user.manager.ManagerOrderView;
import com.dampizza.views.user.manager.ManagerSelectDealerView;
import com.dampizza.views.user.ModifyPersonalInfoView;
import com.dampizza.views.user.OrderView;
import com.dampizza.views.user.ProfileView;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.layout.layer.SidePopupView;
import com.gluonhq.charm.glisten.visual.Swatch;
import java.util.ArrayList;
import java.util.List;
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

    public static Locale locale;
    public static ResourceBundle bundle;
    public static CredentialEntity userLoggedIn = null;
    private static TestUtil test = null;

    public static final String LOGIN_VIEW = HOME_VIEW;
    public static final String SIGNUP_VIEW = "SignUp view";
    public static final String RECOVER_VIEW = "Recover Password view";
    public static final String PROFILE_VIEW = "Profile view";
    public static final String CUSTOMER_VIEW = "Customer Password view";
    public static final String MANAGER_VIEW = "Manager view";
    public static final String MANAGER_ORDER_VIEW = "Manager order view";
    public static final String MANAGER_SELECT_DEALER_VIEW = "Manager select view";
    public static final String DEALER_VIEW = "Dealer Password view";

    public static final String ORDER_VIEW = "Order view";
    public static final String HISTORY_VIEW = "History view";

    public static final String MENU_LAYER = "Side Menu";

    @Override
    public void init() {
        // Init ResourceBundle
        //locale = new Locale("es");
        //bundle = ResourceBundle.getBundle("resources.properties.MessageString");

        /* ADD VIEWS TO VIEW FACTORY */
        addViewFactory(LOGIN_VIEW, () -> new LoginView(LOGIN_VIEW).getView());
        addViewFactory(SIGNUP_VIEW, () -> new SignupView(SIGNUP_VIEW).getView());
        addViewFactory(RECOVER_VIEW, () -> new RecoverView(RECOVER_VIEW).getView());
        addViewFactory(PROFILE_VIEW, () -> new ModifyPersonalInfoView(PROFILE_VIEW).getView());
        addViewFactory(CUSTOMER_VIEW, () -> new CustomerView(CUSTOMER_VIEW).getView());
        addViewFactory(MANAGER_VIEW, () -> new ManagerView(MANAGER_VIEW).getView());
        addViewFactory(MANAGER_ORDER_VIEW, () -> new ManagerOrderView(MANAGER_ORDER_VIEW).getView());
        addViewFactory(MANAGER_SELECT_DEALER_VIEW, () -> new ManagerSelectDealerView(MANAGER_SELECT_DEALER_VIEW).getView());
        addViewFactory(DEALER_VIEW, () -> new DealerView(DEALER_VIEW).getView());
        addViewFactory(ORDER_VIEW, () -> new OrderView(ORDER_VIEW).getView());
        addViewFactory(HISTORY_VIEW, () -> new HistoryView(HISTORY_VIEW).getView());
        
        addLayerFactory(MENU_LAYER, () -> new SidePopupView(new DrawerManager().getDrawer()));

        //testHibernate();

    }

    @Override
    public void postInit(Scene scene) {
        Swatch.BLUE.assignTo(scene);

        scene.getStylesheets().add(App.class.getResource("style.css").toExternalForm());
        //((Stage) scene.getWindow()).getIcons().add(new Image(App.class.getResourceAsStream("/icon.png")));
        ((Stage) scene.getWindow()).getIcons().add(new Image(App.class.getResourceAsStream("/img/pizza_avatar_sm_128.png")));
    }

    public void testHibernate() {

        test = new TestUtil();

        // TEST USER
        test.testCreateUsers();
        test.testUpdateUsers();
        test.testDeleteUser();
        test.testGetUsers();

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
    }
}
