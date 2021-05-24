package br.com.tkp.controller.carrinho;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.dao.DataBaseConnection;

public class VerificaSeExisteProduto {

	private Connection connection;

	public VerificaSeExisteProduto() {
		connection = DataBaseConnection.getInstance().getConnection();
	}

	public boolean procurarPeloProduto(int idDoProduto, int cliente) {
		PreparedStatement preparedStatement;
		try {
			String sql = "SELECT * FROM carrinho WHERE codigoDoProduto = ? AND codigoDoUsuario = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, idDoProduto);
			preparedStatement.setInt(2, cliente);

			ResultSet resultSet = preparedStatement.executeQuery();

			if (!resultSet.next()) {
				//System.out.println("Este produto não existe no carrinho.");
				return false;
			} else {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}