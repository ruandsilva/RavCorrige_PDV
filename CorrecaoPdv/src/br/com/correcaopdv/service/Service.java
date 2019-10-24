package br.com.correcaopdv.service;

import java.sql.SQLException;

import br.com.correcaopdv.repository.Repository;

public class Service {
	
	public Repository repository ;
	
	public Service() throws SQLException {
		repository = new Repository();
	}
	
	public void zerarBasesDeCalculo(String banco) throws SQLException {
		this.repository.zerarBasesDeCalculo(banco);
	}
	
	public void ausenciaDeTrocoGeral(String banco) throws SQLException {
		this.repository.ausenciaDeTrocoGeral(banco);
	}
	
	public void ausenciaDeTrocoIndividual(String banco, String nota) throws SQLException {
		this.repository.ausenciaDeTrocoIndividual(banco, nota);
	}
	
	public void zerarBasesDeCalculoIndividual(String banco, String nota) throws SQLException {
		this.repository.zerarBasesDeCalculoIndividual(banco, nota);
	}

}
