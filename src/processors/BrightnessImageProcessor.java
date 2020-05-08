package processors;

import core.CustomImage;
import core.Pixel;
import utils.Utils;

/**
 * Processor class which modifies image brightness.
 */
public class BrightnessImageProcessor implements ImageProcessor<CustomImage> {
    
    /**
     * The value with which the brightness will be modified.
     */
    private final int brightnessLevel;
    
    public BrightnessImageProcessor(int brightnessLevel) {
        this.brightnessLevel = brightnessLevel;
    }
    
    @Override
    public CustomImage process(CustomImage inputImage) {
        CustomImage outputImage = new CustomImage(inputImage);
        
        Pixel[][] pixels = outputImage.getImagePixels();
        
        int height = pixels.length;
        int width = pixels[0].length;
        
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                Pixel currentPixel = pixels[row][col];
                changePixelBrightness(outputImage, currentPixel, row, col);
            }
        }
        
        return outputImage;
    }
    
    /**
     * Method which calculates the new values for red, green and blue based on 
     * the brightness variable and updates the pixel for the given image.
     * 
     * To modify the brightness for a pixel you just need to add the brightness 
     * value to each color of the pixel.
     * 
     * @param customImage The image which will be modified
     * @param pixel The pixel which will be modified
     * @param row The row on which the pixel is situated
     * @param col The col on which the pixel is situated
     */
    private void changePixelBrightness(CustomImage customImage, Pixel pixel, 
            int row, int col) {
        int newRedColor = (int)Utils.truncateColorValue(pixel.getRedColor() + 
                brightnessLevel);
        int newGreenColor = (int)Utils.truncateColorValue(
                pixel.getGreenColor() + brightnessLevel);
        int newBlueColor = (int)Utils.truncateColorValue(
                pixel.getBlueColor() + brightnessLevel);
        
        customImage.updatePixel(
                row, col, newRedColor, newGreenColor, newBlueColor);
    }
    
}
