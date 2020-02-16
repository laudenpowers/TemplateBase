package br.com.templatebase.core.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

import br.com.templatebase.core.domain.CategoriaCarteira;
import br.com.templatebase.core.domain.Motorista;

public class MotoristaService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public Motorista salvar(Motorista motorista) {
		
		Preconditions.checkNotNull(motorista, "O motorista não pode ser nulo");
		
		Preconditions.checkArgument(!Strings.isNullOrEmpty(
				motorista.getMatricula()), "A matricula é obrigatória");
		
		Preconditions.checkNotNull(motorista.getCategoriaCarteira(), "A carteira é obrigatória");
		
		Preconditions.checkNotNull(CategoriaCarteira.valueOf("Z"), "A categoria é inválida");
		
		//Buscar por matricula (Motorista)
		
		return motorista;

	}
	
	public List<Motorista> listarPor(String nome){
		return new ArrayList<Motorista>();
	}
	
	public Motorista remover(Motorista motorista) {
		return motorista;
	}

}
