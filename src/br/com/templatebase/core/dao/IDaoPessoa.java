package br.com.templatebase.core.dao;

import java.util.List;

import br.com.templatebase.core.domain.Pessoa;

public interface IDaoPessoa {

	public Pessoa inserir(Pessoa novaPessoa);

	public Pessoa alterar(Pessoa pessoaSalva);

	public int removerPessoaPor(Integer codigo);

	public List<Pessoa> listarPor(String nome);

}