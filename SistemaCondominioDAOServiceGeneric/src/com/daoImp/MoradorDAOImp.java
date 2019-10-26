/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daoImp;

import com.connection.ConnectionFactory;
import com.model.Morador;
import com.dao.MoradorDAO;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.swing.JOptionPane;

/**
 *
 * @author Carlos
 */
public class MoradorDAOImp extends GenericDAOImp<Morador, Integer> implements MoradorDAO, Serializable{
    
        

    @Override
    public  List<Morador>  findByID(Integer id) {

        EntityManager em = new ConnectionFactory().getConnection();
        Morador morador = null;

        try {
            morador = em.find(Morador.class, id);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao Buscar" + e);
        } finally {
            em.close();
        }

        return (List<Morador>) morador;

    }
    
    @Override
        public List<Morador> findByNome(String nome) {

        EntityManager em = new ConnectionFactory().getConnection();
        Morador morador = null;

        try {
            morador = em.find(Morador.class, nome);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao Buscar" + e);
        } finally {
            em.close();
        }

        return (List<Morador>) morador;

    }


}
