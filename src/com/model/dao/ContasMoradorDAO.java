/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model.dao;

import com.connection.ConnectionFactory;
import com.model.bean.ContasMorador;
import java.util.List;
import javax.persistence.EntityManager;
import javax.swing.JOptionPane;

/**
 *
 * @author Carlos
 */
public class ContasMoradorDAO {
    public ContasMorador save(ContasMorador cm) {
        EntityManager em = new ConnectionFactory().getConnection();

        try {
            em.getTransaction().begin();
            em.persist(cm);
            em.getTransaction().commit();
            JOptionPane.showMessageDialog(null, "Salvo com Sucesso");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao Salvar" + e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }

        return cm;
    }

    public ContasMorador update(ContasMorador cm) {
        EntityManager em = new ConnectionFactory().getConnection();

        try {
            em.getTransaction().begin();
            em.merge(cm);
            em.getTransaction().commit();
            JOptionPane.showMessageDialog(null, "Atualizado com Sucesso");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao Atualizar" + e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }

        return cm;
    }

    public ContasMorador findByID(Integer id) {

        EntityManager em = new ConnectionFactory().getConnection();
        ContasMorador cm = null;

        try {
            cm = em.find(ContasMorador.class, id);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao Buscar" + e);
        } finally {
            em.close();
        }

        return cm;

    }

    public List<ContasMorador> FindAll() {
        EntityManager em = new ConnectionFactory().getConnection();
        List<ContasMorador> cm = null;

        try {
            cm = em.createQuery("from ContasMorador cm").getResultList();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao Atualizar" + e);
        } finally {
            em.close();
        }

        return cm;
    }

    public ContasMorador remove(Integer id) {
        EntityManager em = new ConnectionFactory().getConnection();
        ContasMorador cm = null;
        try {
            cm = em.find(ContasMorador.class, id);
            em.getTransaction().begin();
            em.remove(cm);
            em.getTransaction().commit();
            JOptionPane.showMessageDialog(null, "Removido com Sucesso");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao remover" + e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }

        return cm;
    }

}


