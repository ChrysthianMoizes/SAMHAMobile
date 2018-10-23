package com.br.chrys.samhamobile.dominio;

import java.io.Serializable;

public class MatrizCurricular implements Serializable, Comparable<Object> {

    private int id;
    private String nome;
    private int ano;
    private int semestre;
    private Curso curso;

    public MatrizCurricular() {
    }

    public MatrizCurricular(int id, String nome, int ano, int semestre, Curso curso) {
        this.id = id;
        this.nome = nome;
        this.ano = ano;
        this.semestre = semestre;
        this.curso = curso;
    }

    public MatrizCurricular(String nome, int ano, int semestre, Curso curso) {
        this.nome = nome;
        this.ano = ano;
        this.semestre = semestre;
        this.curso = curso;
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

    public int getAno() {
        return ano;
    }

    public void setAno(int anoCriacao) {
        this.ano = anoCriacao;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
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
        MatrizCurricular other = (MatrizCurricular) o;
        return this.getNome().compareTo(other.getNome());
    }
}
