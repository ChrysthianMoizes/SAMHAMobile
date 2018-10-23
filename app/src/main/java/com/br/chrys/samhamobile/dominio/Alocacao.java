package com.br.chrys.samhamobile.dominio;

import java.io.Serializable;

public class Alocacao implements Serializable, Comparable<Object>{

    private int id;
    private int ano;
    private int semestre;
    private Disciplina disciplina;
    private Professor professor1;
    private Professor professor2;

    public Alocacao() {
    }

    public Alocacao(int id, int ano, int semestre, Disciplina disciplina, Professor professor1, Professor professor2) {
        this.id = id;
        this.ano = ano;
        this.semestre = semestre;
        this.disciplina = disciplina;
        this.professor1 = professor1;
        this.professor2 = professor2;
    }

    public Alocacao(int ano, int semestre, Disciplina disciplina, Professor professor1, Professor professor2) {
        this.ano = ano;
        this.semestre = semestre;
        this.disciplina = disciplina;
        this.professor1 = professor1;
        this.professor2 = professor2;
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

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public Professor getProfessor1() {
        return professor1;
    }

    public void setProfessor1(Professor professor1) {
        this.professor1 = professor1;
    }

    public Professor getProfessor2() {
        return professor2;
    }

    public void setProfessor2(Professor professor2) {
        this.professor2 = professor2;
    }
    
    @Override
    public String toString() {
        
        String retorno = disciplina.getSigla()+ " - " + professor1.obterNomeAbreviado();
        if(disciplina.getTipo().toUpperCase().equals("ESPECIAL")){
            retorno = retorno + "/" + professor2.obterNomeAbreviado();
        }
        return retorno;
    }
    
    public Object[] toArray() {
        return new Object[] { this, disciplina.getPeriodo() };
    }

    @Override
    public int compareTo(Object o) {
        Alocacao other = (Alocacao) o;  
        return this.getDisciplina().getSigla().compareTo(other.getDisciplina().getSigla());
    }
}
