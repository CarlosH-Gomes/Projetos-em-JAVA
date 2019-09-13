/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model.dao;

import com.connection.ConnectionFactory;
import com.model.bean.Morador;
import java.util.List;
import javax.persistence.EntityManager;
import javax.swing.JOptionPane;

/**
 *
 * @author Carlos
 */
public class MoradorDAO {
    
        public Morador save(Morador morador) {
        EntityManager em = new ConnectionFactory().getConnection();

        try {
            em.getTransaction().begin();
            em.persist(morador);
            em.getTransaction().commit();
            JOptionPane.showMessageDialog(null, "Salvo com Sucesso");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao Salvar" + e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }

        return morador;
    }

    public Morador update(Morador morador) {
        EntityManager em = new ConnectionFactory().getConnection();

        try {
            em.getTransaction().begin();
            em.merge(morador);
            em.getTransaction().commit();
            JOptionPane.showMessageDialog(null, "Atualizado com Sucesso");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao Atualizar" + e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }

        return morador;
    }

    public Morador findByID(Integer id) {

        EntityManager em = new ConnectionFactory().getConnection();
        Morador morador = null;

        try {
            morador = em.find(Morador.class, id);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao Buscar" + e);
        } finally {
            em.close();
        }

        return morador;

    }

    public List<Morador> FindAll() {
        EntityManager em = new ConnectionFactory().getConnection();
        List<Morador> morador = null;

        try {
            morador = em.createQuery("from Morador m").getResultList();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao Atualizar" + e);
        } finally {
            em.close();
        }

        return morador;
    }

    public Morador remove(Integer id) {
        EntityManager em = new ConnectionFactory().getConnection();
        Morador morador = null;
        try {
            morador = em.find(Morador.class, id);
            em.getTransaction().begin();
            em.remove(morador);
            em.getTransaction().commit();
            JOptionPane.showMessageDialog(null, "Removido com Sucesso");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao remover" + e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }

        return morador;
    }

}
