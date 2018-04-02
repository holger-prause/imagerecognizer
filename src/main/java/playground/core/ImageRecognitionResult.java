package playground.core;

import org.bytedeco.javacpp.opencv_core.Mat;
import org.bytedeco.javacpp.opencv_core.RectVector;

public class ImageRecognitionResult {
    private final Mat inputImage;
    private final RectVector matches;

    public ImageRecognitionResult(Mat inputImage, RectVector matches) {
        this.inputImage = inputImage;
        this.matches = matches;
    }

    public Mat getInputImage() {
        return inputImage;
    }

    public RectVector getMatches() {
        return matches;
    }
}
