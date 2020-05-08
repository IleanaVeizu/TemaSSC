package processors;

/**
 * Enum which indicates different types of processing that can be done on an 
 * image.
 */
public enum ImageProcessingType {
    BRIGHTNESS_CORRECTION,
    CONTRAST_CORRECTION;
    
    /**
     * Method which returns a processing type based on a index that the user 
     * selects when he is prompted to pick a processing type.
     * 
     * @param index The index of the processing type
     * @return The processing type
     */
    public static ImageProcessingType getImageProcessingType(String index) {
         switch (index) {
            case "Contrast":
                return CONTRAST_CORRECTION;
            case "Brightness":
                return BRIGHTNESS_CORRECTION;
            default:
                return CONTRAST_CORRECTION;
        }
    }
}
