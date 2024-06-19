import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class ApiConnexion{

    String direccionUrl = "https://v6.exchangerate-api.com/v6/89156fda7dea8296340834e4/pair/";

    IntercambioDeDivisa intercambio(String monedaParaCambio, String conversionDeMoneda) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(direccionUrl + monedaParaCambio + "/" + conversionDeMoneda))
                .build();
        Gson gson = new Gson();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return gson.fromJson(response.body(), IntercambioDeDivisa.class);
        } catch (IOException | InterruptedException e) {
            System.err.println("Error al realizar la solicitud a la API: " + e.getMessage());
        } catch (JsonSyntaxException e) {
            System.err.println("Error al deserializar la respuesta de la API: " + e.getMessage());
        }
        return null;
    }
}

