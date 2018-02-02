package com.dampizza;

import com.dampizza.logic.dto.UserDTO;
import com.dampizza.logic.imp.UserManagerImp;
import com.dampizza.model.entity.UserEntity;
import com.dampizza.views.login.LoginView;
import com.dampizza.views.login.RecoverView;
import com.dampizza.views.login.SignupView;
import com.dampizza.views.user.CustomerView;
import com.dampizza.views.user.DealerView;
import com.dampizza.views.user.ManagerView;
import com.dampizza.views.user.ProfileView;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.layout.layer.SidePopupView;
import com.gluonhq.charm.glisten.visual.Swatch;
import java.util.List;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * DamPizzaApp Main Class
 * 
 * Pizza place mobile app.
 * * Customers can order pizzas.
 * * Managers con manage orders.
 * * Dealers can view and set orders as delivered.
 * 
 * @author Carlos Santos
 */
public class App extends MobileApplication {
    
    public static final String LOGIN_VIEW = HOME_VIEW;
    public static final String SIGNUP_VIEW = "SignUp view";
    public static final String RECOVER_VIEW = "Recover Password view";
    public static final String PROFILE_VIEW = "Profile view";
    public static final String CUSTOMER_VIEW = "Customer Password view";
    public static final String MANAGER_VIEW = "Manager view";
    public static final String DEALER_VIEW = "Dealer Password view";
    
    public static final String MENU_LAYER = "Side Menu";
    
    @Override
    public void init() {
        
        /* ADD VIEWS TO VIEW FACTORY */
        addViewFactory(LOGIN_VIEW, () -> new LoginView(LOGIN_VIEW).getView());
        addViewFactory(SIGNUP_VIEW, () -> new SignupView(SIGNUP_VIEW).getView());
        addViewFactory(RECOVER_VIEW, () -> new RecoverView(RECOVER_VIEW).getView());
        addViewFactory(PROFILE_VIEW, () -> new ProfileView(PROFILE_VIEW).getView());
        addViewFactory(CUSTOMER_VIEW, () -> new CustomerView(CUSTOMER_VIEW).getView());
        addViewFactory(MANAGER_VIEW, () -> new ManagerView(MANAGER_VIEW).getView());
        addViewFactory(DEALER_VIEW, () -> new DealerView(DEALER_VIEW).getView());
        
        addLayerFactory(MENU_LAYER, () -> new SidePopupView(new DrawerManager().getDrawer()));
        
        testHibernate();
        
    }

    @Override
    public void postInit(Scene scene) {
        Swatch.BLUE.assignTo(scene);

        scene.getStylesheets().add(App.class.getResource("style.css").toExternalForm());
        //((Stage) scene.getWindow()).getIcons().add(new Image(App.class.getResourceAsStream("/icon.png")));
        ((Stage) scene.getWindow()).getIcons().add(new Image(App.class.getResourceAsStream("/img/pizza_avatar_sm_128.png")));
    }
    
    public void testHibernate(){
        // TEST CREATE USER
        UserDTO user = new UserDTO("clsantos","carlos","santos","clsantos@dampizza.com","mi casa");
        new UserManagerImp().createUser(user, "passwordtest");

        System.out.println(user.getUsername());
        
//        // TEST UPDATE USER
//        user.setName("UPDATE TEST");
//        new UserManagerImp().updateUser(user);
//        
        // TEST GET ALL USERS
        List<UserDTO> userList = new UserManagerImp().getAllUsers();
        userList.forEach(u -> System.out.println(u.toString()));
//        
//        // TEST DELETE USER
//        new UserManagerImp().deleteUser(user);
    }
}
