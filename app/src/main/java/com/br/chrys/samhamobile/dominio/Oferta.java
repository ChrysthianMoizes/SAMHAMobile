package com.br.chrys.samhamobile.dominio;

import java.io.Serializable;

public class Oferta implements Serializable {

    private int id;
    private int ano;
    private int semestre;
    private int tempoMaximoTrabalho;
    private int intervaloMinimo;
    private Turma turma;

    public Oferta() {
    }

    public Oferta(int id, int ano, int semestre, int tempoMaximoTrabalho, int intervaloMinimo, Turma turma) {
        this.id = id;
        this.semestre = semestre;
        this.tempoMaximoTrabalho = tempoMaximoTrabalho;
        this.intervaloMinimo = intervaloMinimo;
        this.turma = turma;
    }

    public Oferta(int ano, int semestre, int tempoMaximoTrabalho, int intervaloMinimo, Turma turma) {
        this.semestre = semestre;
        this.tempoMaximoTrabalho = tempoMaximoTrabalho;
        this.intervaloMinimo = intervaloMinimo;
        this.turma = turma;
    }

    public double getTempoMaximoTrabalho() {
        return tempoMaximoTrabalho;
    }

    public void setTempoMaximoTrabalho(int tempoMaximoTrabalho) {
        this.tempoMaximoTrabalho = tempoMaximoTrabalho;
    }

    public double getIntervaloMinimo() {
        return intervaloMinimo;
    }

    public void setIntervaloMinimo(int intervaloMinimo) {
        this.intervaloMinimo = intervaloMinimo;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    } 
}
