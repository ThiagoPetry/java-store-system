package br.com.tkp.controller.pessoa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.dao.DataBaseConnection;

public class PesquisaPessoa {
	
	private Connection connection;

	public PesquisaPessoa() {
		connection = DataBaseConnection.getInstance().getConnection();
	}

	public String pesquisaNomePessoa(int cliente) {
		PreparedStatement preparedStatement;
		String sql;

		try {
			sql = "SELECT usuario.nome FROM usuario WHERE codigo = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, cliente);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			
			return resultSet.getString("nome");
		} catch (Exception e) {
			System.out.println("\nNão foi possível encontrar o usuário.");
			return null;
		}
	}
}