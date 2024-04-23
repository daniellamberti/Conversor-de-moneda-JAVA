import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultaCurrency {

    public String buscarCurrencyJson(String USD) {
        URI uri = URI.create("https://v6.exchangerate-api.com/v6/25169bf2063df0bfeb6b2bb1/latest/" + "USD");

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error fetching currency data", e);
        }
    }
}
