package ua.edu.ukma.ykrukovska.practice2.processor;

public class ProcessingManager {
    private static boolean processing = false;

    public static void stopProcessing() {
        processing = false;
    }

    public static void startProcessing() {
        processing = true;
    }

    public static boolean isProcessing() {
        return processing;
    }
}
