package com.dampizza.views.custom;

import com.gluonhq.charm.glisten.control.CharmListCell;
import com.gluonhq.charm.glisten.control.ListTile;
import com.dampizza.logic.dto.ProductDTO;
import javafx.beans.value.ObservableValue;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * 
 * @author Jon Xabier 
 * @author Carlos Garcia
 */
public class PizzaCLV extends CharmListCell<ProductDTO> {

    private final ListTile tile;
    private final ImageView imageView;

    public PizzaCLV() {
        this.tile = new ListTile();
        imageView = new ImageView();
        tile.setPrimaryGraphic(imageView);
        setText(null);
    }

    @Override
    public void updateItem(ProductDTO item, boolean empty) {
        super.updateItem(item, empty);
        if (item != null && !empty) {
            tile.textProperty().setAll(
                    item.getName(),
                    item.getDescription());
            final Image image = new Image(item.getUrl(),50,50,false,false);
            if (image != null) {
               imageView.setImage(image);  
            }
            setGraphic(tile);
        } else {
            setGraphic(null);
        }
    }

}
