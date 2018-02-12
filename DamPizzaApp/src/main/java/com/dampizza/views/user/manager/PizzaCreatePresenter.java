/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dampizza.views.user.manager;

import com.dampizza.App;
import com.dampizza.exception.ingredient.IngredientQueryException;
import com.dampizza.logic.dto.IngredientDTO;
import com.dampizza.logic.dto.ProductDTO;
import com.dampizza.logic.imp.IngredientManagerImp;
import com.dampizza.logic.io.IngredientManagerInterface;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.Alert;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.control.TextField;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author 2dam
 */
public class PizzaCreatePresenter implements Initializable {

    //<editor-fold defaultstate="collapsed" desc="@FXML NODES">
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
    private ComboBox<String> cbInredients;
    @FXML
    private Label lbIngredients;
    @FXML
    private Button btAddIngredient;
    @FXML
    private Button btDeleteIngredient;
    @FXML
    private Button btAddPizza;
    @FXML
    private TextArea taIngredients;
    //</editor-fold>
    private ProductDTO pizza;
    private List<IngredientDTO> pizzaIngredients;
    private Alert alert;
    private IngredientManagerInterface ingredientManager;
    private ObservableList<IngredientDTO> availeableIngredients;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        primary.showingProperty().addListener((obs, oldValue, newValue) -> {

            if (newValue) {
                AppBar appBar = MobileApplication.getInstance().getAppBar();

                appBar.setVisible(true);

                appBar.setNavIcon(MaterialDesignIcon.MENU.button(e
                        -> MobileApplication.getInstance().showLayer(App.MENU_LAYER)));
                appBar.setTitleText("Make pizza");
                //Charege the window with some values
                chargeData();
            }

        });

    }

    /**
     * Method to charge one image by URL
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
     * Method to add ingredients to one pizza object
     */
    @FXML
    private void addIngredient() {
        //if selected is not null
        if (cbInredients.getSelectionModel().getSelectedIndex() != -1) {
            Integer selectedIndex = cbInredients.getSelectionModel().getSelectedIndex();
            IngredientDTO selectedIngredient = availeableIngredients.get(selectedIndex);
            //Check if the ingredient is already on the pizza ingredient list
            Boolean isSelected = ingredientIsSelected(selectedIngredient);
            if (isSelected) {
                //if is selected notify user
                alert = new Alert(AlertType.INFORMATION, "The ingredient that you attempt to insert is alredy selected on the ingredient list");
                alert.showAndWait();
            } else {
                //if is not selected add the ingredient to the list and write the name on the ingredients text area 
                pizzaIngredients.add(selectedIngredient);
                taIngredients.appendText("-" + selectedIngredient.getName() + "\n");
            }
            //if selected is  null            
        } else {
            alert = new Alert(AlertType.WARNING, "Select one item first");
        }
    }

    /**
     * Method to check if the selected ingredient is already added
     *
     * @param selectedIngredent the ingredient selected
     * @return true if is added, false if it is not added
     */
    private Boolean ingredientIsSelected(IngredientDTO selectedIngredent) {
        Boolean isSelected = false;
        for (IngredientDTO ingredient : pizzaIngredients) {
            //check if the selected ingredient is on the pizza ingredient list
            if (ingredient.getId().equals(selectedIngredent.getId())) {
                isSelected = true;
                break;
            }
        }
        return isSelected;
    }

    /**
     * Method to add one pizza into the DB
     */
    @FXML
    private void addPizza() {
        pizza.setName(tfPizzaName.getText());
        pizza.setPrice(priceCalculation());
        //pizza.setDescription(description);
        pizza.setIngredients(pizzaIngredients);

    }

    /**
     * Method to delete ingredients to one pizza object
     */
    @FXML
    private void deleteIngredient() {
        //if selected is not null
        if (cbInredients.getSelectionModel().getSelectedIndex() != -1) {
            Integer selectedIndex = cbInredients.getSelectionModel().getSelectedIndex();
            IngredientDTO selectedIngredient = availeableIngredients.get(selectedIndex);
            //Check if the ingredient is already on the pizza ingredient list
            Boolean isSelected = ingredientIsSelected(selectedIngredient);
            if (isSelected) {
                //if is selected notify user and delete if he want to delete
                alert = new Alert(AlertType.CONFIRMATION, "¿Are you sure that you want to delete this ingredient?");
                Optional<ButtonType> result = alert.showAndWait();
                //if he accept, delete the ingredient
                if (result.get() == ButtonType.OK) {
                    pizzaIngredients.remove(selectedIngredient);
                    printList(pizzaIngredients);
                }
            } else {
                //if is not selected notify user
                alert = new Alert(AlertType.WARNING, "You cant delete an ingredient if it is not on the ingredient list");
                alert.showAndWait();
            }
            //if selected is  null            
        } else {
            alert = new Alert(AlertType.WARNING, "Select one item first");
            alert.showAndWait();
        }
    }

    /**
     * Method to update the ingredient ComboBox
     */
    private void chargeData() {
        try {
            tfPizzaName.setPromptText("Pizza Name");
            //Created pizza
            pizza = new ProductDTO();
            //ingredients of the created pizza
            pizzaIngredients = new ArrayList<>();
            //ingredient manager
            ingredientManager = new IngredientManagerImp();
            //Adding to the list all availeable ingredients
            availeableIngredients = FXCollections.observableList(ingredientManager.getAllIngredients());
            //Add the String of each ingredient to the list
            ObservableList<String> allIngredients = FXCollections.observableList(ingredientManager.getAllIngredientsToString());
            //Adding all ingredients to the comboBox
            cbInredients.setItems(allIngredients);
        } catch (IngredientQueryException ex) {
            Logger.getLogger(PizzaCreatePresenter.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Method to print all the selected ingredients names
     *
     * @param ingredients ingredients to print
     */
    private void printList(List<IngredientDTO> ingredients) {
        //set the ingredient text area void
        taIngredients.setText("");
        //print all the ingredient names
        for (IngredientDTO ingredient : ingredients) {
            taIngredients.appendText("-" + ingredient.getName() + "\n");
        }
    }
    /**
     * Method to calculate the price of the created pizza
     * @return 
     */
    private Double priceCalculation() {
        //by default all pizzas has the price of 15 Euros
        Double price = 15.00;
        //If the pizza has more than 5 ingredients
        if (pizzaIngredients.size() > 5) {
            //start adding the price of the ingredients, starting on the 6th ingredient
            for (int i = 5; i < pizzaIngredients.size(); i++) {
                price=price+pizzaIngredients.get(i).getPrice();
            }
        }
        return price;
        
    }

}
