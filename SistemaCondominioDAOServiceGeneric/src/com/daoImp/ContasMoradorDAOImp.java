/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daoImp;

import com.connection.ConnectionFactory;
import com.dao.ContasMoradorDAO;
import com.model.ContasMorador;
import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.swing.JOptionPane;

/**
 *
 * @author Carlos
 */
public class ContasMoradorDAOImp extends GenericDAOImp<ContasMorador, Integer> implements ContasMoradorDAO, Serializable{

    @Override
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
        return  cm;
        }
}


