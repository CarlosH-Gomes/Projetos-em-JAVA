package com.exemplo.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class TabelaClienteModel extends AbstractTableModel{

	private static final long serialVersionUID = 6893553424676264421L;

	private final String colunas[] = {"Id","Nome","Bairro",
									   "Cidade","Endereço",
									   "Cep","Número"};
	
	private List<Cliente> listaClientes;
	
	private static final int ID       = 0;
	private static final int NOME     = 1;
	private static final int BAIRRO   = 2;
	private static final int CIDADE   = 3;
	private static final int ENDERECO = 4;
	private static final int CEP      = 5;
	private static final int NUMERO   = 6;
	
	public TabelaClienteModel() {
           listaClientes = new ArrayList<Cliente>(); 
	}

	
	public Cliente getCliente(int linhaIndex) {
		return this.getListaClientes().get(linhaIndex);
	}
	
	public void addCliente(Cliente cliente) {
		this.getListaClientes().add(cliente);
		fireTableRowsInserted(getRowCount()-1, getRowCount()-1 );
	}
	
	public void updateCliente(Cliente cliente, int linhaIndex) {
		this.getListaClientes().set(linhaIndex, cliente);
		fireTableRowsUpdated(linhaIndex, linhaIndex);
	}
	
	public void removeCliente(int linhaIndex) {
		this.getListaClientes().remove(linhaIndex);
		fireTableRowsDeleted(linhaIndex, linhaIndex);
	}
	
	public void removeTodosClientes() {
		this.getListaClientes().clear();
	}
		
	@Override
	public String getColumnName(int nomeColuna) {
		return this.colunas[nomeColuna];
	}
	
	
	@Override
	public int getColumnCount() {
		return this.getColunas().length;
	}

	@Override
	public int getRowCount() {
		return this.getListaClientes().size();
	}

	@Override
	public Object getValueAt(int linhaIndex, int colunaIndex) {
		
		Cliente cliente = this.getListaClientes().get(linhaIndex);
		
		switch(colunaIndex) {
		case ID:
			return cliente.getId();
		case NOME:
			return cliente.getNome();
		case BAIRRO:
			return cliente.getBairro();
		case CIDADE:
			return cliente.getCidade();
		case ENDERECO:
			return cliente.getEndereco();
		case CEP:
			return cliente.getCep();
		case NUMERO:
			return cliente.getNumero();
		default:
			return cliente;
		
		}
	}
	
	@Override
	public Class<?> getColumnClass(int colunaIndex){
		switch(colunaIndex) {
		case ID:
			return Long.class;
		case NOME:
			return String.class;
		case BAIRRO:
			return String.class;
		case CIDADE:
			return String.class;
		case ENDERECO:
			return String.class;
		case CEP:
			return String.class;
		case NUMERO:
			return String.class;
		}
		return null;
	}
	

	public String[] getColunas() {
		return colunas;
	}

	public List<Cliente> getListaClientes() {
		return listaClientes;
	}
	
	
	public void setListaClientes(List<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}


	public static int getId() {
		return ID;
	}

	public static int getNome() {
		return NOME;
	}

	public static int getBairro() {
		return BAIRRO;
	}

	public static int getCidade() {
		return CIDADE;
	}

	public static int getEndereco() {
		return ENDERECO;
	}


	public static int getCep() {
		return CEP;
	}

	public static int getNumero() {
		return NUMERO;
	}

	
}
