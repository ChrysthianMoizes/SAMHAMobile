package com.br.chrys.samhamobile.dominio;

public class CoordenadorAcademico extends Usuario{

    private Servidor servidor;

    public CoordenadorAcademico() {
    }

    public CoordenadorAcademico(Servidor servidor, int id, String login, String senha) {
        super(id, login, senha);
        this.servidor = servidor;
    }

    public CoordenadorAcademico(Servidor servidor, String login, String senha) {
        super(login, senha);
        this.servidor = servidor;
    }

    public Servidor getServidor() {
        return servidor;
    }

    public void setServidor(Servidor servidor) {
        this.servidor = servidor;
    }
    
    @Override
    public String toString() {
        return servidor.getNome();
    }

    public Object[] toArray() {
        return new Object[] { this, getServidor().getMatricula(), "COORDENADOR ACADÊMICO" };
    }
}
