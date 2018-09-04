package com.example.chrys.samhamobile.dominio;

import java.io.Serializable;

public class Aula implements Serializable, Comparable<Object> {

    private int id;
    private int numero;
    private int dia;
    private int turno;
    private Alocacao alocacao;
    private Oferta oferta;
    
    public Aula() {
    }

    public Aula(int id, int numero, int dia, int turno, Alocacao alocacao, Oferta oferta) {
        this.id = id;
        this.numero = numero;
        this.dia = dia;
        this.turno = turno;
        this.alocacao = alocacao;
        this.oferta = oferta;
    }

    public Aula(int numero, int dia, int turno, Alocacao alocacao, Oferta oferta) {
        this.numero = numero;
        this.dia = dia;
        this.turno = turno;
        this.alocacao = alocacao;
        this.oferta = oferta;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getTurno() {
        return turno;
    }

    public void setTurno(int turno) {
        this.turno = turno;
    }

    public Alocacao getAlocacao() {
        return alocacao;
    }

    public void setAlocacao(Alocacao alocacao) {
        this.alocacao = alocacao;
    }

    public Oferta getOferta() {
        return oferta;
    }

    public void setOferta(Oferta oferta) {
        this.oferta = oferta;
    }
    
    @Override
    public String toString() { 
        String retorno = getAlocacao().getDisciplina().getSigla() + " - " + getAlocacao().getProfessor1().obterNomeAbreviado();
        if(getAlocacao().getDisciplina().getTipo().toUpperCase().equals("ESPECIAL")){
            retorno = retorno + "/" + getAlocacao().getProfessor2().obterNomeAbreviado();
        }
        return retorno;
    }

    @Override
    public int compareTo(Object o) {
       
        Aula other = (Aula) o;
        if(this.getNumero() > other.getNumero())
            return 1;
        return -1;
    }
}
