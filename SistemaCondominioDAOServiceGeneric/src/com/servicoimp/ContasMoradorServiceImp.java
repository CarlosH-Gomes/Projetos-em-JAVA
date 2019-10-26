/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servicoimp;

import com.dao.ContasMoradorDAO;
import com.daoImp.ContasMoradorDAOImp;
import com.model.ContasMorador;
import com.servico.ContasMoradorService;
import java.util.List;

/**
 *
 * @author Carlos
 */
public class ContasMoradorServiceImp implements ContasMoradorService{

    ContasMoradorDAO dao =  new ContasMoradorDAOImp();
    
    @Override
    public void inserir(ContasMorador cm) {
        dao.save(cm);
    }

    @Override
    public void update(ContasMorador cm) {
        dao.update(cm);
    }

    @Override
    public void remove(ContasMorador cm,Integer id) {
        dao.remove(cm,id);
    }

 

    @Override
    public ContasMorador findByID(Integer id) {
        
        return  (ContasMorador) dao.findByID(id);
    }

    @Override
    public List<ContasMorador> findAll(String busca) {
        return dao.FindAll(busca);
    }

   
}
