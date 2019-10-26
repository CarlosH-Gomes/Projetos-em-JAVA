/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import com.dao.GenericDAO;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author Carlos
 */
@Entity
public class ContasMorador implements GenericDAO, Serializable {
    
    @Id //chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY)//Auto -> escolhe o melhor / identity -> gara sozinho /  SEQUENCE -> quando bd não possui suporte pra auto inclemento
    private Integer codigo;
    private String TipoConta;
    private String valor;
    private String dataVenc;
    @ManyToOne
    private Morador moradorCpf;

    public Integer getCodigo() {
        return codigo;
    }
    
    public Morador getMoradorCpf() {
        return moradorCpf;
    }

    public String getTipoConta() {
        return TipoConta;
    }

    public String getValor() {
        return valor;
    }

    public String getDataVenc() {
        return dataVenc;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

      
    public void setMoradorCpf(Morador moradorCpf) {
        this.moradorCpf = moradorCpf;
    }

    public void setTipoConta(String TipoConta) {
        this.TipoConta = TipoConta;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public void setDataVenc(String dataVenc) {
        this.dataVenc = dataVenc;
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
    public List FindAll(String busca) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  
    
    
}
