package com.example.chrys.samhamobile.dominio;

import java.util.Comparator;

public class ComparadorUsuario implements Comparator<Usuario>{

    @Override
    public int compare(Usuario o1, Usuario o2) {
        
        String nomeO1 = identificarUsuario(o1);
        String nomeO2 = identificarUsuario(o2);
        return nomeO1.compareTo(nomeO2);
        
    }
    
    public String identificarUsuario(Usuario usuario){
        
        String nome = "";
        
        if(usuario instanceof CoordenadorCurso){
            
            CoordenadorCurso other = (CoordenadorCurso) usuario;
            nome = other.getProfessor().getNome();
            
        }else{
            
            if(usuario instanceof CoordenadorAcademico){
                CoordenadorAcademico other = (CoordenadorAcademico) usuario;  
                nome = other.getServidor().getNome();
                
            }else{
                CoordenadorPedagogico other = (CoordenadorPedagogico) usuario;
                nome = other.getServidor().getNome();
            } 
        }       
        return nome;
    }
}
