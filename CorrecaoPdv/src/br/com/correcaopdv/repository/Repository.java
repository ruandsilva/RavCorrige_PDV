package br.com.correcaopdv.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.correcaopdv.connection.Conexao;

public class Repository {

	private Connection con;
	
	private static String dataDe; 

	public Repository() throws SQLException {
		
		//this.con = Conexao.getConnection();
	}

	public Connection getCon() {
		return con;
	}

	public void setCon(Connection con) {
		this.con = con;
	}
	
	public boolean zerarBasesDeCalculo(String banco) throws SQLException {
	
		this.con = Conexao.getConnection(banco);
		
		String update = "update tbnfce ce set ce.base_calculo_icms = 0, ce.base_calculo_pis = 0, ce.base_calculo_cofins = 0, "
				+ "ce.valor_icms = 0,ce.valor_cofins = 0, ce.valor_pis = 0, ce.valor_total_iss = 0 where ce.ocorrencia in ('E','G')";
		
		PreparedStatement state = this.con.prepareStatement(update);
		
		//state.setString(1, data);
		
		boolean atualizado = state.executeUpdate() > 0;
		state.close();
		state.close();
		
		String update2 = "update tbnfce_itens ni set ni.base_calculo_icms = 0, ni.base_calculo_pis = 0, "
				+ "ni.base_calculo_cofins = 0, ni.base_iss = 0,ni.valor_pis = 0, ni.valor_icms = 0, "
				+ "ni.valor_cofins = 0, ni.valor_iss = 0 where cod_doc in(select doc from tbnfce where ocorrencia in ('E','G'))";
		
		PreparedStatement state2 = this.con.prepareStatement(update2);
		
		//state.setString(1, data);
		
		boolean atualizado2 = state2.executeUpdate() > 0;
		state.close();
		state.close();
		
		
		
		System.out.println("Zerou!");
		
		return atualizado2;	
	}
	
	public boolean zerarBasesDeCalculoIndividual(String banco, String nota) throws SQLException {
		
		this.con = Conexao.getConnection(banco);
		
		String update = "update tbnfce ce set ce.base_calculo_icms = 0, ce.base_calculo_pis = 0, ce.base_calculo_cofins = 0, "
				+ "ce.valor_icms = 0,ce.valor_cofins = 0, ce.valor_pis = 0, ce.valor_total_iss = 0 where ce.numero_nfc = " +nota +" ";
		
		PreparedStatement state = this.con.prepareStatement(update);
		
		//state.setString(1, data);
		
		boolean atualizado = state.executeUpdate() > 0;
		state.close();
		state.close();
		
		String update2 = "update tbnfce_itens ni set ni.base_calculo_icms = 0, ni.base_calculo_pis = 0, "
				+ "ni.base_calculo_cofins = 0, ni.base_iss = 0,ni.valor_pis = 0, ni.valor_icms = 0, "
				+ "ni.valor_cofins = 0, ni.valor_iss = 0 where cod_doc =" +nota +" ";
		
		PreparedStatement state2 = this.con.prepareStatement(update2);
		
		//state.setString(1, data);
		
		boolean atualizado2 = state2.executeUpdate() > 0;
		state.close();
		state.close();
		
		
		
		System.out.println("Zerou!");
		
		return atualizado2;	
	}
	
	public boolean ausenciaDeTrocoGeral(String banco) throws SQLException {
		
		this.con = Conexao.getConnection(banco);
		
		String update = "update tbnfce_pag set valor = (valor - 0.01) where cod_doc in (select doc from tbnfce ce"
				+ " where ce.ocorrencia in ('E','G'))";
		
		PreparedStatement state = this.con.prepareStatement(update);
		
		//state.setString(1, data);
		
		boolean atualizado = state.executeUpdate() > 0;
		state.close();
		state.close();
		
		System.out.println("Ausencia de troco geral");
		
		return atualizado;
	}
	
	public boolean ausenciaDeTrocoIndividual(String banco, String nota) throws SQLException {
		
		this.con = Conexao.getConnection(banco);
		
		String update = "update tbnfce_pag set valor = (valor - 0.01) where cod_doc =" +nota +" ";
		
		PreparedStatement state = this.con.prepareStatement(update);
		
		//state.setString(1, data);
		
		boolean atualizado = state.executeUpdate() > 0;
		state.close();
		state.close();
		
		System.out.println("Ausencia de troco específica");
		
		return atualizado;
	}
	
}
