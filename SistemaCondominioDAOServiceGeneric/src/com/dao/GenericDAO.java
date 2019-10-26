/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Carlos
 * @param <T>
 * @param <ID>
 */
public interface GenericDAO <T, ID extends Serializable>{

    public T save(T t);
    public T update(T t);
    public List<T> FindAll(String busca);
    public T remove(T t, Integer id);
}
