package core;

import java.awt.image.BufferedImage;

/**
 *
 * Class that holds a reference to a image and also it's pixels.
 */
public class CustomImage {

    /**
     * Reference to the image
     */
    private final BufferedImage image;
    
    /**
     * Matrix of pixels for given image
     */
    private Pixel[][] imagePixels;
    
    public CustomImage(BufferedImage image) {
        this.image = image;
        this.imagePixels = null;
    }
    
    /**
     * Copy Constructor
     * 
     * @param customImage 
     */
    public CustomImage(CustomImage customImage) {
        this.image = new BufferedImage(
                customImage.getImage().getWidth(), 
                customImage.getImage().getHeight(), 
                customImage.getImage().getType());
        
        
        Pixel[][] inputImagePixels = customImage.getImagePixels();
        this.imagePixels = 
                new Pixel[inputImagePixels.length][inputImagePixels[0].length];
        
        for (int row = 0; row < inputImagePixels.length; row++) {
            for (int col = 0; col < inputImagePixels[0].length; col++) {
                Pixel currentPixel = inputImagePixels[row][col];
                this.image.setRGB(col, row, currentPixel.getColorCode());
                this.imagePixels[row][col] = new Pixel(currentPixel);
            }
        }
    }
    
    /**
     * Set the color for a given pixel in the matrix. Also updates the pixel's
     * color inside the image reference (not only in the matrix).
     * 
     * @param row The row on which the pixel is situated
     * @param col The col on which the pixel is situated
     * @param redColor The value for red
     * @param greenColor The value for green
     * @param blueColor  The value for blue
     */
    public void updatePixel(int row, int col, int redColor, int greenColor, 
            int blueColor) {
        Pixel pixel = this.getImagePixels()[row][col];
        pixel.setRedColor(redColor);
        pixel.setGreenColor(greenColor);
        pixel.setBlueColor(blueColor);
        
        image.setRGB(col, row, pixel.getColorCode());
    }

    /**
     * Get the image reference
     * 
     * @return Image reference
     */
    public BufferedImage getImage() {
        return image;
    }
    
    /**
     * Get the matrix of pixels. The matrix is instantiated lazy so it's created
     * when it's first required.
     * 
     * @return Matrix of pixels
     */
    public Pixel[][] getImagePixels() {
        
        if (imagePixels == null) {
            int width = image.getWidth();
            int height = image.getHeight();

            Pixel[][] pixels = new Pixel[height][width];

            for (int row = 0; row < height; row++) {
                for (int col = 0; col < width; col++) {
                    Pixel currentPixel = new Pixel(image.getRGB(col, row));
                    pixels[row][col] = currentPixel;
                }
            }

            this.imagePixels = pixels;
        }
        
        return this.imagePixels;
    }
    
    
}
