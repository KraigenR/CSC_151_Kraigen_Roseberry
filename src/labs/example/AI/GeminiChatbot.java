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

        String apiKey = "AIzaSyC_APKaBFaPSd1DdOtcJ2tfaqZ8R79W6NQ";

        System.out.println("Simple Chatbot using Gemini 2.0 Flash API");
        System.out.println("------------------------------------------");
        System.out.println("Type 'exit' to quit.");

        while (true) {
            System.out.print("You: ");
            String userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("exit")) {
                System.out.println("Gemini: Goodbye!");
                break;
            }

            try {
                String response = callGeminiAPI(userInput, apiKey);
                String responseText = extractTextFromJson(response);

                System.out.println("Gemini: " + responseText);
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }

        scanner.close();
    }

    public static String callGeminiAPI(String userInput, String apiKey) throws IOException, URISyntaxException {
        String urlString = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent?key=" + apiKey;

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

        BufferedReader reader;

        if (connection.getResponseCode() >= 200 && connection.getResponseCode() < 300) {
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

        return response.toString();
    }

    public static String extractTextFromJson(String jsonResponse) {
        String searchText = "\"text\": \"";
        int startIndex = jsonResponse.indexOf(searchText);

        if (startIndex == -1) {
            return "Could not find response text in JSON.";
        }

        startIndex = startIndex + searchText.length();

        int endIndex = jsonResponse.indexOf("\"", startIndex);

        if (endIndex == -1) {
            return "Could not find the end of the response text.";
        }

        String responseText = jsonResponse.substring(startIndex, endIndex);

        responseText = responseText.replace("\\n", "\n");
        responseText = responseText.replace("\\\"", "\"");
        responseText = responseText.replace("\\\\", "\\");

        return responseText;
    }

    public static String escapeJson(String text) {
        return text.replace("\\", "\\\\")
                   .replace("\"", "\\\"");
    }
}