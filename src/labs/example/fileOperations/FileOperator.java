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
                } // close the try block for processing the line
            } // close the while loop for reading lines

        } catch (IOException e) { // checking for IOException when trying to read the file and log the error if it occurs
            logError(logFile, "Failed to read users.csv file."); // Log the error if the file cannot be read
        } // close the try block for reading the file
    } // close the main method

    private static void processLine(String line) { // the processLine method is using the CSV as it's input
        String[] parts = line.split(","); // Split the line by commas to separate the name and the numbers 
        if (parts.length < 2) { // Checking if we have at least a name and one number to process, if not, throw an exception to be caught in the main method and logged
        throw new IllegalArgumentException("Invalid data format"); // Throwing an exception if the line does not contain at least a name and one number to process
}
        String name = parts[0].trim(); // The first part is the name, and the rest are numbers to be averaged. Adding trim() to remove any leading or trailing whitespace from the name string to ensure it is correctly processed and displayed.

        double sum = 0; // Initialize sum and count to calculate the average of the numbers
        int count = 0; // Loop through the parts starting from index 1 to skip the name and calculate the sum and count of the numbers

        for (int i = 1; i < parts.length; i++) { // Converting the string to a double, adding it to the sum and incrementing the count
            if (parts[i].trim().isEmpty()) { // Checking if the number string is empty after our trim, if so then we skip to avoid a NumberFormatException when trying to parse an empty string to a double
                continue; // Skip this iteration if the number string is empty
            } // close the if block for checking empty number strings
            sum += Double.parseDouble(parts[i].trim()); // using parseDouble to convert the string to a double and add it to the sum. Adding trim() to remove any leading or trailing whitespace from the number string before parsing it to ensure it is correctly converted to a double.
            count++; // Incrementing the count for each number processed
        } // close the for loop for processing the numbers

        if (count == 0) { // in case there are no grades to process, we want to avoid a division by zero error when calculating the average, so we check if count is zero and if so, throw an exception to be caught in the main method and logged
            throw new IllegalArgumentException("No valid grades found"); // If there are no valid numbers to process, throw an exception to be caught in the main method and logged
        }
        double average = sum / count; // Calculate the average and print the result 

        System.out.printf("%s: %.2f%n", name, average); //Rounding to two decimal places. %s print's the name. %.2f prints the average with two decimal places and %n makes a new line similar to \n. 
        // I decided to use the printf method to format the output to show the name and the average with two decimal places for better readability. I pulled it from my Java for dumies book and it seemed to be the best to do it without importanting additional libraries.
    } // close the processLine method

    private static void createLogFileIfNotExists(String logFile) { 
        File file = new File(logFile); // Create a File object for the log file and check if it exists, if not, create a new file

        try {
            if (!file.exists()) { // If the file does not exist, create a new file
                file.createNewFile(); // using createNewFile() method to create the log file and if it fails, catch the IOException and print an error message
            }
        } catch (IOException e) { // Log the error if the log file cannot be created
            System.out.println("Could not create log file."); // Printing an error message if an error is thrown while trying to create the log file
        }
    } // close the createLogFileIfNotExists method

    private static void logError(String logFile, String message) { // Logging errors to our log file with a timestamp
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFile, true))) { // ensuring the writer is closed after use and using FileWriter in append mode to add new log entries without overwriting existing ones
            writer.write(new Date() + " - " + message); // Writing the error message to the log file with a timestamp and adding a new line after each log entry
            writer.newLine(); // Adding a new line after each log entry for better readability
        } catch (IOException e) { // Log the error if there is an issue writing to the log file
            System.out.println("Error writing to log file."); // Printing an error message if an error is thrown while trying to write to the log file
        }
    } // close the logError method
} // close the FileOperator class
