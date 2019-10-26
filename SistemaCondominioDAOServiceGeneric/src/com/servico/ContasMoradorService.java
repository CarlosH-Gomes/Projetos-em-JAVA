/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servico;

import com.model.ContasMorador;
import java.util.List;

/**
 *
 * @author Carlos
 */
public interface ContasMoradorService {
    public void inserir(ContasMorador cm);
	public void update(ContasMorador cm);
	public void remove(ContasMorador cm,Integer id);
	public List<ContasMorador> findAll(String busca);
        public ContasMorador findByID(Integer id);
}
