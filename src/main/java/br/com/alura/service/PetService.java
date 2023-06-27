package br.com.alura.service;

import br.com.alura.client.ClientHttpConfiguration;
import br.com.alura.dominio.Pet;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class PetService {

    private ClientHttpConfiguration client;

    public PetService(ClientHttpConfiguration client) {
        this.client = client;
    }

    public boolean importarPetsDoAbrigo() throws IOException, InterruptedException {
        System.out.println("Digite o id ou nome do abrigo:");
        String idOuNome = lerDoTeclado();

        System.out.println("Digite o nome do arquivo CSV:");
        String nomeArquivo = lerDoTeclado();

        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(nomeArquivo));
        } catch (IOException e) {
            System.out.println("Erro ao carregar o arquivo: " +nomeArquivo);
            return true;
        }
        String line;
        while ((line = reader.readLine()) != null) {
            String[] campos = line.split(",");
            Pet pet = criarPet(campos);

            HttpResponse<String> response = client.dispararRequisicaoPost("http://localhost:8080/abrigos/" + idOuNome + "/pets", pet);
            int statusCode = response.statusCode();
            String responseBody = response.body();
            if (statusCode == 200) {
                System.out.println("Pet cadastrado com sucesso: " + pet.getNome());
            } else if (statusCode == 404) {
                System.out.println("Id ou nome do abrigo não encontado!");
                break;
            } else if (statusCode == 400 || statusCode == 500) {
                System.out.println("Erro ao cadastrar o pet: " + pet.getNome());
                System.out.println(responseBody);
                break;
            }
        }
        reader.close();
        return false;
    }

    private Pet criarPet(String[] campos) {
        return new Pet(
                campos[0].toUpperCase(),
                campos[1],
                campos[2],
                Integer.parseInt(campos[3]),
                campos[4],
                Float.parseFloat(campos[5])
        );
    }

    public boolean listarPetsDoAbrigo() throws IOException, InterruptedException {
        System.out.println("Digite o id ou nome do abrigo:");
        String idOuNome = lerDoTeclado();

        HttpResponse<String> response = client.dispararRequisicaoGet("http://localhost:8080/abrigos/" + idOuNome + "/pets");
        int statusCode = response.statusCode();
        if (statusCode == 404 || statusCode == 500) {
            System.out.println("ID ou nome não cadastrado!");
            return true;
        }
        String responseBody = response.body();
        System.out.println("Pets cadastrados:");
        Pet[] pets = new ObjectMapper().readValue(responseBody, Pet[].class);
        List<Pet> petList = Arrays.stream(pets).toList();
        for (Pet pet : petList) {
            long id = pet.getId();
            String tipo = pet.getTipo();
            String nome = pet.getNome();
            String raca = pet.getRaca();
            int idade = pet.getIdade();
            System.out.println(id +" - " +tipo +" - " +nome +" - " +raca +" - " +idade +" ano(s)");
        }
        return false;
    }

    private String lerDoTeclado() {
        return new Scanner(System.in).nextLine();
    }
}
