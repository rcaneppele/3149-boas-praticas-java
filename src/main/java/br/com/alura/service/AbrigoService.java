package br.com.alura.service;

import br.com.alura.client.ClientHttpConfiguration;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class AbrigoService {

    private ClientHttpConfiguration client;

    public AbrigoService(ClientHttpConfiguration client) {
        this.client = client;
    }

    public void listarAbrigo() throws IOException, InterruptedException {
        String uri = "http://localhost:8080/abrigos";
        HttpResponse<String> response = client.dispararRequisicaoGet(uri);
        String responseBody = response.body();
        JsonArray jsonArray = JsonParser.parseString(responseBody).getAsJsonArray();
        System.out.println("Abrigos cadastrados:");
        for (JsonElement element : jsonArray) {
            JsonObject jsonObject = element.getAsJsonObject();
            long id = jsonObject.get("id").getAsLong();
            String nome = jsonObject.get("nome").getAsString();
            System.out.println(id +" - " +nome);
        }
    }

    public void cadastrarAbrigo() throws IOException, InterruptedException {
        System.out.println("Digite o nome do abrigo:");
        String nome = new Scanner(System.in).nextLine();
        System.out.println("Digite o telefone do abrigo:");
        String telefone = new Scanner(System.in).nextLine();
        System.out.println("Digite o email do abrigo:");
        String email = new Scanner(System.in).nextLine();

        JsonObject json = new JsonObject();
        json.addProperty("nome", nome);
        json.addProperty("telefone", telefone);
        json.addProperty("email", email);

        String uri = "http://localhost:8080/abrigos";
        HttpResponse<String> response = client.dispararRequisicaoPost(uri, json);
        int statusCode = response.statusCode();
        String responseBody = response.body();
        if (statusCode == 200) {
            System.out.println("Abrigo cadastrado com sucesso!");
            System.out.println(responseBody);
        } else if (statusCode == 400 || statusCode == 500) {
            System.out.println("Erro ao cadastrar o abrigo:");
            System.out.println(responseBody);
        }
    }
}