package playground.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;

/**
 * Created by Holger on 03.04.2018.
 */
public abstract class ImageController {
    private final FileChooser fileChooser;

    @FXML
    protected ImageView imageDisplay;

    public ImageController() {
        fileChooser = new FileChooser();
        fileChooser.setTitle("Select Picture to scan");
        fileChooser.setInitialDirectory(new File("src/main/resources/sample_images"));

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
    }

    @FXML
    protected void imageSelected(ActionEvent event) {
        Button source = (Button) event.getSource();
        File selectedPicture = fileChooser.showOpenDialog(source.getScene().getWindow());
        if (selectedPicture != null) {
            imageSelectedSuccess(selectedPicture);
        }
    }

    protected abstract void imageSelectedSuccess(File selectedPicture);
}
