package br.com.alura.dominio;

public class Abrigo {

    private long id;
    private String nome;
    private String telefone;
    private String email;
    private Pet[] pets;

    public Abrigo() {

    }

    public Abrigo(String nome, String telefone, String email) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Pet[] getPets() {
        return pets;
    }
}
