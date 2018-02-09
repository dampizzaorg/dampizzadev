/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dampizza.views.user;

import com.dampizza.App;
import com.dampizza.logic.dto.IngredientDTO;
import com.dampizza.logic.dto.ProductDTO;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.control.TextField;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author 2dam
 */
public class MakePizzaPresenter implements Initializable {

    @FXML
    private View primary;
    @FXML
    private ImageView img;
    @FXML
    private TextField tfUrl;
    @FXML
    private TextField tfPizzaName;
    @FXML
    private Button btOk;
    @FXML
    private ComboBox cbInredients;
    @FXML
    private Label lbIngredients;
    @FXML
    private Button btAddIngredient;
    @FXML
    private Button btAddPizza;
    @FXML
    private TextArea taIngredients;
    private ProductDTO pizza;
    private List<IngredientDTO> ingredients;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        primary.showingProperty().addListener((obs, oldValue, newValue) -> {

            if (newValue) {
                AppBar appBar = MobileApplication.getInstance().getAppBar();

                appBar.setVisible(true);

                appBar.setNavIcon(MaterialDesignIcon.MENU.button(e
                        -> MobileApplication.getInstance().showLayer(App.MENU_LAYER)));
                appBar.setTitleText("Make pizza");
            }
        });
        chargeData();
        tfPizzaName.setPromptText("Pizza Name");
        pizza = new ProductDTO();
        ingredients = new ArrayList<>();
    }

    /**
     * Metoth to charge one image by URL
     */
    @FXML
    private void ChargeImage() {
        //take the url of the image
        String url = tfUrl.getText();
        try {
            //set the image on the imageview
            img = new ImageView(new Image(url, 50, 50, true, true));
        } catch (Exception e) {
            //The URL is null or is invalid
            img = new ImageView(new Image("/img/pizza_avatar", 50, 50, true, true));
        }

    }

    /**
     * Metoth to add ingredients to one pizza object
     */
    @FXML
    private void addIngredient() {
        cbInredients.getSelectionModel().getSelectedItem();
        if (ingredientIsSelected()) {

        } else {
            
        }

    }

    /**
     * metoth to add one pizza into the DB
     */
    @FXML
    private void addPizza() {
        pizza.setName(tfPizzaName.getText());
        pizza.setPrice(Double.NaN);
        //pizza.setDescription(description);
        pizza.setIngredients(ingredients);

    }

    private void chargeData() {

    }

    private boolean ingredientIsSelected() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
