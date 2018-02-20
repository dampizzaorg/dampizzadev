package com.dampizza.views.user.common;

import com.dampizza.App;
import com.dampizza.cfg.AppConstants;
import com.dampizza.exception.ingredient.IngredientQueryException;
import com.dampizza.exception.product.ProductCreateException;
import com.dampizza.exception.product.ProductQueryException;
import com.dampizza.logic.dto.IngredientDTO;
import com.dampizza.logic.dto.ProductDTO;
import com.dampizza.logic.io.IngredientManagerInterface;
import com.dampizza.util.LogicFactory;
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
import javafx.scene.image.ImageViewBuilder;

/**
 *
 * @author Isma
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
    private ComboBox<IngredientDTO> cbIngredients;
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
    private void chargeImage() {
        //take the url of the image
        String url = tfUrl.getText();
        try {
            //set the image on the imageview
            img= new ImageView();
            img.setImage(new Image(url));
        } catch (Exception e) {
            //The URL is null or is invalid
            img.setImage(new Image("/img/pizza_avatar_128.png"));
        }

    }

    /**
     * Method to add ingredients to one pizza object
     */
    @FXML
    private void addIngredient() {
        //if selected is not null
        if (cbIngredients.getSelectionModel().getSelectedIndex() != -1) {
            Integer selectedIndex = cbIngredients.getSelectionModel().getSelectedIndex();
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
            alert.showAndWait();
        }
    }

    /**
     * Method to check if the selected ingredient is already added
     *
     * @param selectedIngredent the ingredient selected
     * @return true if is added, false if it is not added
     */
    public Boolean ingredientIsSelected(IngredientDTO selectedIngredent) {
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
        if (!tfPizzaName.getText().trim().equals("")) {
            try {
                //set data
                pizza.setName(tfPizzaName.getText());
                pizza.setPrice(priceCalculation());
                pizza.setDescription("");
                pizza.setIngredients(pizzaIngredients);
                pizza.setCategory(1);
                //if the user is a customer set userID 
                if(!LogicFactory.getUserManager().getSession().get("type").equals(AppConstants.USER_MANAGER)){
                    pizza.setUserId((Long) LogicFactory.getUserManager().getSession().get("id"));
                }
                System.out.println(pizza.getName());
                LogicFactory.getProductManager().createProduct(pizza);
                cleanData();
            } catch (ProductCreateException ex) {
                Logger.getLogger(PizzaCreatePresenter.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ProductQueryException ex) {
                Logger.getLogger(PizzaCreatePresenter.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            Alert a = new Alert(AlertType.WARNING, "set a name of the pizza first");
            a.showAndWait();
        }

    }

    /**
     * Method to delete ingredients to one pizza object
     */
    @FXML
    private void deleteIngredient() {
        //if selected is not null
        if (cbIngredients.getSelectionModel().getSelectedIndex() != -1) {
            Integer selectedIndex = cbIngredients.getSelectionModel().getSelectedIndex();
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
            //tfPizzaName.setPromptText("Pizza Name");
            //Created pizza
            pizza = new ProductDTO();
            //ingredients of the created pizza
            pizzaIngredients = new ArrayList<>();
            //Adding to the list all availeable ingredients
            availeableIngredients = FXCollections.observableList(LogicFactory.getIngredientManager().getAllIngredients());
            //Adding all ingredients to the comboBox
            cbIngredients.setItems(availeableIngredients);
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
     *
     * @return
     */
    private Double priceCalculation() {
        //by default all pizzas has the price of 15 Euros
        Double price = 6.00;
        //If the pizza has more than 5 ingredients
        if (pizzaIngredients.size() > 5) {
            //start adding the price of the ingredients, starting on the 6th ingredient
            for (int i = 5; i < pizzaIngredients.size(); i++) {
                price = price + pizzaIngredients.get(i).getPrice();
            }
        }
        return price;

    }
    /**
     * Method to clean all the data, after make the pizza
     */
    private void cleanData() {
        tfPizzaName.setText("");
        taIngredients.setText("");
        pizzaIngredients.clear();
    }
}
