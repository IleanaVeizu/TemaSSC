package utils;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

/**
 * Class for static utility functions.
 */
public class Utils {
    
    /**
     * The image format supported by the application.
     */
    public static final String IMAGE_FORMAT = "bmp";
    
    /**
     * Method which verifies if the given color is within the valid range of 
     * 0 to 255.
     * 
     * @param colorValue The color value that is verified
     * @return The truncated color value
     */
    public static float truncateColorValue(float colorValue) {
        if (colorValue < 0)
            return 0;
        
        if (colorValue > 255)
            return 255;
        
        return colorValue;
    }
    
    /**
     * Methods which calculates the contrast factor from the contrast level.
     * 
     * @param contrastLevel The contrast level with which we want to modify an 
     * image.
     * @return The contrast factor
     */
    public static float calculateContrastFactor(int contrastLevel) {
        float contrastFactor = 
                (259 * (contrastLevel + 255)) / (255 * (259 - contrastLevel));
        
        return contrastFactor;
    }
    
    /**
     * Verifies if the image format for the given file is the same as the 
     * desired format.
     * 
     * @param inputImageFile The file object of the image.
     * @param desiredFormat The desired format for the image
     * @return True if the file has the desired format. False otherwise.
     */
    public static boolean verifyImageFileFormat(File inputImageFile, 
            String desiredFormat) {
        ImageInputStream iis = null;
        try {
            iis = ImageIO.createImageInputStream(inputImageFile);
            Iterator<ImageReader> iter = ImageIO.getImageReaders(iis);
            if (iter.hasNext() == false) {
                throw new RuntimeException("No readers found for input file.");
            }
            ImageReader reader = iter.next();
            String imageFormat = reader.getFormatName();
            if (imageFormat.toLowerCase().equals(desiredFormat) == false) {
                return false;
            }
            
        } catch (IOException e) {
            System.err.println("Error reading input image. Please try again.\n"
                    + "This is the error: " + e.getMessage());
        } finally {
            if (iis != null) {
                try {
                    iis.close();
                } catch (IOException e) {
                    System.err.println("Error closing the input stream. "
                            + "Please try again.\n" + "This is the error: " + 
                            e.getMessage());
                }
            }
        }
        
        return true;
    }
    
}
