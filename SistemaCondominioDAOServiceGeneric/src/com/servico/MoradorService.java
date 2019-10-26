/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servico;

import com.model.Morador;
import java.util.List;

/**
 *
 * @author Carlos
 */
public interface MoradorService {
    public void inserir(Morador m);
    public void update(Morador m);
    public void remove(Morador m,Integer id);
    public List <Morador> findAll(String busca);
    public List<Morador> findByID(Integer Id);
    public List<Morador> findByNome(String nome);
}
