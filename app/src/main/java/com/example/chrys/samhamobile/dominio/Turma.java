package com.example.chrys.samhamobile.dominio;

import java.io.Serializable;

public class Turma implements Serializable, Comparable<Object> {
    
    private int id;
    private String nome;
    private String turno;
    private int ano;
    private int semestre;
    private MatrizCurricular matriz;
    
    public Turma() {
    }

    public Turma(int id, String nome, int ano, int semestre, String turno, MatrizCurricular matriz) {
        this.id = id;
        this.nome = nome;
        this.ano = ano;
        this.semestre = semestre;
        this.matriz = matriz;
        this.turno = turno;
    }

    public Turma(String nome, int ano, int semestre, String turno, MatrizCurricular matriz) {
        this.nome = nome;
        this.ano = ano;
        this.semestre = semestre;
        this.matriz = matriz;
        this.turno = turno;
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

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }
    
    public MatrizCurricular getMatriz() {
        return matriz;
    }

    public void setMatriz(MatrizCurricular matriz) {
        this.matriz = matriz;
    }
    
    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }
   
    @Override
    public String toString() {
        return nome;
    }
    
    public Object[] toArray() {
        return new Object[] { this, getAnoSemestre(), getMatriz().getNome(), getMatriz().getCurso().getNome(), getTurno() };
    }
    
    public String getAnoSemestre(){
        return String.valueOf(getAno()) + "/" + String.valueOf(getSemestre());
    }

    @Override
    public int compareTo(Object o) {
        
        Turma other = (Turma) o;
        return this.getNome().compareTo(other.getNome());
        
    }
}
