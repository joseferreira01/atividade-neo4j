/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.bancoII.neo4j.entity;

import java.util.Date;

/**
 *
 * @author jose
 */
public class Publicacao {
    private long ID;
    private Date data;
    private String texto;

    public Publicacao() {
    }

    public Publicacao(long ID, Date data, String texto) {
        this.ID = ID;
        this.data = data;
        this.texto = texto;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
    
    
}
