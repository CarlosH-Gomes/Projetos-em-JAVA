/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.model.Morador;
import java.util.List;

/**
 *
 * @author Carlos
 */
public interface MoradorDAO extends GenericDAO<Morador, Integer>{  
   public  List<Morador>  findByID(Integer id);
   public List<Morador> findByNome(String nome);
}
