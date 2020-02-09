package br.com.templatebase.core.dao.spring.mysql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import br.com.templatebase.core.dao.IDaoRamo;
import br.com.templatebase.core.dao.spring.rowmapper.RamoRowMapper;
import br.com.templatebase.core.domain.Ramo;

public class DaoRamo implements IDaoRamo{

	private DataSource dataSource;
	
	
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public Ramo inserir(Ramo novoRamo) {
		StringBuilder sqlInsertRamo = new StringBuilder();
		sqlInsertRamo.append("INSERT INTO ramos ");
		sqlInsertRamo.append(" (nome) ");
		sqlInsertRamo.append("VALUES ");
		sqlInsertRamo.append(" (:nome)");
		
		Map<String, Object> paramsInsert = new HashMap<String, Object>();
		paramsInsert.put("nome", novoRamo.getNome());
		
		new NamedParameterJdbcTemplate(dataSource).update(
				sqlInsertRamo.toString(), paramsInsert);

		novoRamo.setCodigo(0);
		
		return novoRamo;
	}

	@Override
	public Ramo alterar(Ramo ramoSalvo) {
		StringBuilder sqlUpdateRamo = new StringBuilder();
		sqlUpdateRamo.append("UPDATE ramos SET ");
		sqlUpdateRamo.append(" nome = :nome ");
		sqlUpdateRamo.append("WHERE codigo = :codigo ");

		Map<String, Object> paramsUpdate = new HashMap<String, Object>();
		paramsUpdate.put("nome", ramoSalvo.getNome());
		paramsUpdate.put("codigo", ramoSalvo.getCodigo());

		new NamedParameterJdbcTemplate(dataSource).update(
				sqlUpdateRamo.toString(), paramsUpdate);

		return ramoSalvo;
	}

	@Override
	public int removerRamoPor(Integer codigo) {
		return new JdbcTemplate(dataSource).update("DELETE FROM ramos "
				+ "WHERE codigo = ?", codigo);

	}

	@Override
	public List<Ramo> listarPor(String nome) {
		StringBuilder sqlSelectTodas = new StringBuilder();
		sqlSelectTodas.append("SELECT ");
		sqlSelectTodas.append("  codigo, nome ");
		sqlSelectTodas.append("FROM ");
		sqlSelectTodas.append("  ramos ");
		sqlSelectTodas.append("WHERE Upper(nome) LIKE Upper(:nome) ");
		sqlSelectTodas.append("ORDER BY nome ");

		Map<String, Object> paramsSelect = new HashMap<String, Object>();
		paramsSelect.put("nome", "%" + nome + "%");

		return new NamedParameterJdbcTemplate(dataSource).query(
				sqlSelectTodas.toString(), paramsSelect, new RamoRowMapper());	
		
	}
}
