package br.com.alura.dominio;

public class Pet {

    private long id;
    private final String tipo;
    private final String nome;
    private final String raca;
    private final int idade;
    private final String cor;
    private final float peso;

    public Pet(String tipo, String nome, String raca, int idade, String cor, float peso){
        this.tipo = tipo;
        this.nome = nome;
        this.raca = raca;
        this.idade = idade;
        this.cor = cor;
        this.peso = peso;
    }

    public String getNome() {
        return nome;
    }

    public long getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public String getRaca() {
        return raca;
    }

    public int getIdade() {
        return idade;
    }
}
