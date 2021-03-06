package playground.ui;

import javafx.scene.image.Image;
import playground.core.ImageMarkerService;
import playground.core.ImageRecognitionResult;
import playground.core.ImageRecognizerService;
import playground.util.Utils;

import java.io.File;

public class FacesTabController extends ImageController{

    private final ImageRecognizerService imageRecognizerService;
    private final ImageMarkerService imageMarkerService;

    public FacesTabController() {
        imageRecognizerService = new ImageRecognizerService();
        imageMarkerService = new ImageMarkerService();
    }

    @Override
    protected void imageSelectedSuccess(File selectedPicture) {
        ImageRecognitionResult detect = imageRecognizerService.detectAllFaces(selectedPicture);
        imageMarkerService.markMatches(detect);

        Image image = Utils.mat2Image(detect.getInputImage());
        imageDisplay.setImage(image);
    }
}
