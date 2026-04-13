package labs.example.fileOperations;
import java.io.*;
import java.nio.file.*;
import java.util.*;

public class FileOperator {

    private static final String BASE_PATH = "src/labs/example/fileOperations/"; // Base path for file input and output
    public static void main(String[] args) {

        Path inputFile = Paths.get(BASE_PATH, "files", "users.csv"); // Path to the input CSV file
        Path logFile = Paths.get(BASE_PATH, "logs", "csv_error.log"); // Path to the log file for errors
        Path apiLogFile = Paths.get(BASE_PATH, "logs", "api_error.log"); // Path to the log file for API errors

        // Ensure log directories and files exist before processing
        createLogFileIfNotExists(logFile);
        createLogFileIfNotExists(apiLogFile);

        try (BufferedReader reader = Files.newBufferedReader(inputFile)) { // Try with resources to ensure the reader is closed

            String line; // Read the file line by line

            String header = reader.readLine(); // Skip the header line of the CSV file
            if (header == null) {
                return; // No data to process
            }

            while ((line = reader.readLine()) != null) { // Process each line of the CSV file using the processLine method
                try {
                    processLine(line); // Process the line and if an exception occurs, log the error
                } catch (NumberFormatException e) { // Catching NumberFormatException that may occur if there is an issue parsing a number in the processLine method, and logging those errors with the line that caused them
                    logError(logFile, "[CSV ERROR] Number format error: " + line + " | " + e.getMessage()); // same as above but for number format
                } catch (IllegalArgumentException e) { // Catching specific exceptions that we throw in the processLine method for invalid data formats and no valid grades found, and logging those errors with the line that caused them
                    logError(logFile, "[CSV ERROR] Invalid data: " + line + " | " + e.getMessage()); // Logging illegal argument exceptions
                } catch (Exception e) { // Catching any other exceptions that may occur during processing of a line and logging those errors with the line that caused them
                    logError(apiLogFile, "[SYSTEM ERROR] Line: " + line + " | " + e.getMessage()); // Logging any other exceptions that may occur during processing of a line to the API log file
                }
            } // close the while loop for reading lines

        } catch (IOException e) { // checking for IOException when trying to read the file and log the error if it occurs
            logError(apiLogFile, "[SYSTEM ERROR] Failed to read users.csv file: " + e.getMessage()); // Log the error if the file cannot be read
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

    private static void createLogFileIfNotExists(Path logFile) {
        try {
            if (logFile.getParent() != null) {
                Files.createDirectories(logFile.getParent());
            }
            if (Files.notExists(logFile)) {
                Files.createFile(logFile);
            }
        } catch (IOException e) {
            System.out.println("Could not create log file: " + e.getMessage());
        }
    } // close the createLogFileIfNotExists method

    private static void logError(Path logFile, String message) { // Logging errors to our log file with a timestamp
        createLogFileIfNotExists(logFile);
        try (BufferedWriter writer = Files.newBufferedWriter(logFile, StandardOpenOption.CREATE, StandardOpenOption.APPEND)) { // ensuring the writer is closed after use and appending new log entries
            writer.write(new Date() + " - " + message); // Writing the error message to the log file with a timestamp and adding a new line after each log entry
            writer.newLine(); // Adding a new line after each log entry for better readability
        } catch (IOException e) { // Log the error if there is an issue writing to the log file
            System.out.println("Error writing to log file: " + e.getMessage());
        }
    } // close the logError method
} // close the FileOperator class
