/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.model.ContasMorador;

public interface ContasMoradorDAO extends GenericDAO<ContasMorador, Integer>{
    public ContasMorador findByID(Integer id);
}
