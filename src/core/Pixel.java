package core;

/**
 *
 * Class that represents a single pixel in an image.
 */
public class Pixel {
    /**
     * Value for red color.
     */
    private int redColor;
    
    /**
     * Value for green color
     */
    private int greenColor;
    
    /**
     * Value for blue color
     */
    private int blueColor;
    
    /**
     * The RGB color code from which the red, green and blue values are 
     * subtracted.
     * Red, green and blue values combined give this value.
     */
    private int colorCode;
    
    public Pixel(int redColor, int greenColor, int blueColor) {
        this.redColor = redColor;
        this.greenColor = greenColor;
        this.blueColor = blueColor;
        this.updateColorCode();
    }
    
    /**
     * Constructor which calculates the red, green and blue values based on a 
     * color code.
     * 
     * @param colorCode The input color code
     */
    public Pixel(int colorCode) {
        this.redColor = (colorCode & 0x00ff0000) >> 16;
        this.greenColor = (colorCode & 0x0000ff00) >> 8;
        this.blueColor = (colorCode & 0x000000ff);
        this.colorCode = colorCode;
    }
    
    /**
     * Copy constructor
     * 
     * @param inputPixel The pixel which will be copied
     */
    public Pixel(Pixel inputPixel) {
        this(inputPixel.getColorCode());
    }
    
    /**
     * Method which calculates the color code from the red, green and blue 
     * values.
     * This method should be called whenever the color values change.
     */
    private void updateColorCode() {
        this.colorCode = 
                (this.getRedColor() << 16 | 
                this.getGreenColor() << 8 | 
                this.getBlueColor());
    }

    public int getRedColor() {
        return redColor;
    }

    public void setRedColor(int redColor) {
        this.redColor = redColor;
        this.updateColorCode();
    }

    public int getGreenColor() {
        return greenColor;
    }

    public void setGreenColor(int greenColor) {
        this.greenColor = greenColor;
        this.updateColorCode();
    }

    public int getBlueColor() {
        return blueColor;
    }

    public void setBlueColor(int blueColor) {
        this.blueColor = blueColor;
        this.updateColorCode();
    }
    
    public int getColorCode() {
        return this.colorCode;
    }
    
    public void setColorCode(int colorCode) {
        this.colorCode = colorCode;
        
    }
    
}
