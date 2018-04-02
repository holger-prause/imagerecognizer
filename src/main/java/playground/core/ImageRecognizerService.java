package playground.core;

import org.bytedeco.javacpp.opencv_core.Mat;
import org.bytedeco.javacpp.opencv_core.RectVector;
import org.bytedeco.javacpp.opencv_core.Size;
import org.bytedeco.javacpp.opencv_imgproc;
import org.bytedeco.javacpp.opencv_objdetect.CascadeClassifier;

import java.io.File;

import static org.bytedeco.javacpp.opencv_imgcodecs.imread;
import static org.bytedeco.javacpp.opencv_imgproc.cvtColor;
import static org.bytedeco.javacpp.opencv_imgproc.equalizeHist;

public class ImageRecognizerService {

    private CascadeClassifier faceClassifier;

    public ImageRecognizerService() {
        faceClassifier = new CascadeClassifier();
        faceClassifier.load("src/main/resources/haarcascades/haarcascade_frontalface_alt2.xml");
    }

    public ImageRecognitionResult detectAllFaces(File file) {
        return detect(file, faceClassifier);
        //return detect(file, carClassifier);
    }

    private ImageRecognitionResult detect(File file, CascadeClassifier classifier) {
        Mat inputImage = imread(file.getAbsolutePath());
        Mat grayImage = new Mat();

        cvtColor(inputImage, grayImage, opencv_imgproc.COLOR_BGR2GRAY);
        equalizeHist(grayImage, grayImage);

        RectVector matches = new RectVector();
        //lets talk about some parameters
        //the 3rd parameter is scale factor - picking a lower value results in more potantial matches but is also more resource consuming
        //the 4th parameter is the amount of neighbours - generally speaking the higher this value, the more exact the match is, but can miss some matches
        //the 5th parameter is a flag for influencing the processing
        classifier.detectMultiScale(grayImage, matches, 1.05, 5, 0, new Size(5,5), new Size());
        return new ImageRecognitionResult(inputImage, matches);
    }
}