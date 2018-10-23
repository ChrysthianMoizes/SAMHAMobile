package com.br.chrys.samhamobile.dominio;

import java.io.Serializable;

public class RestricaoProfessor implements Serializable{
    
    private int id;
    private String nome;
    private String descricao;
    private int dia;
    private String turno;
    private boolean aula1;
    private boolean aula2;
    private boolean aula3;
    private boolean aula4;
    private boolean aula5;
    private boolean aula6;
    private String prioridade;
    private Professor professor;
    
    public RestricaoProfessor(){    
    }

    public RestricaoProfessor(int id, String nome, String descricao, int dia, String turno, 
            boolean aula1, boolean aula2, boolean aula3, boolean aula4, boolean aula5, boolean aula6, String prioridade, Professor professor) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.dia = dia;
        this.turno = turno;
        this.aula1 = aula1;
        this.aula2 = aula2;
        this.aula3 = aula3;
        this.aula4 = aula4;
        this.aula5 = aula5;
        this.aula6 = aula6;
        this.prioridade = prioridade;
        this.professor = professor;
    }

    public RestricaoProfessor(String nome, String descricao, int dia, String turno,
            boolean aula1, boolean aula2, boolean aula3, boolean aula4, boolean aula5, boolean aula6, String prioridade, Professor professor) {
        this.nome = nome;
        this.descricao = descricao;
        this.dia = dia;
        this.turno = turno;
        this.aula1 = aula1;
        this.aula2 = aula2;
        this.aula3 = aula3;
        this.aula4 = aula4;
        this.aula5 = aula5;
        this.aula6 = aula6;
        this.prioridade = prioridade;
        this.professor = professor;
    }

    public boolean isAula1() {
        return aula1;
    }

    public void setAula1(boolean aula1) {
        this.aula1 = aula1;
    }

    public boolean isAula2() {
        return aula2;
    }

    public void setAula2(boolean aula2) {
        this.aula2 = aula2;
    }

    public boolean isAula3() {
        return aula3;
    }

    public void setAula3(boolean aula3) {
        this.aula3 = aula3;
    }

    public boolean isAula4() {
        return aula4;
    }

    public void setAula4(boolean aula4) {
        this.aula4 = aula4;
    }

    public boolean isAula5() {
        return aula5;
    }

    public void setAula5(boolean aula5) {
        this.aula5 = aula5;
    }

    public boolean isAula6() {
        return aula6;
    }

    public void setAula6(boolean aula6) {
        this.aula6 = aula6;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    @Override
    public String toString() {
        return nome + " - " + dia + " - " + turno;
    }
}
