package br.com.templatebase.core.domain;

import java.io.Serializable;

public class Motorista implements Serializable{

	private static final long serialVersionUID = 1L;
	private String matricula;
	private String nome;
	private CategoriaCarteira categoriaCarteira;
	
	
	public Motorista(String matricula, String nome, CategoriaCarteira categoriaCarteira) {
		
		this.matricula = matricula;
		this.nome = nome;
		this.categoriaCarteira = categoriaCarteira;
	}
	
	public Motorista() {
		
	}

	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public CategoriaCarteira getCategoriaCarteira() {
		return categoriaCarteira;
	}
	public void setCategoriaCarteira(CategoriaCarteira categoriaCarteira) {
		this.categoriaCarteira = categoriaCarteira;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((matricula == null) ? 0 : matricula.hashCode());
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
		Motorista other = (Motorista) obj;
		if (matricula == null) {
			if (other.matricula != null)
				return false;
		} else if (!matricula.equals(other.matricula))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Motorista [matricula=" + matricula + ", nome=" + nome
				+ ", categoriaCarteira=" + categoriaCarteira + "]";
	}
	
}
