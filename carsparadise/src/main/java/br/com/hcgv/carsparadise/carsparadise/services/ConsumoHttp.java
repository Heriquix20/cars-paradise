package br.com.hcgv.carsparadise.carsparadise.services;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsumoHttp {

    public String comsumirApi(String endereco) {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endereco))
                .build();
        HttpResponse<String> response = null;
        try {
            response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            System.out.println("Erro ao tentar consumir a API");
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            System.out.println("Erro ao tentar consumir a API");
            throw new RuntimeException(e);
        }

        String json = response.body();
        return json;
    }
}
