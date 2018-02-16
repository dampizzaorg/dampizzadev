/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dampizza.views.user.manager;

import com.dampizza.logic.dto.ProductDTO;
import com.gluonhq.charm.glisten.control.CharmListCell;
import com.gluonhq.charm.glisten.control.ListTile;


import javafx.scene.image.ImageView;
/**
 *
 * @author Jon Xabier Gimenez
 */
public class productList extends CharmListCell<ProductDTO>{
    
    private final ListTile tile;
      private final ImageView imageView;

    public productList() {
        this.tile = new ListTile();
        imageView = new ImageView();
        tile.setPrimaryGraphic(imageView);
        setText(null);
    }

    @Override
    public void updateItem(ProductDTO item, boolean empty) {
        super.updateItem(item, empty);
        if (item != null && !empty) {
            
            tile.textProperty().setAll(item.getName()+"    "+item.getPrice(),item.getDescription());
            setGraphic(tile);
        } else {
            setGraphic(null);
        }
    }
    
}
