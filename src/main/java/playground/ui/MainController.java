package playground.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import playground.core.ImageMarkerService;
import playground.core.ImageRecognitionResult;
import playground.core.ImageRecognizerService;
import playground.util.Utils;

import java.io.File;

public class MainController {

    private final FileChooser fileChooser;
    private final ImageRecognizerService imageRecognizerService;
    private final ImageMarkerService imageMarkerService;

    @FXML
    private ImageView imageDisplay;

    public MainController() {
        imageRecognizerService = new ImageRecognizerService();
        imageMarkerService = new ImageMarkerService();

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
            ImageRecognitionResult detect = imageRecognizerService.detectAllFaces(selectedPicture);
            imageMarkerService.markMatches(detect);

            Image image = Utils.mat2Image(detect.getInputImage());
            imageDisplay.setImage(image);
        }
    }
}
