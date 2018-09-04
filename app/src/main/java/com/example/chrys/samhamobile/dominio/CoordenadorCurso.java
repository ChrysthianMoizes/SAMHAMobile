package com.example.chrys.samhamobile.dominio;

public class CoordenadorCurso extends Usuario{

    private Professor professor;

    public CoordenadorCurso() {
    }

    public CoordenadorCurso(Professor professor, int id, String login, String senha) {
        super(id, login, senha);
        this.professor = professor;
    }

    public CoordenadorCurso(Professor professor, String login, String senha) {
        super(login, senha);
        this.professor = professor;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    @Override
    public String toString() {
        return professor.getNome();
    }

    public Object[] toArray() {
        return new Object[] { this, getProfessor().getMatricula(), "COORDENADOR DE CURSO"};
    }
}
