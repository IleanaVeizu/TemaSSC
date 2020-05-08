package processors;

/**
 * Interface used by image processors to process an image.
 * @param <T> The type of the image that is being processed.
 */
public interface ImageProcessor<T> {
    
    public T process(T image);
    
}
