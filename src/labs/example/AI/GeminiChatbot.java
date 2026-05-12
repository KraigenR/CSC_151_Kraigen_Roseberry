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

public class GeminiChatbot {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Paste your API key between the quotation marks.
        // Do not share this key or submit screenshots with it visible.
        String apiKey = "AIzaSyA98kzSn3DG8P5SjLS3YSuyBeOwTpCT7xk";

        System.out.println("Simple Chatbot using Gemini 2.5 Flash-Lite API");
        System.out.println("------------------------------------------------------");
        System.out.println("Type 'exit' to quit.");

        while (true) {
            System.out.print("You: ");
            String userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("exit")) {
                System.out.println("Gemini: Goodbye!");
                break;
            }

            try {
                String geminiResponse = callGeminiAPI(userInput, apiKey);
                System.out.println("Gemini: " + geminiResponse);
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }

        scanner.close();
    }

    public static String callGeminiAPI(String userInput, String apiKey) throws IOException, URISyntaxException {
        String urlString = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash-lite:generateContent?key=" + apiKey;

        URI uri = new URI(urlString);
        URL url = uri.toURL();

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        String jsonInput = "{"
                + "\"contents\":["
                + "{"
                + "\"parts\":["
                + "{"
                + "\"text\":\"" + escapeJson(userInput) + "\""
                + "}"
                + "]"
                + "}"
                + "]"
                + "}";

        try (OutputStream outputStream = connection.getOutputStream()) {
            byte[] input = jsonInput.getBytes(StandardCharsets.UTF_8);
            outputStream.write(input, 0, input.length);
        }

        int responseCode = connection.getResponseCode();

        BufferedReader reader;

        if (responseCode >= 200 && responseCode < 300) {
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
        } else {
            reader = new BufferedReader(new InputStreamReader(connection.getErrorStream(), StandardCharsets.UTF_8));
        }

        StringBuilder response = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            response.append(line);
        }

        reader.close();
        connection.disconnect();

        String jsonResponse = response.toString();

        if (responseCode >= 200 && responseCode < 300) {
            return extractTextFromJson(jsonResponse);
        } else {
            String errorMessage = extractJsonStringValue(jsonResponse, "message");
            return "API error " + responseCode + ": " + errorMessage;
        }
    }

    public static String extractTextFromJson(String jsonResponse) {
        String responseText = extractJsonStringValue(jsonResponse, "text");

        if (responseText.equals("")) {
            return "Could not find response text in JSON. Check your API key, model name, and request format.";
        }

        return responseText;
    }

    public static String extractJsonStringValue(String jsonResponse, String fieldName) {
        String searchText = "\"" + fieldName + "\"";
        int fieldIndex = jsonResponse.indexOf(searchText);

        if (fieldIndex == -1) {
            return "";
        }

        int colonIndex = jsonResponse.indexOf(":", fieldIndex);

        if (colonIndex == -1) {
            return "";
        }

        int startQuoteIndex = jsonResponse.indexOf("\"", colonIndex);

        if (startQuoteIndex == -1) {
            return "";
        }

        int startIndex = startQuoteIndex + 1;

        StringBuilder value = new StringBuilder();
        boolean escaped = false;

        for (int i = startIndex; i < jsonResponse.length(); i++) {
            char currentChar = jsonResponse.charAt(i);

            if (escaped) {
                if (currentChar == 'n') {
                    value.append("\n");
                } else if (currentChar == 't') {
                    value.append("\t");
                } else if (currentChar == 'r') {
                    value.append("\r");
                } else if (currentChar == '"') {
                    value.append("\"");
                } else if (currentChar == '\\') {
                    value.append("\\");
                } else {
                    value.append(currentChar);
                }

                escaped = false;
            } else {
                if (currentChar == '\\') {
                    escaped = true;
                } else if (currentChar == '"') {
                    break;
                } else {
                    value.append(currentChar);
                }
            }
        }

        return value.toString();
    }

    public static String escapeJson(String text) {
        StringBuilder escapedText = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char currentChar = text.charAt(i);

            if (currentChar == '\\') {
                escapedText.append("\\\\");
            } else if (currentChar == '"') {
                escapedText.append("\\\"");
            } else if (currentChar == '\n') {
                escapedText.append("\\n");
            } else if (currentChar == '\r') {
                escapedText.append("\\r");
            } else if (currentChar == '\t') {
                escapedText.append("\\t");
            } else {
                escapedText.append(currentChar);
            }
        }

        return escapedText.toString();
    }
}