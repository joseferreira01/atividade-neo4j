/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.bancoII.neo4j.entity;

import java.util.Objects;

/**
 *
 * @author jose2
 */
public class Usuario {
    private String CPF;
    private String nome;
    private int idade;

    public Usuario() {
    }

    public Usuario(String CPF, String nome, int idad) {
        this.CPF = CPF;
        this.nome = nome;
        this.idade = idad;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idad) {
        this.idade = idad;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.CPF);
        hash = 53 * hash + Objects.hashCode(this.nome);
        hash = 53 * hash + this.idade;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuario other = (Usuario) obj;
        if (this.idade != other.idade) {
            return false;
        }
        if (!Objects.equals(this.CPF, other.CPF)) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Pessoa{" + "CPF=" + CPF + ", nome=" + nome + ", idad=" + idade + '}';
    }
    
    
}
