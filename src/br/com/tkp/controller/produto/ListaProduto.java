package br.com.tkp.controller.produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.dao.DataBaseConnection;

public class ListaProduto {

	private Connection connection;

	public ListaProduto() {
		connection = DataBaseConnection.getInstance().getConnection();
	}

	public ResultSet listarProdutos() {
		PreparedStatement preparedStatement;

		try {
			String sql = "SELECT * FROM produto";
			preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			System.out.println("\n============== Produtos Cadastrados ==============");
			System.out.printf("| %2s | %13s | %6s | %4s | %9s |\n", "ID", "Produto", "Preço", "Qtd", "R$ total");
			System.out.println("==================================================");
			
			while(resultSet.next()) {
				System.out.printf("| %2s | %13s | %6s | %4s | %9s |\n", 
						resultSet.getInt("codigoDoProduto"), 
						resultSet.getString("nomeDoProduto"),
						resultSet.getDouble("precoDoProduto"), 
						resultSet.getInt("quantidadeDoProduto"),
						resultSet.getDouble("saldoEmEstoque"));
			}
			return resultSet;
		} catch (Exception e) {
			return null;
		}
	}
}
