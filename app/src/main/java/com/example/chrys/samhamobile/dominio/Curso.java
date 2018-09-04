package com.example.chrys.samhamobile.dominio;

import java.io.Serializable;

public class Curso implements Serializable, Comparable<Object> {

    private int id;
    private String nome;
    private int qtPeriodos;
    private String nivel;
    private Coordenadoria coordenadoria;
    private CoordenadorCurso coordenador;

    public Curso() {
    }

    public Curso(int id, String nome, int qtPeriodos, String nivel, Coordenadoria coordenadoria, CoordenadorCurso coordenador) {
        this.id = id;
        this.nome = nome;
        this.qtPeriodos = qtPeriodos;
        this.nivel = nivel;
        this.coordenadoria = coordenadoria;
        this.coordenador = coordenador;
    }

    public Curso(String nome, int qtPeriodos, String nivel, Coordenadoria coordenadoria, CoordenadorCurso coordenador) {
        this.nome = nome;
        this.qtPeriodos = qtPeriodos;
        this.nivel = nivel;
        this.coordenadoria = coordenadoria;
        this.coordenador = coordenador;
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

    public int getQtPeriodos() {
        return qtPeriodos;
    }

    public void setQtPeriodos(int qtPeriodos) {
        this.qtPeriodos = qtPeriodos;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public Coordenadoria getCoordenadoria() {
        return coordenadoria;
    }

    public void setCoordenadoria(Coordenadoria coordenadoria) {
        this.coordenadoria = coordenadoria;
    }

    public CoordenadorCurso getCoordenador() {
        return coordenador;
    }

    public void setCoordenador(CoordenadorCurso coordenador) {
        this.coordenador = coordenador;
    }
    
    @Override
    public String toString() {
        return nome;
    }
   
    public Object[] toArray() {
        return new Object[] { this, getNivel()};
    }

    @Override
    public int compareTo(Object o) {
        Curso other = (Curso) o;
        return this.getNome().compareTo(other.getNome());
    }
}
