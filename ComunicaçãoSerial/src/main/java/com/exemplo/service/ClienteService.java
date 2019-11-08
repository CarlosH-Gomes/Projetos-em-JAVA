package com.exemplo.service;


import java.util.List;

import javax.persistence.EntityTransaction;


import com.exemplo.model.Cliente;
import com.exemplo.repository.ClienteRepository;

public class ClienteService extends ConexaoBancoService {
	
	private ClienteRepository clienteRepository;
	
	private static Integer ERRO_INCLUSAO = 1;
	private static Integer ERRO_ALTERACAO = 2;
	private static Integer ERRO_EXCLUSAO = 3;
	private static Integer SUCESSO_TRANSACAO = 0;
	
	public ClienteService() {
		clienteRepository = new ClienteRepository(getEntityManager());
	}
	
		
	public Integer salvarCliente(Cliente cliente) {
		EntityTransaction transacao = this.getEntityManager().getTransaction();
		try {
			transacao.begin();
			clienteRepository.save(cliente);
			transacao.commit();
		}catch (Throwable e) {
			e.printStackTrace();
	        if (transacao.isActive()) {
	        	transacao.rollback();
	        	close();
	        }
			return ERRO_INCLUSAO;
		} finally {
			close();
		}
		return SUCESSO_TRANSACAO;
	}
	

	public Integer alterarCLiente(Cliente cliente) {
		EntityTransaction transacao = this.getEntityManager().getTransaction();
		try {
			transacao.begin();
			clienteRepository.update(cliente);
			transacao.commit();
		} catch(Throwable e) {
			e.printStackTrace();
			if (transacao.isActive()) {
				transacao.rollback();
				close();
			}
			return ERRO_ALTERACAO;
		} finally {
			close();
		}
		return SUCESSO_TRANSACAO;
	}
	
	public Integer excluirCliente(Cliente cliente) {
		EntityTransaction transacao = this.getEntityManager().getTransaction();
		try {
			Cliente clienteRemovido = consultarCliente(cliente.getId());
			transacao.begin();
			clienteRepository.remove(clienteRemovido);
			transacao.commit();
		} catch(Throwable e) {
			e.printStackTrace();
			if (transacao.isActive()) {
				transacao.rollback();
				close();
			}
			return ERRO_EXCLUSAO;
		} finally {
			close();
		}
		return SUCESSO_TRANSACAO;
	}
	
	
	public Cliente consultarCliente(Long id) {
		return clienteRepository.findById(id);
	}
	
	public List<Cliente> listarTodosClientes(){
		return clienteRepository.findAll(Cliente.class);
	}

	public Integer calcularTotalRegistros() {
		return clienteRepository.calcularTotalRegistros(Cliente.class);
	}
	
	public List<Cliente> listarTodosRegistrosComPaginacao(Integer primeiro, Integer tamanhoPagina){
	    return clienteRepository.listarTodosRegistrosComPaginacao(primeiro, tamanhoPagina);
	}
	
}
