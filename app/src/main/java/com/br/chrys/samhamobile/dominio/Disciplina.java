package com.br.chrys.samhamobile.dominio;

import java.io.Serializable;

public class Disciplina implements Serializable, Comparable<Object> {
    
    private int id;
    private String nome;
    private String sigla;
    private String tipo;
    private int cargaHoraria;
    private int qtAulas;
    private int periodo;
    private MatrizCurricular matriz;

    public Disciplina() {
    }

    public Disciplina(int id, String nome, String sigla, String tipo, int cargaHoraria, int qtAulas, MatrizCurricular matriz, int periodo) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.cargaHoraria = cargaHoraria;
        this.qtAulas = qtAulas;
        this.matriz = matriz;
        this.periodo = periodo;
        this.sigla = sigla;
    }

    public Disciplina(String nome, String sigla, String tipo, int cargaHoraria, int qtAulas, MatrizCurricular matriz, int periodo) {
        this.nome = nome;
        this.tipo = tipo;
        this.cargaHoraria = cargaHoraria;
        this.qtAulas = qtAulas;
        this.matriz = matriz;
        this.periodo = periodo;
        this.sigla = sigla;
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

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }
    
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public int getQtAulas() {
        return qtAulas;
    }

    public int getPeriodo() {
        return periodo;
    }

    public void setPeriodo(int periodo) {
        this.periodo = periodo;
    }


    public void setQtAulas(int qtAulas) {
        this.qtAulas = qtAulas;
    }

    public MatrizCurricular getMatriz() {
        return matriz;
    }

    public void setMatriz(MatrizCurricular matriz) {
        this.matriz = matriz;
    }

    @Override
    public String toString() {
        return nome;
    }
    
    public Object[] toArray() {
        return new Object[] { this, getQtAulas(), getTipo() };
    }

    @Override
    public int compareTo(Object o) {
        Disciplina other = (Disciplina) o; 
        return this.getNome().compareTo(other.getNome());
    }
    
}
