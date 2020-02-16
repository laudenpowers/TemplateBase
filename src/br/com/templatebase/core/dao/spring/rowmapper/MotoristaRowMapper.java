package br.com.templatebase.core.dao.spring.rowmapper;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import br.com.templatebase.core.domain.CategoriaCarteira;
import br.com.templatebase.core.domain.Motorista;

public class MotoristaRowMapper implements RowMapper<Motorista>,Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public Motorista mapRow(ResultSet rs, int row) throws SQLException {
		
		String matricula = rs.getString("matricula");
		String nome = rs.getString("nome");
	 
		CategoriaCarteira categoriaCarteira = CategoriaCarteira.valueOf(rs.getString("categoriacarteira"));
	
		return new Motorista(matricula,nome, categoriaCarteira);
	}
	

}
