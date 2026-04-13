package labs.example.fileOperations;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Logger { 

    private static final String BASE_PATH = "src/labs/example/fileOperations/"; // Base structure for file path
    private static final String API_LOG_FILE = BASE_PATH + "logs/api_error.log"; // Location of the log file

    public static void main(String[] args) {
        // First pass to print the log file
        BufferedReader reader = openErrorLog(); // Open the log file and get a BufferedReader for reading the file

        if (reader != null) { // Check if the file was opened successfully
            printLogFile(reader); // Print the contents of the log file to the console
            try { // Close the reader after the first pass
                reader.close(); // Closing reader to free up system resources after we are done with the first pass of reading the file
            } catch (IOException e) { // Catching any potential exceptions that may occur while closing the file
                e.printStackTrace(); // Print the stack trace for debugging purposes
            } // close the reader to free up system resources
        } // If the reader is null, it means there was an error opening the file, and we skip the first pass

        // Second pass to count error types
        BufferedReader reader2 = openErrorLog(); // Open the log file again for the second pass

        if (reader2 != null) { // Check if the file was opened successfully
            getCountOfErrorTypes(reader2); // Count the different types of log entries
            try { // Close the reader after the second pass
                reader2.close(); // Closing reader to free up resources
            } catch (IOException e) { // Same as before, catch any exceptions that may occur while closing the file
                e.printStackTrace(); // Print the stack trace for debugging purposes like before
            }
        } // Closing the reader

        // Third pass to count memory limit exceeded errors by endpoint
        BufferedReader reader3 = openErrorLog(); // Open the log file for the third pass

        if (reader3 != null) { // Check if the file was opened successfully
            getMemoryLimitExceededCount(reader3); // Count the number of "Memory Limit Exceeded" errors by endpoint
            try { // Close the reader after the third pass
                reader3.close(); // Closing reader to free up resources
            } catch (IOException e) { // Same as before, catch any exceptions that may occur while closing the file
                e.printStackTrace(); // Print the stack trace for debugging purposes like before
            }
        } // Closing the reader
    } // Closing the main method

    public static BufferedReader openErrorLog() { // Method to open the log file and return a BufferedReader for reading the file
        try { // Try to open the file and return a BufferedReader
            return new BufferedReader(new FileReader(API_LOG_FILE)); // Create a new BufferedReader to read our log file at the path specified by API_LOG_FILE
        } catch (IOException e) { // catch any IOException that may occur if the file cannot be found or opened
            System.out.println("Error opening file: " + API_LOG_FILE); // Printing an error message to the console if the file cannot be opened but including the file path for clarity
            e.printStackTrace(); // debugging purposes
            return null; // Return null if there was an error opening the file, which allows the calling code to handle this case appropriately
        } // closing our exception handling for opening the file
    } // closing the openErrorLog method

    public static void printLogFile(BufferedReader file) { // Method to read the log file and print each line to the console
        String line; // Variable to hold each line read from the file while printing

        try { // Try to read through the file line by line and print each line
            while ((line = file.readLine()) != null) { // Read each line from the file until we reach the end (when readLine returns null)
                System.out.println(line); // Print the current line to the console
            } // closing the while loop that reads through the file
        } catch (IOException e) { // catching any exceptions that may occur while reading the file
            System.out.println("Error reading file."); // Print an error message to the console if there was an issue reading the file
            e.printStackTrace(); // stack trace for debugging purposes
        } // closing our exception handling for reading the file
    } // closing the printLogFile method

    public static void getCountOfErrorTypes(BufferedReader file) { // Method to count the different types of log entries (ERROR, WARN, INFO, DEBUG) in the log file

        ArrayList<String> types = new ArrayList<>(); // List to hold the different log entry types we want to count
        ArrayList<Integer> counts = new ArrayList<>(); // List to hold the counts corresponding to each log entry type, initialized to 0 for each type

        types.add("ERROR"); // Adding the Log entry for ERROR to the types list
        types.add("WARN"); // Adding the Log entry for WARN to the types list
        types.add("INFO"); // Adding the Log entry for INFO to the types list
        types.add("DEBUG"); // Adding the Log entry for DEBUG to the types list

        counts.add(0); // Initializing the count for ERROR to 0
        counts.add(0); // Initializing the count for WARN to 0
        counts.add(0); // Initializing the count for INFO to 0
        counts.add(0); // Initializing the count for DEBUG to 0

        String line; // Variable to hold each line read from the file during the counting process

        try { // Try to read through the file line by line and count the occurrences of each log entry type
            while ((line = file.readLine()) != null) { // Read each line from the file until we reach the end (when readLine returns null)

                if (line.contains("[ERROR]")) { // Check if the line contains the ERROR log entry
                    counts.set(0, counts.get(0) + 1); // if it does, increment the count for ERROR by 1
                } else if (line.contains("[WARN]")) { // Check if the line contains the WARN log entry
                    counts.set(1, counts.get(1) + 1); // if it does, increment the count for WARN by 1
                } else if (line.contains("[INFO]")) { // Check if the line contains the INFO log entry
                    counts.set(2, counts.get(2) + 1); // if it does, increment the count for INFO by 1
                } else if (line.contains("[DEBUG]")) { // Check if the line contains the DEBUG log entry
                    counts.set(3, counts.get(3) + 1); // if it does, increment the count for DEBUG by 1
                } // If the line does not contain any of the specified log entry types, we simply ignore it and move on to the next line without incrementing any counts
            } // closing the while loop that reads through the file

            for (int i = 0; i < types.size(); i++) { // After counting is complete, we loop through the types list and print out the count for each log entry type in a readable format
                System.out.println(types.get(i) + " count: " + counts.get(i)); // Print the log entry type and its corresponding count to the console
            } // closing the printing loop

        } catch (IOException e) { // catching any exceptions that may occur while reading the file
            System.out.println("Error reading file."); // Print an error message to the console if there was an issue reading the file
            e.printStackTrace(); // stack trace for debugging purposes
        } // closing our exception handling for reading the file
    } // closing the getCountOfErrorTypes method

    private static void getMemoryLimitExceededCount(BufferedReader file) { // Method to count the number of "Memory Limit Exceeded" errors by endpoint in the log file

        ArrayList<String> endpoints = new ArrayList<>(); // List to hold the different endpoints that we encounter in the log file, initialized as an empty list
        ArrayList<Integer> counts = new ArrayList<>(); // List to hold the counts corresponding to each endpoint, initialized as an empty list

        String line; // again a variable to hold each line read from the file during the counting process

        try { // Try to read through the file line by line and count the occurrences of "Memory Limit Exceeded" errors by endpoint
            while ((line = file.readLine()) != null) { // Read each line from the file until we reach the end (when readLine returns null)
                if (line.toLowerCase().contains("memory limit exceeded")) { // Check if the line contains the phrase "Memory Limit Exceeded" 
                    String endpoint = "UNKNOWN"; // Initialize the endpoint variable to "UNKNOWN" in case we cannot find an endpoint in the log entry
                    int index = line.toLowerCase().indexOf("endpoint:"); // Look for the index of the substring "endpoint:" in the line, ignoring case
                    if (index != -1) { // If the substring "endpoint:" is found in the line (index is not -1), we can extract the endpoint information from the log entry
                        endpoint = line.substring(index + 9).trim(); // Extracting the endpoint information from the log by taking the substring starting from the index of "endpoint:" plus the length of "endpoint:" (9 characters) -
                        // - then trimming any leading or trailing whitespace from the resulting string to get a clean endpoint name
                    } // closing the if statement that checks for the presence of "endpoint:" in the log entry

                    if (endpoints.contains(endpoint)) { // Check if the endpoint is already in our list of endpoints, if it is, we need to increment the count for that endpoint
                        int i = endpoints.indexOf(endpoint); // Find the index of the endpoint in the endpoints list
                        counts.set(i, counts.get(i) + 1); // Increment the count for that endpoint by 1 by getting the current count at index i from the counts list and adding 1 to it, then setting that new value back at index i in the counts list
                    } else { // If the endpoint is not already in our list of endpoints, we need to add it to the list and initialize its count to 1
                        endpoints.add(endpoint); // Add the new endpoint to the endpoints list
                        counts.add(1); // Add a count of 1 for this new endpoint to the counts list, since we have encountered it once in the log entry that we are currently processing
                    } // closing if-else statement that checks if the endpoint is already in the list of endpoints
                } // closing the if statement that checks if the line contains "Memory Limit Exceeded"
            } // closing the while loop that reads through the file

            System.out.println("\nMemory Limit Exceeded Counts by Endpoint:"); // After counting is complete, we print out the counts for each endpoint in a readable format
            for (int i = 0; i < endpoints.size(); i++) { // Loop through the endpoints list and print out the count for each endpoint
                System.out.println(endpoints.get(i) + ": " + counts.get(i)); // Print the endpoint and its corresponding count to the console
            } // closing the printing loop for endpoints and their counts

        } catch (IOException e) { // catching any exceptions that may occur while reading the file
            System.out.println("Error reading file."); // Same as always, print an error message to the console if there was an issue reading the file
            e.printStackTrace(); // stack trace for debugging purposes
        } // closing our exception handling for reading the file
    } // closing the getMemoryLimitExceededCount method
} // closing the Logger class