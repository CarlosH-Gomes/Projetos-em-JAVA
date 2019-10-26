/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servicoimp;

import com.dao.MoradorDAO;
import com.daoImp.MoradorDAOImp;
import com.model.Morador;
import com.servico.MoradorService;
import java.util.List;

/**
 *
 * @author Carlos
 */
public class MoradorServiceImp implements MoradorService{

    MoradorDAO dao =  new MoradorDAOImp();
    
    @Override
    public void inserir(Morador m) {
        dao.save(m);
    }

    @Override
    public void update(Morador m) {
        dao.update(m);
    }

    @Override
    public void remove(Morador m,Integer id) {
        dao.remove( m,id);
    }

    @Override
    public List<Morador> findAll(String busca) {
        
        return dao.FindAll(busca);
    }

    @Override
    public List<Morador> findByID(Integer Id) {
         
        return dao.findByID(Id);
    }

    @Override
    public List<Morador> findByNome(String nome) {
       
        return  dao.findByNome(nome);
    }
    
}
