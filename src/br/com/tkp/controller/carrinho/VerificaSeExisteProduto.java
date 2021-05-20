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

	public boolean procurarPeloProduto(int idDoProduto) {
		PreparedStatement preparedStatement;
		try {
			String sql = "SELECT * FROM carrinho WHERE codigoDoProduto = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, idDoProduto);

			ResultSet resultSet = preparedStatement.executeQuery();

			if (!resultSet.next()) {
				System.out.println("Este produto não existe no carrinho.");
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
