package com.exemplo.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TAB_CLIENTE")
public class Cliente {
	
	
	private Long   id;
	private String nome;
	private String bairro;
	private String cidade;
	private String endereco;
	private List<Telefone> telefones;
	private String cep;
	private String numero;
	
	public Cliente() {
	}


	public Cliente(Long id, String nome, String bairro, String cidade, 
			       List<Telefone> telefones, String cep, String numero, String endereco) {
		this.id = id;
		this.nome = nome;
		this.bairro = bairro;
		this.cidade = cidade;
		this.telefones = telefones;
		this.endereco = endereco;
		this.cep = cep;
		this.numero = numero;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CLIENTE_ID")
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	@Column(name = "CLIENTE_NOME", nullable = false, length = 100 )
	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	@Column(name = "CLIENTE_BAIRRO", nullable = false, length = 50 )
	public String getBairro() {
		return bairro;
	}


	public void setBairro(String bairro) {
		this.bairro = bairro;
	}


	@Column(name = "CLIENTE_CIDADE", nullable = false, length = 100 )
	public String getCidade() {
		return cidade;
	}


	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	
	
	/*
	 * @OneToOne(mappedBy = "cliente",targetEntity = Telefone.class, cascade =
	 * CascadeType.ALL,fetch = FetchType.LAZY)
	 */
	
	
	/*
	 * @ManyToMany(fetch = FetchType.LAZY)
	 * 
	 * @JoinTable(name = "TAB_CLIENTE_TELEFONE", joinColumns = @JoinColumn(name =
	 * "CLIENTE_ID"), inverseJoinColumns = @JoinColumn(name="TELEFONE_ID"))
	 */

	
	@OneToMany(mappedBy = "cliente",targetEntity = Telefone.class, cascade =
	CascadeType.ALL,fetch = FetchType.LAZY)
	public List<Telefone> getTelefones() {
		return telefones;
	}


	public void setTelefones(List<Telefone>telefones) {
		this.telefones = telefones;
	}
	
	@Column(name = "CLIENTE_CEP", nullable = false, length = 10 )
	public String getCep() {
		return cep;
	}


	public void setCep(String cep) {
		this.cep = cep;
	}

	
	@Column(name = "CLIENTE_NUMERO", nullable = false, length = 5 )
	public String getNumero() {
		return numero;
	}


	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	@Column(name = "CLIENTE_ENDERECO", nullable = false, length = 100 )
	public String getEndereco() {
		return endereco;
	}


	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nome=" + nome + ", bairro=" + bairro + ", cidade=" + cidade + ", cep=" + cep + ", numero=" + numero + "]";
	}
	
	
	
	
	
	
	
	
	

}
