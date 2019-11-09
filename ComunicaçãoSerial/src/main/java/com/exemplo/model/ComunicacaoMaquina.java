package com.exemplo.model;

import java.util.Date;
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
@Table(name = "TAB_MAQUINA")
public class ComunicacaoMaquina {

	private Long id;
	private String estado;
	//private String data;
	
	public ComunicacaoMaquina() {
		
	}
	
	public ComunicacaoMaquina(Long id,String estado, String data) {
		this.id = id;
		this.estado = estado;
		//this.data = data;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MAQUINA_ID")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "MAQUINA_ESTADO", nullable = false, length = 100 )
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}


	
}
