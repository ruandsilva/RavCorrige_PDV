package br.com.correcaopdv.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	
	public static Connection getConnection(String senha) throws SQLException{
		
		Connection con = null;
		String driver = "org.firebirdsql.jdbc.FBDriver";
		String url = "jdbc:firebirdsql:localhost/3050:" +senha;
		String user ="SYSDBA";
		String password = "masterkey";
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			System.out.println("Conectão bem sucedida!!");
			return con;
			
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
			return null;
		} 
	}
	
	public static void close(Connection con) {
		try {
			con.close();
		} catch (Exception e) {
			e.getMessage();
		}
	}

}
