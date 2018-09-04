package com.example.chrys.samhamobile.dominio;

import java.io.Serializable;

public class Coordenadoria implements Serializable, Comparable<Object> {

    private int id;
    private String nome;
    private Eixo eixo;
    
    public Coordenadoria() {
    }

    public Coordenadoria(int id, String nome, Eixo eixo) {
        this.id = id;
        this.nome = nome;
        this.eixo = eixo;
    }

    public Coordenadoria(String nome, Eixo eixo) {
        this.nome = nome;
        this.eixo = eixo;
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

    public Eixo getEixo() {
        return eixo;
    }

    public void setEixo(Eixo eixo) {
        this.eixo = eixo;
    }
    
    @Override
    public String toString() {
        return nome;
    }
    
    public Object[] toArray() {
        return new Object[] { this };
    }

    @Override
    public int compareTo(Object o) {
        Coordenadoria other = (Coordenadoria) o;
        return this.getNome().compareTo(other.getNome());
    }
}
