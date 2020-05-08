package processors;

import core.CustomImage;
import core.Pixel;
import utils.Utils;

/**
 * Processor class which modifies the contrast of a image
 */
public class ContrastImageProcessor implements ImageProcessor<CustomImage> {

    /**
     * The value with which the contrast will be modified.
     */
    private final int contrastLevel;
    
    /**
     * The contrast factor calculated based on the contrast level.
     * 
     * The formula for getting the contrast factor is:
     * F = (259 * (C + 255)) / (255 * (259 - C))
     * Where C is the contrast level.
     */
    private final float contrastFactor;
    
    public ContrastImageProcessor(int contrastLevel) {
        this.contrastLevel = contrastLevel;
        this.contrastFactor = 
                (259f * (contrastLevel + 255f)) / (255f * (259f - contrastLevel));
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
                changePixelContrast(outputImage, currentPixel, row, col);
            }
        }
        
        return outputImage;
    }
    
    /**
     * Method which calculates the new values for red, green and blue based on 
     * the contrast factor and updates the pixel for the given image.
     * 
     * To modify the contrast for a pixel, this is the formula:
     *  newColor = truncate(factor * (color - 128) + 128)
     * 
     * This formula should be applied for every color (red, green and blue). 
     * Truncate is a method which ensures that the new value is within the 
     * valid range of 0 to 255.
     * 
     * @param customImage
     * @param pixel
     * @param row
     * @param col 
     */
    private void changePixelContrast(CustomImage customImage, Pixel pixel, 
            int row, int col) {
        int newRedColor = (int)Utils.truncateColorValue(
                contrastFactor * (pixel.getRedColor() - 128) + 128);
        int newGreenColor = (int)Utils.truncateColorValue(
                contrastFactor * (pixel.getGreenColor() - 128) + 128);
        int newBlueColor = (int)Utils.truncateColorValue(
                contrastFactor * (pixel.getBlueColor() - 128) + 128);
        
        customImage.updatePixel(
                row, col, newRedColor, newGreenColor, newBlueColor);
    }
    
}
