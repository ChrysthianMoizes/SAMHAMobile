package com.example.chrys.samhamobile.dominio;

import java.io.Serializable;

public class Eixo implements Serializable, Comparable<Object> {

    private int id;
    private String nome;

    public Eixo() {
    }

    public Eixo(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Eixo(String nome) {
        this.nome = nome;
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

    @Override
    public String toString() {
        return nome;
    }
    
    public Object[] toArray() {
        return new Object[] { this };
    }   

    @Override
    public int compareTo(Object o) {
        Eixo other = (Eixo) o;
        return this.getNome().compareTo(other.getNome());
    }
}