package br.com.templatebase.core.dao;

import java.util.List;

import br.com.templatebase.core.domain.Motorista;

public interface IDaoMotorista {
	
	public Motorista inserir(Motorista novoMotorista);
	
	public Motorista alterar(Motorista motoristaSalvo);
	
	public Motorista remover(Motorista motoristaSalvo);
	
	public List<Motorista> listarPor(String nome);

}
