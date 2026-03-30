package labs.example.fileOperations;
import java.io.*;
import java.util.*;

public class FileOperator {

    public static void main(String[] args) {

        String inputFile = "src/labs/example/fileOperations/files/users.csv"; // Path to the input CSV file
        String logFile = "src/labs/example/fileOperations/logs/csv_error.log"; // Path to the log file for errors

        // Checking if the log file exists, if not, create it
        createLogFileIfNotExists(logFile);

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) { // Try with resources to ensure the reader is closed

            String line; // Read the file line by line

            while ((line = reader.readLine()) != null) { // Process each line of the CSV file using the processLine method
                try {
                    processLine(line); // Process the line and if an exception occurs, log the error
                } catch (Exception e) { 
                    logError(logFile, "Error processing line: " + line); // Log the error with the line that caused it
                }
            }

        } catch (IOException e) { // checking for IOException when trying to read the file and log the error if it occurs
            logError(logFile, "Failed to read users.csv file."); // Log the error if the file cannot be read
        }
    }

    private static void processLine(String line) { // the processLine method is using the CSV as it's input
        String[] parts = line.split(","); // Split the line by commas to separate the name and the numbers 

        String name = parts[0]; // The first part is the name, and the rest are numbers to be averaged

        double sum = 0; // Initialize sum and count to calculate the average of the numbers
        int count = 0; // Loop through the parts starting from index 1 to skip the name and calculate the sum and count of the numbers

        for (int i = 1; i < parts.length; i++) { // Converting the string to a double, adding it to the sum and incrementing the count
            sum += Double.parseDouble(parts[i]); // using parseDouble to convert the string to a double and add it to the sum
            count++; // Incrementing the count for each number processed
        }

        double average = sum / count; // Calculate the average and print the result in the format "name: average"

        System.out.println(name + ": " + average); 
    }

    private static void createLogFileIfNotExists(String logFile) { 
        File file = new File(logFile); // Create a File object for the log file and check if it exists, if not, create a new file

        try {
            if (!file.exists()) { // If the file does not exist, create a new file
                file.createNewFile(); // using createNewFile() method to create the log file and if it fails, catch the IOException and print an error message
            }
        } catch (IOException e) { // Log the error if the log file cannot be created
            System.out.println("Could not create log file."); // Printing an error message if an error is thrown while trying to create the log file
        }
    }

    private static void logError(String logFile, String message) { // Logging errors to our log file with a timestamp
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFile, true))) { // ensuring the writer is closed after use and using FileWriter in append mode to add new log entries without overwriting existing ones
            writer.write(new Date() + " - " + message); // Writing the error message to the log file with a timestamp and adding a new line after each log entry
            writer.newLine(); // Adding a new line after each log entry for better readability
        } catch (IOException e) { // Log the error if there is an issue writing to the log file
            System.out.println("Error writing to log file."); // Printing an error message if an error is thrown while trying to write to the log file
        }
    }
}
