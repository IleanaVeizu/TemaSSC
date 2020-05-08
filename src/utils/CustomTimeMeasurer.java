package utils;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that can measure execution time for pieces of code and store
 * information about the code being measured.
 */
public class CustomTimeMeasurer {
    
    /**
     * Singleton instance.
     */
    private static CustomTimeMeasurer instance = null;
    
    /**
     * Reference to start time for every task that is being emasured.
     */
    private Instant startTime = null;
    
    /**
     * The list of logs of the times that were measured.
     */
    private List<String> timeLogs;
    
    private CustomTimeMeasurer() {
        timeLogs = new ArrayList<String>();
    }
    
    /**
     * Get the singleton reference for this class.
     * 
     * @return Singleton instance
     */
    public static CustomTimeMeasurer getInstance() {
        if (instance == null) {
            instance = new CustomTimeMeasurer();
        }
        
        return instance;
    }
    
    /**
     * Record the time that the task started and store it's descripiton.
     * 
     * @param taskDescription The description of the task being measured.
     */
    public void startTimer(String taskDescription) {
        timeLogs.add(taskDescription);
        this.startTime = Instant.now();
    }
    
    /**
     * Stop the timer and log the result.
     */
    public void stopTimer() {
        if (startTime == null) {
            return;
        }
        
        Instant stopTime = Instant.now();
        long timeElapsed = Duration.between(startTime, stopTime).toMillis();
        String timeLog = timeLogs.get(timeLogs.size() - 1);
        timeLog += ": " + Long.toString(timeElapsed) + " milliseconds";
        
        timeLogs.remove(timeLogs.size() - 1);
        timeLogs.add(timeLog);
        
        startTime = null;
    }
    
    /**
     * Print all the logged information to the console.
     */
    public void printLogs() {
        for (int i = 0 ; i < timeLogs.size(); ++i) {
            System.out.println(timeLogs.get(i));
        }
    }
    
    
}
