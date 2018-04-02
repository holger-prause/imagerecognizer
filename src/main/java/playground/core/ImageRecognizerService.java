package playground.core;

import org.bytedeco.javacpp.opencv_core.Mat;
import org.bytedeco.javacpp.opencv_core.RectVector;
import org.bytedeco.javacpp.opencv_core.Size;
import org.bytedeco.javacpp.opencv_imgproc;
import org.bytedeco.javacpp.opencv_objdetect;
import org.bytedeco.javacpp.opencv_objdetect.CascadeClassifier;

import java.io.File;

import static org.bytedeco.javacpp.opencv_imgcodecs.imread;
import static org.bytedeco.javacpp.opencv_imgproc.cvtColor;
import static org.bytedeco.javacpp.opencv_imgproc.equalizeHist;

public class ImageRecognizerService {

    private CascadeClassifier classifier;

    public ImageRecognizerService() {
        classifier = new CascadeClassifier();
    }

    public ImageRecognitionResult detectAllFaces(File file) {
        classifier.load("src/main/resources/haarcascades/haarcascade_frontalface_alt2.xml");

        Mat inputImage = imread(file.getAbsolutePath());
        Mat grayImage = new Mat();

        cvtColor(inputImage, grayImage, opencv_imgproc.COLOR_BGR2GRAY);
        equalizeHist(grayImage, grayImage);

        RectVector matches = new RectVector();
        classifier.detectMultiScale(grayImage, matches, 1.02, 4, 0 | opencv_objdetect.CASCADE_SCALE_IMAGE, new Size(), new Size());
        return new ImageRecognitionResult(inputImage, matches);
    }
}
