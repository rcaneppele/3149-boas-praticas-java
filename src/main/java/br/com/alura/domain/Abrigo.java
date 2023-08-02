package br.com.alura.domain;

public class Abrigo {

    public Abrigo() {

    }

    public Abrigo(String nome, String telefone, String email) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }

    private Long id;
    private String nome;
    private String telefone;
    private String email;
    private Pet[] pets;

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public Pet[] getPets() {
        return pets;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return """
               "id":%s,"nome":"%s","telefone":"%s","email":"%s"
               """.formatted(this.id, this.nome, this.telefone, this.email);
    }
}
