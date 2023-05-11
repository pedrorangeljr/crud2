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
	
	/*Metodo que não deica gravar Telefone duplicado*/
	
	public boolean validarTelefone(String telefone) {
		
		try {
			
			String sql = "select count(1) as quantidade from  contato where telefone = '"+telefone+"'";
			PreparedStatement consultar = connection.prepareStatement(sql);
			ResultSet resultado = consultar.executeQuery();
			
			if(resultado.next()) {
				
				return resultado.getInt("quantidade") <= 0;
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return false;
	}
	
	/*metodo consultar usado para carregar os para edição*/
	
	public Contato consultar(String id) throws Exception {
		
		String sql = "select * from contato where id = '"+id+"'";
		PreparedStatement consultar = connection.prepareStatement(sql);
		ResultSet resultado = consultar.executeQuery();
		
		if(resultado.next()) {
			
			Contato contato = new Contato();
			
			contato.setId(resultado.getLong("id"));
			contato.setNome(resultado.getString("nome"));
			contato.setTelefone(resultado.getString("telefone"));
			contato.setEmail(resultado.getString("email"));
			
			return contato;
		}
		
		return null;
	}
	
	/*Metodo Atualizar*/
	
	public void atualizar(Contato contato) {
		
		try {
			
			String sql = "update contato set nome = ?, telefone = ?, email = ? where id = " + contato.getId();
			PreparedStatement update = connection.prepareStatement(sql);
			
			update.setString(1, contato.getNome());
			update.setString(2, contato.getTelefone());
			update.setString(3, contato.getEmail());
			update.executeUpdate();
			
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
