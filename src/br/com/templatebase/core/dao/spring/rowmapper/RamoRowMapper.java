package br.com.templatebase.core.dao.spring.rowmapper;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import br.com.templatebase.core.domain.Ramo;

public class RamoRowMapper  implements RowMapper<Ramo>, Serializable{

	private static final long serialVersionUID = 1L;

	@Override

	public Ramo mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Integer codigo = rs.getInt("codigo");
		String nome = rs.getString("nome");


		return new Ramo(codigo, nome);

	}

}
