package br.com.templatebase.core.dao.spring;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import br.com.templatebase.core.dao.IDaoPessoa;

public class FactorySpringDao implements Serializable{

	private static final long serialVersionUID = 1L;

	private static FactorySpringDao obj;
	
	private ApplicationContext context;
	
	private FactorySpringDao(){
		context = new ClassPathXmlApplicationContext("beans_mysql.xml");
	}
	
	public IDaoPessoa getDaoPessoa(){
		return (IDaoPessoa)context.getBean("daoPessoa");
	}	
	
	public Connection getConexaoDoBD(){
		try {
			DataSource dataSource = (DataSource)context.getBean("dataSource");
			return dataSource.getConnection();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			return null;
		}
	}
	
	public void encerrar(Connection conexaoDoBD){
		try {
			conexaoDoBD.close();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}
	
	public static FactorySpringDao getInstance(){
		
		if (obj == null){
			
			synchronized (FactorySpringDao.class) {
				
				if (obj == null){
					obj = new FactorySpringDao();
				}
				
			}
			
		}
		
		return obj;
	}
	
}	
