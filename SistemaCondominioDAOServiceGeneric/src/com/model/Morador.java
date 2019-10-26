/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import com.dao.GenericDAO;
import com.sun.istack.internal.NotNull;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Carlos
 */
@Entity
public class Morador implements GenericDAO, Serializable{
     //chave primária
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//Auto -> escolhe o melhor / identity -> gara sozinho /  SEQUENCE -> quando bd não possui suporte pra auto inclemento
    private Integer codigo;
    private String nome;
    @NotNull
    private String cpf;
    private String rg;
    private String dataNasc;
    private String Nacionalidade;
    private String numApt;
    private String blocoApt;
    private String AndarApt;

    public Integer getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getRg() {
        return rg;
    }

    public String getDataNasc() {
        return dataNasc;
    }

    public String getNacionalidade() {
        return Nacionalidade;
    }

    public String getNumApt() {
        return numApt;
    }

    public String getBlocoApt() {
        return blocoApt;
    }

    public String getAndarApt() {
        return AndarApt;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public void setDataNasc(String dataNasc) {
        this.dataNasc = dataNasc;
    }

    public void setNacionalidade(String Nacionalidade) {
        this.Nacionalidade = Nacionalidade;
    }

    public void setNumApt(String numApt) {
        this.numApt = numApt;
    }

    public void setBlocoApt(String blocoApt) {
        this.blocoApt = blocoApt;
    }

    public void setAndarApt(String AndarApt) {
        this.AndarApt = AndarApt;
    }
    
     @Override
    public String toString() {
        return getCpf(); //To change body of generated methods, choose Tools | Templates.
    }

    public Integer getID() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object save(Object t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object update(Object t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  

    @Override
    public Object remove(Object t,Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List FindAll(String nome) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
