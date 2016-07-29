package br.com.androidessencial.carros.domain;

/**
 * Created by Paulo Andrade local on 24/07/2016.
 */
public class Usuario{
    private int id;
    private String nome;
    private String email;

    public void Usuario(){
        this.setId(0);
        this.setNome("");
        this.setEmail("@");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
