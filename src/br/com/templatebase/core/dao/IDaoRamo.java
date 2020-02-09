package br.com.templatebase.core.dao;

import java.util.List;

import br.com.templatebase.core.domain.Ramo;

public interface IDaoRamo {

	public Ramo inserir(Ramo novoRamo);

	public Ramo alterar(Ramo ramoSalva);

	public int removerRamoPor(Integer codigo);

	public List<Ramo> listarPor(String nome);
}
