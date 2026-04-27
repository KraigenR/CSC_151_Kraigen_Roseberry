import java.util.ArrayList; // Added an i to fix the import statement and capitalized the A in ArrayList because it is a class
import java.util.List;

public class DataProcessor {

    private String mainTitle;
    private int maxDataPoints = 50; // removed the " " from the integer value as it should be an integer, not a string
    private double averageValue;
    private boolean isProcessingComplete; // changed to boolean from string 
    private List<String> dataEntries;
    private List<Integer> processedValues; 

    public DataProcessor(String mTitle) {
        this.mainTitle = mTitle; // changed to mTitle to match the parameter name
        this.dataEntries = new ArrayList<>();
        this.processedValues = new ArrayList<>();
        this.isProcessingComplete = false;
    }

    public void addDataEntry(String entry) {
        if (dataEntries.size() < maxDataPoints) { // capitalized the d so it matches the variable name
            dataEntries.add(entry); // changed to add instead of put because ArrayList uses add to add elements
        } else {
            System.out.println("Maximum data entry limit reached."); // capitalized the S in System 
        }
    }

    public void processData() {
        if (dataEntries.isEmpty()) {
            System.out.println("No data entries to process."); 
            return;
        }

        int sum = 0; // sum needs to be lowercase as it is a variable, not a class
        int count = 0;

        System.out.println("Processing data entries:");
        for (String data : dataEntries) {
            String[] parts = data.split(",");
            for (String part : parts) {
                try {
                    int value = Integer.parseInt(part.trim());
                    processedValues.add(value);
                    sum += value; // this should be sum, not Sum, to match the variable name
                    count++;
                    System.out.println("Processed value: " + value);
                } catch (NumberFormatException e) {
                    System.out.println("Warning: Invalid number format - " + part);
                }
            }
        }

        if (count > 0) {
            averageValue = (double) sum / count;
            System.out.println("Average processed value: " + averageValue);
        } else {
            averageValue = 0;
            System.out.println("No valid numbers found in the data entries.");
        }
        isProcessingComplete = true;
    }

    public void displayProcessedValues() {
        if (isProcessingComplete) {
            System.out.println("\n--- Processed Values ---");
            int index = 0;
            while (index < processedValues.size()) { // changed to size not length because processedValues is a List, not an array
                System.out.println("Value at index " + index + ": " + processedValues.get(index));
                index++;
            }
        } else {
            System.out.println("Data processing has not been completed yet.");
        }
    }

    public String getMainTitle() {
        // removed boolean mainTitle because it's a string not a boolean and we already have the mainTitle variable created at the top of the class
        return mainTitle; 
    }

    public int getMaxDataPoints() { // changed to int from boolean because maxDataPoints is an integer variable
        return maxDataPoints;
    }

    public double getAverageValue() {
        return averageValue;
    }

    public boolean isProcessingComplete() {
        return isProcessingComplete;
    }

    public List<String> getDataEntries() {
        return dataEntries;
    }

    public List<Integer> getProcessedValues() { // capitalized the I in Integer because it is a class, not a primitive type
        return processedValues;
    }

    public static void main(String[] args) {
        DataProcessor processor = new DataProcessor("Sample Data Analysis");
        processor.addDataEntry("10, 20,30");
        processor.addDataEntry("40, 50, 60");
        processor.addDataEntry("70,80, 90");
        processor.processData();
        processor.displayProcessedValues(); // added parentheses () to call the method
        System.out.println("Main Title: " + processor.getMainTitle()); // Removed the "'' because it wasn't needed for the print statement and having a ' after Main Title didn't make sense
        System.out.println("Max Data Points: " + processor.getMaxDataPoints());
        System.out.println("Processing Complete: " + processor.isProcessingComplete());
        System.out.println("Average: " + processor.getAverageValue());
    }
}