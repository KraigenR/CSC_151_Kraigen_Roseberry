import java.net.URI;
import java.net.URISyntaxException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
// These are all the imports needed for my Gemini chatbot 
// I ended up using Gemini 2.5 instead of Gemini 2.0 because of API key issues and high demand on the Gemini 2.0 Flash model, which caused me to switch to the Flash-Lite version.
public class GeminiChatbot {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Scanner for reading user input from the console


        String apiKey = "Insert your API key here"; // Replace this placeholder with your own Gemini API key before running the program. I ended up running out of free credits though and paid for credits. This resulted in me changing to 2.5 Flash-Lite. If your API key doesn't work just reach out to me and I will send you mine. 

        System.out.println("Simple Chatbot using Gemini 2.5 Flash-Lite API");  // This is out initial greeting when the chatbot starts. I ended up having to use Gemini 2.5 Flash-Lite because of API key issues with Gemini 2.0 Flash. Additionally I kept getting "API error 503: This model is currently experiencing high demand." causing me to switch to Lite.
        System.out.println("------------------------------------------------------"); // Just a separator for better readability in the console
        System.out.println("Type 'exit' to quit."); // Instructions on how to exit the chatbot

        while (true) { // Main loop to continuously read user input and respond until the user types "exit"
            System.out.print("You: "); // Prompt for user input
            String userInput = scanner.nextLine(); // Read the user's input from the console

            if (userInput.equalsIgnoreCase("exit")) { // Check if the user wants to exit the chatbot
                System.out.println("Gemini: Goodbye!"); // Farewell message before exiting
                break; // Exit the loop and end the program
            } // Close the if statement for checking "exit"

            try { String geminiResponse = callGeminiAPI(userInput, apiKey); // Call the method to send the user input to the Gemini API and get the response
                System.out.println("Gemini: " + geminiResponse); // Print the response from Gemini to the console, prefixed with "Gemini: " for clarity
            } catch (Exception e) { // Catch any exceptions that occur during the API call and print an error message to the console
                System.out.println("An error occurred: " + e.getMessage()); // Print the error message if an exception occurs
            } // Close the try-catch block for handling exceptions during the API call
        } // Close the while loop for continuous user input

        scanner.close(); // Close the scanner to free up resources after the user has exited the chatbot
    } // Close the main method

    public static String callGeminiAPI(String userInput, String apiKey) throws IOException, URISyntaxException { // Method to call the Gemini API with the user's input and return the response as a string
        String urlString = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash-lite:generateContent?key=" + apiKey; // Construct the URL for the Gemini API endpoint, including the API key as a query parameter

        URI uri = new URI(urlString); // Create a URI object from the URL string, which can throw a URISyntaxException if the URL is not properly formatted. Had to spend time fixing the URL formatting to ensure it works correctly with the API key and endpoint
        URL url = uri.toURL(); // Convert the URI to a URL object, which can throw a MalformedURLException if the URL is not valid

        HttpURLConnection connection = (HttpURLConnection) url.openConnection(); // Open a connection to the URL, which can throw an IOException if there is an issue with the network connection or the URL is not reachable

        connection.setRequestMethod("POST"); // Set the HTTP request method to POST, as required by the Gemini API for generating content. This also caused some issues as I didn't set a request method at first
        connection.setRequestProperty("Content-Type", "application/json"); // Set the Content-Type header to application/json, as the Gemini API expects JSON-formatted requests
        connection.setDoOutput(true); // Set the doOutput property to true to allow writing data to the connection

        String jsonInput = "{" // Construct the JSON request body as a string, including the user input. This is the format required by the Gemini API for generating content. This took a lot of debugging to ensure the JSON is formatted correctly
                + "\"contents\":[" // Start of the contents array in the JSON request body
                + "{" // Start of the first content object in the contents array
                + "\"parts\":[" // Start of the parts array in the content object
                + "{" // Start of the first part object in the parts array
                + "\"text\":\"" + escapeJson(userInput) + "\"" // Add the user input as the text field in the part object, ensuring that any special characters in the user input are properly escaped for JSON formatting
                + "}" // Close the first part object
                + "]" // Close the parts array
                + "}" // Close the first content object
                + "]" // Close the contents array
                + "}"; // Close the JSON request body string

        try (OutputStream outputStream = connection.getOutputStream()) { // Get the output stream of the connection to write the JSON request body, using a try-with-resources statement to ensure the stream is properly closed after writing
            byte[] input = jsonInput.getBytes(StandardCharsets.UTF_8); // Convert the JSON request body string to bytes using UTF-8 encoding, which is required for sending the data in the HTTP request
            outputStream.write(input, 0, input.length); // Write the byte array of the JSON request body to the output stream, starting at index 0 and writing the entire length of the byte array
        } // The output stream is automatically closed at the end of the try-with-resources block, ensuring that resources are properly released even if an exception occurs during writing

        int responseCode = connection.getResponseCode(); // Get the HTTP response code from the connection after sending the request

        BufferedReader reader; // Declare a BufferedReader to read the response from the connection

        if (responseCode >= 200 && responseCode < 300) { // Check if the response code indicates a successful response (2xx status code)
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8)); // If the response is successful, create a BufferedReader to read from the input stream of the connection, using UTF-8 encoding to properly read the response data
        } else {
            reader = new BufferedReader(new InputStreamReader(connection.getErrorStream(), StandardCharsets.UTF_8)); // If the response is an error, create a BufferedReader to read from the error stream of the connection, using UTF-8 encoding to properly read the error data
        } // Close the if-else block for determining which stream to read from based on the response code

        StringBuilder response = new StringBuilder(); // Create a StringBuilder to accumulate the lines of the response as they are read from the BufferedReader
        String line; // Declare a string variable to hold each line of the response as it is read from the BufferedReader

        while ((line = reader.readLine()) != null) { // Read lines from the BufferedReader in a loop until there are no more lines to read (i.e., until readLine() returns null)
            response.append(line); // Append each line of the response to the StringBuilder, building the complete response string as it is read
        } // Close the while loop for reading lines from the BufferedReader

        reader.close(); // Close the BufferedReader after reading the response to free up resources
        connection.disconnect(); // Disconnect the HTTP connection to free up resources after the request and response are complete

        String jsonResponse = response.toString(); // Convert the accumulated response in the StringBuilder to a single string, which contains the full JSON response from the Gemini API

        if (responseCode >= 200 && responseCode < 300) { // If the response code indicates a successful response, extract the text from the JSON response and return it as the result of the method
            return extractTextFromJson(jsonResponse); // Call the method to extract the response text from the JSON response, which will return the generated content from Gemini as a string
        } else { // If the response code indicates an error, extract the error message from the JSON response and return a formatted error message string that includes the response code and the error message
            String errorMessage = extractJsonStringValue(jsonResponse, "message"); // Call the method to extract the error message from the JSON response
            return "API error " + responseCode + ": " + errorMessage; // Return a formatted error message string that includes the HTTP response code and the extracted error message from the JSON response, providing feedback on what went wrong with the API call
        } // Close the if-else block for handling successful and error responses based on the response code
    } // Close the callGeminiAPI method

    public static String extractTextFromJson(String jsonResponse) { // Method to extract the "text" field from the JSON response, which contains the generated content from Gemini. This method uses a simple string search to find the value of the "text" field in the JSON response
        String responseText = extractJsonStringValue(jsonResponse, "text"); // Call the method to extract the value of the "text" field from the JSON response, which will return the generated content from Gemini as a string

        if (responseText.equals("")) { // If the extracted response text is an empty string, return a default error message indicating that the response text could not be found in the JSON response. This can happen if the API key is invalid, the model name is incorrect, or the request format is not correct, resulting in an unexpected JSON response that does not contain the expected "text" field
            return "Could not find response text in JSON. Check your API key, model name, and request format."; // Return a default error message if the "text" field is not found in the JSON response
        } // Close the if statement for checking if the response text is empty

        return responseText; // Return the extracted response text from the JSON response, which contains the generated content from Gemini
    } // Close the extractTextFromJson method

    public static String extractJsonStringValue(String jsonResponse, String fieldName) { // Method to extract the value of a specified string field from the JSON response
        String searchText = "\"" + fieldName + "\""; // Construct the search text to find the field name in the JSON response, which is the field name enclosed in double quotes (e.g., "text" for the "text" field)
        int fieldIndex = jsonResponse.indexOf(searchText); // Find the index of the field name in the JSON response using indexOf, which returns the index of the first occurrence of the search text or -1 if it is not found

        if (fieldIndex == -1) { // If the field name is not found in the JSON response, return an empty string to indicate that the value could not be extracted
            return ""; // Return an empty string if the specified field name is not found in the JSON response
        } // Close the if statement for checking if the field name is found in the JSON response

        int colonIndex = jsonResponse.indexOf(":", fieldIndex); // Find the index of the colon (:) that separates the field name from its value in the JSON response, starting the search from the index of the field name. This is necessary to locate the position of the value in the JSON response after finding the field name

        if (colonIndex == -1) { // If the colon is not found after the field name, return an empty string to indicate that the value could not be extracted
            return ""; // Return an empty string if the colon is not found after the field name in the JSON response, which indicates that the value cannot be properly extracted
        } // Close the if statement for checking if the colon is found after the field name in the JSON response

        int startQuoteIndex = jsonResponse.indexOf("\"", colonIndex); // Find the index of the opening double quote that starts the value of the field in the JSON response, starting the search from the index of the colon. This is necessary to locate the beginning of the value in the JSON response after finding the field name and the colon

        if (startQuoteIndex == -1) { // If the opening double quote is not found after the colon, return an empty string to indicate that the value could not be extracted. 
            return ""; // Return an empty string if the opening double quote is not found after the colon in the JSON response, which indicates that the value cannot be properly extracted
        } // Close the if statement for checking if the opening double quote is found after the colon in the JSON response

        int startIndex = startQuoteIndex + 1; // Calculate the starting index of the value by adding 1 to the index of the opening double quote, which will be used to extract the value from the JSON response. This is necessary because the value starts immediately after the opening double quote in the JSON response

        StringBuilder value = new StringBuilder(); // Create a StringBuilder to accumulate the characters of the value as it is extracted from the JSON response, which allows for efficient string concatenation as we read through the characters of the value
        boolean escaped = false; // A boolean flag to track whether the current character is escaped (preceded by a backslash) in the JSON response, which is necessary to properly handle escaped characters in the value

        for (int i = startIndex; i < jsonResponse.length(); i++) { // Loop through the characters of the JSON response starting from the index of the value, and continue until we reach the end of the JSON response. This loop will read through the characters of the value, handling escaped characters properly, until it reaches the closing double quote that ends the value in the JSON response
            char currentChar = jsonResponse.charAt(i); // Get the current character at index i in the JSON response, which will be processed to extract the value of the specified field while handling escaped characters properly

            if (escaped) { // If the current character is escaped, we need to handle it based on the escape sequence. This is necessary to properly interpret escaped characters in the JSON response, such as \n for newline, \t for tab, etc.
                if (currentChar == 'n') { // If the escaped character is 'n', it represents a newline character in JSON, so we append a newline to the value
                    value.append("\n"); // Append a newline character to the value if the escaped character is 'n'
                } else if (currentChar == 't') { // If the escaped character is 't', it represents a tab character in JSON, so we append a tab to the value
                    value.append("\t"); // Append a tab character to the value if the escaped character is 't'
                } else if (currentChar == 'r') { // If the escaped character is 'r', it represents a carriage return character in JSON, so we append a carriage return to the value
                    value.append("\r"); // Append a carriage return character to the value if the escaped character is 'r'
                } else if (currentChar == '"') { // If the escaped character is '"', it represents a double quote character in JSON, so we append a double quote to the value
                    value.append("\""); // Append a double quote character to the value if the escaped character is '"'
                } else if (currentChar == '\\') { // If the escaped character is '\', it represents a backslash character in JSON, so we append a backslash to the value
                    value.append("\\"); // Append a backslash character to the value if the escaped character is '\\'
                } else { // For any other escaped character, we can just append it as is to the value, as it may be a valid character that is escaped in the JSON response
                    value.append(currentChar); // Append the current character as is to the value if it is an escaped character that does not have a special meaning in JSON, allowing for proper handling of any valid escaped characters in the JSON response
                } // After handling the escaped character, we reset the escaped flag to false for the next character

                escaped = false;// Reset the escaped flag after processing an escaped character, so that the next character will be processed normally unless it is also escaped
            } else { // If the current character is not escaped, we need to check if it is a backslash (which indicates the start of an escape sequence) or a double quote (which indicates the end of the value), or just a regular character that should be appended to the value
                if (currentChar == '\\') { // If the current character is a backslash, it indicates the start of an escape sequence in JSON, so we set the escaped flag to true to indicate that the next character should be processed as an escaped character
                    escaped = true; // Set the escaped flag to true if the current character is a backslash, indicating that the next character should be processed as an escaped character in the JSON response
                } else if (currentChar == '"') { // If the current character is a double quote, it indicates the end of the value in JSON, so we break out of the loop as we have finished extracting the value
                    break; // Break out of the loop if the current character is a double quote, indicating that we have reached the end of the value in the JSON response and can stop extracting characters
                } else { // If the current character is a regular character that is not escaped and not a double quote, we append it to the value as part of the extracted string value from the JSON response
                    value.append(currentChar); // Append the current character to the value if it is a regular character that is not escaped and not a double quote, allowing us to build the complete value string from the JSON response as we read through its characters
                } // Close the if-else block for handling regular characters, backslashes, and double quotes in the JSON response while extracting the value
            } // Close the if-else block for handling escaped and non-escaped characters in the JSON response while extracting the value
        } // Close the for loop for reading through the characters of the JSON response to extract the value of the specified field

        return value.toString(); // Return the extracted value as a string by converting the StringBuilder to a string, which contains the value of the specified field from the JSON response
    } // Close the extractJsonStringValue method

    public static String escapeJson(String text) { // Method to escape special characters in the user input for JSON formatting, which is necessary to ensure that the user input can be safely included in the JSON request body without breaking the JSON structure. - 
    // - This method replaces special characters with their corresponding escape sequences in JSON (e.g., backslash, double quote, newline, etc.) to ensure that the JSON request is properly formatted and can be correctly parsed by the Gemini API
        StringBuilder escapedText = new StringBuilder(); // Create a StringBuilder to accumulate the characters of the escaped text as we process the input string, allowing for efficient string concatenation as we build the escaped version of the user input

        for (int i = 0; i < text.length(); i++) { // Loop through each character in the input text to check for special characters that need to be escaped for JSON formatting
            char currentChar = text.charAt(i); // Get the current character at index i in the input text, which will be checked to see if it is a special character that needs to be escaped for JSON formatting

            if (currentChar == '\\') { // If the current character is a backslash, it needs to be escaped as a double backslash in JSON, so we append two backslashes to the escaped text
                escapedText.append("\\\\"); // Append two backslashes to the escaped text if the current character is a backslash, which is necessary to escape the backslash character in JSON formatting
            } else if (currentChar == '"') { // If the current character is a double quote, it needs to be escaped as a backslash followed by a double quote in JSON, so we append a backslash and a double quote to the escaped text
                escapedText.append("\\\""); // Append a backslash and a double quote to the escaped text if the current character is a double quote, which is necessary to escape the double quote character in JSON formatting
            } else if (currentChar == '\n') { // If the current character is a newline, it needs to be escaped as \n in JSON, so we append the escape sequence for newline to the escaped text
                escapedText.append("\\n"); // Append the escape sequence for newline to the escaped text if the current character is a newline, which is necessary to escape the newline character in JSON formatting
            } else if (currentChar == '\r') { // If the current character is a carriage return, it needs to be escaped as \r in JSON, so we append the escape sequence for carriage return to the escaped text
                escapedText.append("\\r"); // Append the escape sequence for carriage return to the escaped text if the current character is a carriage return, which is necessary to escape the carriage return character in JSON formatting
            } else if (currentChar == '\t') { // If the current character is a tab, it needs to be escaped as \t in JSON, so we append the escape sequence for tab to the escaped text
                escapedText.append("\\t"); // Append the escape sequence for tab to the escaped text if the current character is a tab, which is necessary to escape the tab character in JSON formatting
            } else { // If the current character is not a special character that needs to be escaped, we simply append it as is to the escaped text, allowing regular characters to be included in the escaped version of the user input without modification
                escapedText.append(currentChar); // Append the current character as is to the escaped text if it is not a special character that needs to be escaped, allowing us to build the complete escaped version of the user input for JSON formatting while preserving regular characters
            } // Close the if-else block for handling special characters that need to be escaped and regular characters in the input text while building the escaped version for JSON formatting
        } // Close the for loop for processing each character in the input text to build the escaped version for JSON formatting

        return escapedText.toString(); // Return the escaped version of the input text as a string by converting the StringBuilder to a string, which contains the user input with special characters properly escaped for JSON formatting to ensure that it can be safely included in the JSON request body for the Gemini API
    } // Close the escapeJson method
} // Close the GeminiChatbot class