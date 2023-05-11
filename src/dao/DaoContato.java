package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import conexao.SingleConnection;
import model.Contato;

public class DaoContato {
	
	private Connection connection;
	
	public DaoContato() {
		
		connection = SingleConnection.getConnection();
	}

	
	/*Metodo Salvar*/
	
	public void salvarContato(Contato contato) {
		
		try {
			
			String sql = "insert into contato(nome,telefone,email)values(?,?,?)";
			PreparedStatement insert = connection.prepareStatement(sql);
			
			insert.setString(1, contato.getNome());
			insert.setString(2, contato.getTelefone());
			insert.setString(3, contato.getEmail());
			insert.execute();
			
			connection.commit();
			
		} catch (Exception e) {
			
			try {
				
				connection.rollback();
				
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
			
			e.printStackTrace();
		}
	}
}
