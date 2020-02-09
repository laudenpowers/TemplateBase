package br.com.templatebase.core.domain;

import java.io.Serializable;

public class Ramo implements Serializable{

	private Integer codigo;
	private String nome;
	private static final long serialVersionUID = 1L;
	
	public Ramo(Integer codigo, String nome) {
		this(nome);
		this.codigo = codigo;		
	}
	
	public Ramo(String nome) {		
		this.setNome(nome);
	}
	
	public Ramo() {
		this.nome = "";
		this.codigo=0;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		if (nome != null) {
			this.nome = nome.toUpperCase();
		}else{		
			this.nome = nome;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
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
		Ramo other = (Ramo) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}



	
	
}
