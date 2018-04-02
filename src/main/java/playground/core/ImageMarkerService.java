package playground.core;

import org.bytedeco.javacpp.opencv_core.Mat;
import org.bytedeco.javacpp.opencv_core.Rect;
import org.bytedeco.javacpp.opencv_core.RectVector;
import org.bytedeco.javacpp.opencv_core.Scalar;

import static org.bytedeco.javacpp.opencv_imgproc.rectangle;

public class ImageMarkerService {
    public void markMatches(ImageRecognitionResult imageRecognitionResult) {
        Mat inputImage = imageRecognitionResult.getInputImage();
        RectVector matches = imageRecognitionResult.getMatches();
        for (int i = 0; i < matches.size(); i++) {
            Rect rectMatch = matches.get(i);
            rectangle(inputImage, rectMatch, new Scalar(0, 0, 255, 1));
        }
    }
}
