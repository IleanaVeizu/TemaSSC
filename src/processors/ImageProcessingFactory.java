package processors;

import core.CustomImage;

/**
 * Sinleton Factory class which calls the apropriate processor on a given image.
 */
public class ImageProcessingFactory {
    
    /**
     * Singleton factory instance.
     */
    private static ImageProcessingFactory instance;
    
    private ImageProcessingFactory() {
        
    }
    
    /**
     * Retrieves the singleton instance.
     * 
     * @return Singleton instance
     */
    public static ImageProcessingFactory getInstance() {
        if (instance == null) {
            instance = new ImageProcessingFactory();
        }
        
        return instance;
    }
    
    /**
     * Process a given image based on the processing type that the user wants.
     * 
     * @param inputImage The image which will be modified.
     * @param processingType The processing type.
     * @param parameters Extra parameters used for processing
     * @return The modified image.
     */
    public CustomImage processImage(CustomImage inputImage, 
            ImageProcessingType processingType, int ... parameters) {
        ImageProcessor<CustomImage> imageProcessor = null;
        
        switch(processingType) {
            case BRIGHTNESS_CORRECTION:
                imageProcessor = new BrightnessImageProcessor(parameters[0]);
                break;
            case CONTRAST_CORRECTION:
                imageProcessor = new ContrastImageProcessor(parameters[0]);
                break;
            default:
                return inputImage;
        }
        
        if (imageProcessor != null) {
            return imageProcessor.process(inputImage);
        } else {
            return inputImage;
        }
        
    }
    
}
