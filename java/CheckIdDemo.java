import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CheckIdDemo {
    private static final String BASE_URL = "https://api.checkid.co.za";
    private static final String SAMPLE_ID = "8903075555083";
    private static final String BULK_BODY = "{\"idNumbers\":[\"8903075555083\",\"9001015800088\"]}";

    public static void main(String[] args) throws IOException, InterruptedException {
        String apiKey = requireApiKey();
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest singleRequest = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/api/v1/validate/" + SAMPLE_ID))
                .header("Authorization", "Bearer " + apiKey)
                .GET()
                .build();

        System.out.println("Single validation:");
        printResponse(client.send(singleRequest, HttpResponse.BodyHandlers.ofString()));

        HttpRequest bulkRequest = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/api/v1/validate/bulk"))
                .header("Authorization", "Bearer " + apiKey)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(BULK_BODY))
                .build();

        System.out.println("\nBulk validation:");
        printResponse(client.send(bulkRequest, HttpResponse.BodyHandlers.ofString()));
    }

    private static String requireApiKey() {
        String apiKey = System.getenv("CHECKID_API_KEY");
        if (apiKey == null || apiKey.isBlank()) {
            System.err.println("Set CHECKID_API_KEY before running this example.");
            System.exit(1);
        }

        return apiKey;
    }

    private static void printResponse(HttpResponse<String> response) {
        int status = response.statusCode();
        if (status == 400) {
            System.out.println("Bad request: check the ID number or JSON body.");
        } else if (status == 401) {
            System.out.println("Unauthorised: check CHECKID_API_KEY.");
        } else if (status >= 200 && status < 300) {
            System.out.println("Success");
        } else {
            System.out.println("Unexpected status: " + status);
        }

        System.out.println(response.body());
    }
}
