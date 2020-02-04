package br.com.templatebase.core.dao.spring.mysql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import br.com.templatebase.core.dao.IDaoPessoa;
import br.com.templatebase.core.dao.spring.rowmapper.PessoaRowMapper;
import br.com.templatebase.core.domain.Pessoa;

public class DaoPessoa implements IDaoPessoa {
	
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public Pessoa inserir(Pessoa novaPessoa) {
		
		StringBuilder sqlInsertPessoa = new StringBuilder();
		sqlInsertPessoa.append("INSERT INTO pessoa ");
		sqlInsertPessoa.append(" (nm_pessoa, email) ");
		sqlInsertPessoa.append("VALUES ");
		sqlInsertPessoa.append(" (:nm_pessoa, :email)");
		
		Map<String, Object> paramsInsert = new HashMap<String, Object>();
		paramsInsert.put("nm_pessoa", novaPessoa.getNomeCompleto());
		paramsInsert.put("email", novaPessoa.getEmail());
		
		new NamedParameterJdbcTemplate(dataSource).update(
				sqlInsertPessoa.toString(), paramsInsert);
		
		Integer ultimoCodigo = new JdbcTemplate(dataSource).queryForObject(
				"SELECT Max(id_pessoa) FROM pessoa", Integer.class);
		
		novaPessoa.setCodigo(ultimoCodigo);
		
		return novaPessoa;
		
	}

	@Override
	public Pessoa alterar(Pessoa pessoaSalva) {

		StringBuilder sqlUpdatePessoa = new StringBuilder();
		sqlUpdatePessoa.append("UPDATE pessoa SET ");
		sqlUpdatePessoa.append(" nm_pessoa = :nm_pessoa, email = :email ");
		sqlUpdatePessoa.append("WHERE id_pessoa = :id_pessoa ");

		Map<String, Object> paramsUpdate = new HashMap<String, Object>();
		paramsUpdate.put("nm_pessoa", pessoaSalva.getNomeCompleto());
		paramsUpdate.put("email", pessoaSalva.getEmail());
		paramsUpdate.put("id_pessoa", pessoaSalva.getCodigo());

		new NamedParameterJdbcTemplate(dataSource).update(
				sqlUpdatePessoa.toString(), paramsUpdate);

		return pessoaSalva;

	}

	@Override
	public int removerPessoaPor(Integer codigo) {
		return new JdbcTemplate(dataSource).update("DELETE FROM pessoa "
				+ "WHERE id_pessoa = ?", codigo);
	}

	@Override
	public List<Pessoa> listarPor(String nome) {
		
		StringBuilder sqlSelectTodas = new StringBuilder();
		sqlSelectTodas.append("SELECT ");
		sqlSelectTodas.append("  id_pessoa, nm_pessoa, email ");
		sqlSelectTodas.append("FROM ");
		sqlSelectTodas.append("  pessoa ");
		sqlSelectTodas.append("WHERE Upper(nm_pessoa) LIKE Upper(:nm_pessoa) ");
		sqlSelectTodas.append("ORDER BY nm_pessoa ");

		Map<String, Object> paramsSelect = new HashMap<String, Object>();
		paramsSelect.put("nm_pessoa", "%" + nome + "%");

		return new NamedParameterJdbcTemplate(dataSource).query(
				sqlSelectTodas.toString(), paramsSelect, new PessoaRowMapper());		

	}

}
