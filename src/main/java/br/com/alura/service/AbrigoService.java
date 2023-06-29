package br.com.alura.service;

import br.com.alura.client.ClientHttpConfiguration;
import br.com.alura.dominio.Abrigo;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class AbrigoService {

    private ClientHttpConfiguration client;

    public AbrigoService(ClientHttpConfiguration client) {
        this.client = client;
    }

    public void cadastrarAbrigo() throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o nome do abrigo:");
        String nome = scanner.nextLine();
        System.out.println("Digite o telefone do abrigo:");
        String telefone = scanner.nextLine();
        System.out.println("Digite o email do abrigo:");
        String email = scanner.nextLine();

        Abrigo abrigo = new Abrigo(nome, telefone, email);

        HttpResponse<String> response = client.dispararRequisicaoPost("http://localhost:8080/abrigos", abrigo);
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

    public void listarAbrigos() throws IOException, InterruptedException {
        HttpResponse<String> response = client.dispararRequisicaoGet("http://localhost:8080/abrigos");
        String responseBody = response.body();
        Abrigo[] abrigos = new ObjectMapper().readValue(responseBody, Abrigo[].class);
        if(abrigos.length != 0) {
            mostrarAbrigosCadastrados(abrigos);
        } else {
            System.out.println("Não há abrigos cadastrados");
        }
    }

    private void mostrarAbrigosCadastrados(Abrigo[] abrigos) {
        System.out.println("Abrigos cadastrados:");
        List<Abrigo> abrigoList = Arrays.stream(abrigos).toList();
        for (Abrigo abrigo : abrigoList) {
            long id = abrigo.getId();
            String nome = abrigo.getNome();
            System.out.println(id +" - " +nome);
        }
    }
}
