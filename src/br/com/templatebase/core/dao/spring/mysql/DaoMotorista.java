package br.com.templatebase.core.dao.spring.mysql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import br.com.templatebase.core.dao.IDaoMotorista;
import br.com.templatebase.core.dao.spring.FactorySpringDao;
import br.com.templatebase.core.dao.spring.rowmapper.MotoristaRowMapper;
import br.com.templatebase.core.domain.Motorista;

public class DaoMotorista implements IDaoMotorista {
	
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public static void main(String[] args) {
		IDaoMotorista daoMotorista = FactorySpringDao.getInstance().getDaoMotorista();
		//daoMotorista.inserir(new Motorista("666", "Laudelino", CategoriaCarteira.B));
		//System.out.println("Salvou");
		List<Motorista> motoristas = daoMotorista.listarPor("");
		
		System.out.println(motoristas.get(0).getCategoriaCarteira());
	}
	
	@Override
	public Motorista inserir(Motorista novoMotorista) {
		
		StringBuilder sqlInsert = new StringBuilder();
		sqlInsert.append("INSERT INTO motorista ");
		sqlInsert.append("  (matricula, nome, categoriacarteira) ");
		sqlInsert.append("VALUES ");
		sqlInsert.append("  (:matricula, :nome, :categoriacarteira) ");
		
		Map<String, Object> paramsInsert = new HashMap<String, Object>();
		paramsInsert.put("matricula", novoMotorista.getMatricula());
		paramsInsert.put("nome", novoMotorista.getNome());
		paramsInsert.put("categoriacarteira", novoMotorista.getCategoriaCarteira().name());
		
		new NamedParameterJdbcTemplate(dataSource).update(sqlInsert.toString(), paramsInsert);
		
		return novoMotorista;
		
	}

	@Override
	public Motorista alterar(Motorista motoristaSalvo) {
		return null;
	}

	@Override
	public Motorista remover(Motorista motoristaSalvo) {
		return null;
	}

	@Override
	public List<Motorista> listarPor(String nome) {
		StringBuilder sqlListar = new StringBuilder();
		sqlListar.append("SELECT matricula,nome,categoriacarteira ");
		sqlListar.append("FROM motorista  ");
		sqlListar.append("WHERE UPPER(nome) LIKE UPPER(:nome) ");	
		
		Map <String,Object> paramsListar = new HashMap<String, Object>();
		paramsListar.put("nome", "%" + nome + "%");
		
		return new NamedParameterJdbcTemplate(dataSource).query(
				sqlListar.toString(),paramsListar, new MotoristaRowMapper());
		
	}

}
