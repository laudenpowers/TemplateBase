package br.com.templatebase.core.dao.spring.rowmapper;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import br.com.templatebase.core.domain.Pessoa;

public class PessoaRowMapper implements RowMapper<Pessoa>, Serializable{

	private static final long serialVersionUID = 1L;

	@Override
	public Pessoa mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Integer codigo = rs.getInt("id_pessoa");
		String nomeCompleto = rs.getString("nm_pessoa");
		String email = rs.getString("email");		

		return new Pessoa(codigo, nomeCompleto, email);

	}

}
