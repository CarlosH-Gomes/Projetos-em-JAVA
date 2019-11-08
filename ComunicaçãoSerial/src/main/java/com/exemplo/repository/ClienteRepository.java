package com.exemplo.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;


import com.exemplo.model.Cliente;

public class ClienteRepository extends GenericRepository<Cliente, Long>{

	public ClienteRepository(EntityManager entityManager) {
		super(entityManager);
	}

	
	public List<Cliente> listarTodosRegistrosComPaginacao(Integer primeiro, Integer tamanhoPagina){
		List<Cliente> listaCliente = new ArrayList<Cliente>();
		try {
			TypedQuery<Cliente> query = this.getEntityManager()
			                                .createQuery("SELECT c FROM Cliente c", Cliente.class)
											.setFirstResult(primeiro)
											.setMaxResults(tamanhoPagina);
		listaCliente = query.getResultList();
		} catch( Exception e) {
			e.printStackTrace();
		}

		return listaCliente;
	}
	
}
