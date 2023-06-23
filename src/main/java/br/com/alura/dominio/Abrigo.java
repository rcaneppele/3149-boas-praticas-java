package br.com.alura.dominio;

public class Abrigo {

    private long id;
    private final String nome;
    private final String telefone;
    private final String email;

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
}
