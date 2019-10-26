/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daoImp;

import com.connection.ConnectionFactory;
import java.util.List;
import javax.persistence.EntityManager;
import javax.swing.JOptionPane;
import com.dao.GenericDAO;
import java.io.Serializable;

/**
 *
 * @author Carlos
 * @param <T>
 * @param <ID>
 */
public class GenericDAOImp <T, ID extends Serializable> implements GenericDAO<T, ID> {
    
    public T save(T t) {
        EntityManager em = new ConnectionFactory().getConnection();

        try {
            em.getTransaction().begin();
            em.persist(t);
            em.getTransaction().commit();
            JOptionPane.showMessageDialog(null, "Salvo com Sucesso");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao Salvar" + e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }

        return t;
    }

    @Override
    public T update(T t) {
        EntityManager em = new ConnectionFactory().getConnection();

        try {
            em.getTransaction().begin();
            em.merge(t);
            em.getTransaction().commit();
            JOptionPane.showMessageDialog(null, "Atualizado com Sucesso");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao Atualizar" + e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }

        return t;
    }
        
    @Override
    public List<T> FindAll(String busca) {
        EntityManager em = new ConnectionFactory().getConnection();
        List<T> t = null;

        try {
            t = em.createQuery("from " + busca + " o").getResultList();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao Atualizar " + e);
        } finally {
            em.close();
        }

        return t;
    }

    @Override
    public T remove(T t, Integer id) {
        EntityManager em = new ConnectionFactory().getConnection();

        try {
            t = em.find((Class<T>) t.getClass(), id);
            em.getTransaction().begin();
            em.remove(t);
            em.getTransaction().commit();
            JOptionPane.showMessageDialog(null, "Removido com Sucesso");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao remover" + e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }

        return t;
    }
}
