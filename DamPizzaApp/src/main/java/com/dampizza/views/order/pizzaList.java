/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dampizza.views.order;

import com.gluonhq.charm.glisten.control.CharmListCell;
import com.gluonhq.charm.glisten.control.ListTile;
import com.dampizza.logic.dto.ProductDTO;
import javafx.beans.value.ObservableValue;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class pizzaList extends CharmListCell<ProductDTO> {

    private final ListTile tile;
    private final ImageView imageView;

    public pizzaList() {
        this.tile = new ListTile();
        imageView = new ImageView();
        tile.setPrimaryGraphic(imageView);
        setText(null);
    }

    @Override
    public void updateItem(ProductDTO item, boolean empty) {
        super.updateItem(item, empty);
        if (item != null && !empty) {
            tile.textProperty().setAll(item.getName(),item.getDescription(),item.getCategory().toString()+item.getIngredients().get(0).getName());
            final Image image = new Image("img/pizza_margarita.png",50,50,false,false);
            if (image != null) {
               imageView.setImage(image);
               
            }
            setGraphic(tile);
        } else {
            setGraphic(null);
        }
    }

}
