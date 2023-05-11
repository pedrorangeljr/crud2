package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
	
	/*Metodo Listar Contatos*/
	
	public List<Contato> listar() throws Exception{
		
		List<Contato> listar = new ArrayList<Contato>();
		
		String sql = "select * from contato order by id asc";
		PreparedStatement select = connection.prepareStatement(sql);
		ResultSet resultado = select.executeQuery();
		
		while(resultado.next()) {
			
			Contato contato = new Contato();
			
			contato.setId(resultado.getLong("id"));
			contato.setNome(resultado.getString("nome"));
			contato.setTelefone(resultado.getString("telefone"));
			contato.setEmail(resultado.getString("email"));
			
			listar.add(contato);
		}
		
		return listar;
	}
	
	/*Metodo deletar*/
	
	public void deletar(String id) {
		
		try {
			
			String sql = "delete from contato where id = '"+id+"'";
			PreparedStatement delete = connection.prepareStatement(sql);
			delete.execute();
			
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
