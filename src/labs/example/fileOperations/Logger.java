package labs.example.fileOperations;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Logger { 

    private static final String BASE_PATH = "src/labs/example/fileOperations/"; // Base structure for file path
    private static final String API_LOG_FILE = BASE_PATH + "logs/api_error.log"; // Location of the log file
    private static final String HTTP_LOG_FILE = BASE_PATH + "logs/http_access.log"; // Location of the HTTP access log file adding for Week 14 Lab

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

        //LAB 14 ADDITIONS
        // Fourth pass to find disk space errors with line number and IP address 
        BufferedReader reader4 = openErrorLog(); // Open the log file for the fourth pass

        if (reader4 != null) { // Check if the file was opened successfully
            getDiskSpaceErrorsWithIPAddress(reader4); // Find disk space errors and print the line number and IP address. Same thing as last week just now adding disk space errors. 
            try { // Close the reader after the fourth pass
                reader4.close(); // Closing reader to free up resources
            } catch (IOException e) { // Same as before, catch any exceptions that may occur while closing the file
                e.printStackTrace(); // Print the stack trace for debugging purposes like before
            }
        } // Closing the reader 

        // Fifth pass to count GMT offsets in the HTTP access log file for Lab 14
        BufferedReader reader5 = openErrorLog("http_access_log"); // Open the HTTP access log file using the overloaded method
        if (reader5 != null) { // Check if the file was opened successfully
            getGMTOffset(reader5); // Count the number of times each GMT offset appears in the HTTP access log file
            try { // Close the reader after the fifth pass
                reader5.close(); // Closing reader to free up resources
                } catch (IOException e) { // Same as before, catch any exceptions that may occur while closing the file
                    e.printStackTrace(); // Print the stack trace for debugging purposes like before
                }
        } // Closing the reader for the HTTP access log file

        // Sixth pass to count HTTP satus codes for lab 14
        BufferedReader reader6 = openErrorLog("http_access_log"); // Open the HTTP access log file using the overloaded method
        if (reader6 != null) { // Check if the file was opened successfully
            getHTTPCodes(reader6); // Count the number of times each HTTP status code appears in the HTTP access log file
            try { // Close the reader after the sixth pass
                reader6.close(); // Closing reader to free up resources
            } catch (IOException e) { // Same as before, catch any exceptions that may occur while closing the file
                e.printStackTrace(); // Print the stack trace for debugging purposes like before
            }
        }

        // Seventh pass to count large response sizes
        BufferedReader reader7 = openErrorLog("http_access_log"); // Open the HTTP access log file using the overloaded method to analyze response sizes for Lab 14
        if (reader7 != null) { // Check if the file was opened successfully
            getResponseSizes(reader7); // Count the number of responses that exceed a certain size threshold in the HTTP access log file. 
            try { // Close the reader after the seventh pass
                reader7.close(); // Closing reader to free up resources
            } catch (IOException e) { // Same as before, catch any exceptions that may occur while closing the file
                e.printStackTrace(); // Print the stack trace for debugging purposes like before
            } // Closing the reader for the HTTP access log file after analyzing response sizes
        } // Closing the if statement that checks if the reader was opened successfully for the seventh pass

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

    public static BufferedReader openErrorLog(String fileType) { // Overloaded method for Lab 14
        try {
        if ("http_access_log".equals(fileType)) { // Check if correct argument. We using http_access_log as the argument to specify that we want to open the HTTP access log file instead of the API error log file. If the argument matches, we open the HTTP access log file and return a BufferedReader for it.
            return new BufferedReader(new FileReader(HTTP_LOG_FILE)); // Open HTTP log file and return a BufferedReader for it
        } else { // If the argument does not match the expected value, we print an error message and return null to indicate that the file could not be opened
            System.out.println("Invalid file type: " + fileType); // just printing an error message to the console
            return null; // Return null to indicate that the file could not be opened due to an invalid file type argument
        } // closing the if-else statement that checks the file type argument
            } catch (IOException e) { // catch any IOException that may occur if the file cannot be found or opened
            System.out.println("Error opening file: " + fileType); // Printing an error message to the console if the file cannot be opened but including the file type argument for clarity
            e.printStackTrace(); // normal debugging purposes
            return null;
        } // closing our exception handling for opening the file
    } // closing the overloaded openErrorLog method

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
                        counts.set(i, counts.get(i) + 1); // Increment the count for this endpoint
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

    // LAB 14 ADDITIONS
    private static void getDiskSpaceErrorsWithIPAddress(BufferedReader file) { // Method to find disk space errors and capture the line number and IP address for each one

        ArrayList<Integer> lineNumbers = new ArrayList<>(); // List to hold the line numbers where disk space errors occur
        ArrayList<String> ipAddresses = new ArrayList<>(); // List to hold the IP addresses associated with disk space errors

        String line; // Variable to hold each line read from the file during the counting process
        int currentLineNumber = 0; // Variable to keep track of the current line number while reading the file

        try { // Try to read through the file line by line and find disk space errors
            while ((line = file.readLine()) != null) { // Read each line from the file until we reach the end (when readLine returns null)
                currentLineNumber++; // Increase the line number count each time a new line is read

                if (line.contains("Disk space running low")) { // Check if the line contains a disk space error
                    String[] parts = line.split(" "); // Split the line into parts using spaces. Were using spaces as the delimiter because in the log format we are using, the IP address is separated from the rest of the log entry by spaces

                    if (parts.length >= 5) { // Make sure the line has enough parts to contain an IP address. Changed from >= 4 to >= 5 because we need to account for the fact that the IP address is the fourth item in the log entry, and we want to make sure there are at least 5 parts to avoid an ArrayIndexOutOfBoundsException when we try to access parts[3]
                        String ipAddress = parts[3]; // The IP address is the fourth item in this log format

                        lineNumbers.add(currentLineNumber); // Add the current line number to the lineNumbers list
                        ipAddresses.add(ipAddress); // Add the IP address to the ipAddresses list
                    } // closing the if statement that checks if the line has enough parts
                } // closing the if statement that checks if the line contains a disk space error
            } // closing the while loop that reads through the file

            System.out.println("\nDisk Space Errors With IP Address:"); // Print a heading before the results
            for (int i = 0; i < lineNumbers.size(); i++) { // Loop through the stored line numbers and IP addresses
                System.out.println("Disk space error on line " + lineNumbers.get(i) + " for IP Address: " + ipAddresses.get(i)); // Print the line number and IP address in the required format
            } // closing the printing loop for line numbers and IP addresses

        } catch (IOException e) { // catching any exceptions that may occur while reading the file
            System.out.println("Error reading file."); // Same as always, print an error message to the console if there was an issue reading the file
            e.printStackTrace(); // stack trace for debugging purposes
        } // closing our exception handling for reading the file
    } // closing the getDiskSpaceErrorsWithIPAddress method

    private static void getGMTOffset(BufferedReader file) { // Method to find and count each distinct GMT offset in the HTTP access log file

    ArrayList<String> gmtOffsets = new ArrayList<>(); // List to hold the distinct GMT offsets found in the file
    ArrayList<Integer> counts = new ArrayList<>(); // List to hold the counts for each GMT offset

    String line; // Variable to hold each line read from the file during the counting process

    try { // Try to read through the file line by line and count the GMT offsets
        while ((line = file.readLine()) != null) { // Read each line from the file until we reach the end (when readLine returns null)
            String[] parts = line.split(" "); // Split the line into parts using spaces

            if (parts.length >= 5) { // Make sure the line has enough parts to contain the timestamp and GMT offset
                //String timePart = parts[3]; // Example of what we are targeting: [04/Apr/2025:10:13:07 // This is commented out because we aren't using the time port but I want to keep it for the future just in case it's needed. 
                String offsetPart = parts[4]; // Example of this targeting: +0900]

                String gmtOffset = offsetPart.replace("]", ""); // Remove the closing bracket from the GMT offset

                if (gmtOffsets.contains(gmtOffset)) { // Check if this GMT offset is already in the list
                    int i = gmtOffsets.indexOf(gmtOffset); // Find the index of the GMT offset in the list
                    counts.set(i, counts.get(i) + 1); // Increment the count for this GMT offset
                } else { // If this GMT offset is not already in the list, add it and start the count at 1
                    gmtOffsets.add(gmtOffset); // Add the new GMT offset to the list
                    counts.add(1); // Add a count of 1 for this new GMT offset
                } // closing if-else statement that checks whether the GMT offset is already in the list
            } // closing the if statement that checks if the line has enough parts
        } // closing the while loop that reads through the file

        System.out.println("\nGMT Offset Counts:"); // Print a heading before the results
        for (int i = 0; i < gmtOffsets.size(); i++) { // Loop through the GMT offsets and their counts
            System.out.println(gmtOffsets.get(i) + ": " + counts.get(i)); // Print each GMT offset and its count
        } // closing the printing loop for GMT offsets and counts

    } catch (IOException e) { // catching any exceptions that may occur while reading the file
        System.out.println("Error reading file."); // Same as always, print an error message to the console if there was an issue reading the file
        e.printStackTrace(); // stack trace for debugging purposes
    } // closing our exception handling for reading the file
} // closing the getGMTOffset method

    private static void getHTTPCodes(BufferedReader file) { // Method to count HTTP status code ranges
        int count2xx = 0; // Counter for 2xx status codes
        int count3xx = 0; // Counter for 3xx status codes
        int count4xx = 0; // Counter for 4xx status codes
        int count5xx = 0; // Counter for 5xx status codes

        String line; // Variable to hold each line read from the file during the counting process
        try { // Try to read through the file line by line and count the HTTP status codes
            while ((line = file.readLine()) != null) { // Read each line from the file until we reach the end (when readLine returns null)
                String[] parts = line.split(" "); // Split the line into parts using spaces

                if (parts.length >= 9) { // Make sure the line has enough parts to contain the HTTP status code
                    String statusCodeStr = parts[8]; // The HTTP status code is typically the ninth part of the log entry in this format

                    try { // Try to parse the status code as an integer
                        int statusCode = Integer.parseInt(statusCodeStr); // Extract HTTP status code

                        if (statusCode >= 200 && statusCode < 300) { // Check if it's a 2xx status code
                            count2xx++; // Increment the 2xx counter
                        } else if (statusCode >= 300 && statusCode < 400) { // Check if it's a 3xx status code
                            count3xx++; // Increment the 3xx counter
                        } else if (statusCode >= 400 && statusCode < 500) { // Check if it's a 4xx status code
                            count4xx++; // Increment the 4xx counter
                        } else if (statusCode >= 500 && statusCode < 600) { // Check if it's a 5xx status code
                            count5xx++; // Increment the 5xx counter
                        } // If the status code does not fall into any of these ranges, we simply ignore it and move on to the next line without incrementing any counters
                    } catch (NumberFormatException e) { // Catch any exceptions that may occur while parsing the status code as an integer
                        System.out.println("Invalid status code: " + statusCodeStr); // Print an error message if the status code is not a valid integer
                    } // closing our exception handling for parsing the status code
                } // closing the if statement that checks if the line has enough parts
            } // closing the while loop that reads through the file

            System.out.println("\nHTTP Status Code Counts:"); // Print a heading before the results
            System.out.println("5xx Errors: " + count5xx); // Print the count of 5xx status codes
            System.out.println("4xx Errors: " + count4xx); // Print the count of 4xx status codes
            System.out.println("3xx Errors: " + count3xx); // Print the count of 3xx status codes
            System.out.println("2xx Errors: " + count2xx); // Print the count of 2xx status codes
        } catch (IOException e) { // Catch any exceptions that may occur while reading the file
            System.out.println("Error reading file."); // Print an error message if there was an issue reading the file
            e.printStackTrace(); // Print the stack trace for debugging purposes
        } // closing our exception handling for reading the file
    } // closing the getHTTPCodes method

    private static void getResponseSizes(BufferedReader file) { // Method to count response sizes greater than 3900 bytes
        int count = 0; // Counter for response sizes greater than 3900
        String line; // Variable to hold each line read from the file
        try { // Try to read through the file line by line and count response sizes greater than 3900 bytes
            while ((line = file.readLine()) != null) { // Read each line from the file until we reach the end (when readLine returns null)
                String[] parts = line.split(" "); // Split the line into parts using spaces

                if (parts.length >= 10) { // Make sure the line has enough parts to contain the response size
                    String sizeStr = parts[9]; // Response size is the 10th element


                    try { // Try to parse the response size as an integer
                        int size = Integer.parseInt(sizeStr); // Convert to integer
                        if (size > 3900) { // Check if the response size is greater than 3900 bytes
                            count++; // Increment the counter for large response sizes
                        } // If the response size is not greater than 3900 bytes, we simply ignore it and move on to the next line without incrementing the counter
                    } catch (NumberFormatException e) { // Catch any exceptions that may occur while parsing the response size as an integer
                        System.out.println("Invalid response size: " + sizeStr); // Print an error message if the response size is not a valid integer
                    } // closing our exception handling for parsing the response size
                } // closing the if statement that checks if the line has enough parts
            } // closing the while loop that reads through the file

            System.out.println("\nResponse Size Count:"); // Print a heading before the result
            System.out.println("Number of responses greater than 3900 bytes: " + count); // Print the count of responses greater than 3900 bytes
        } catch (IOException e) { // Catch any exceptions that may occur while reading the file
            System.out.println("Error reading file."); // Print an error message if there was an issue reading the file
            e.printStackTrace(); // Print the stack trace for debugging purposes
        } // closing our exception handling for reading the file
    } // closing the getResponseSizes method
} // closing the Logger class