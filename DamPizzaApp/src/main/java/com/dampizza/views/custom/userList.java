/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dampizza.views.custom;

import com.gluonhq.charm.glisten.control.CharmListCell;
import com.gluonhq.charm.glisten.control.ListTile;
import com.dampizza.logic.dto.UserDTO;
import javafx.beans.value.ObservableValue;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
/**
 *
 * @author Jon Xabier Gimenez
 */
public class userList extends CharmListCell<UserDTO>{
    
    private final ListTile tile;
    private final ImageView imageView;

    public userList() {
        this.tile = new ListTile();
        imageView = new ImageView();
        tile.setPrimaryGraphic(imageView);
        setText(null);
    }

    @Override
    public void updateItem(UserDTO item, boolean empty) {
        super.updateItem(item, empty);
        if (item != null && !empty) {
            
            tile.textProperty().setAll(item.getUsername()+"  "+item.getName(),item.getAddress()+"    "+item.getEmail());
            setGraphic(tile);
        } else {
            setGraphic(null);
        }
    }
    
}
