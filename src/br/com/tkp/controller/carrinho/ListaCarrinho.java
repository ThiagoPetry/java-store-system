package br.com.tkp.controller.carrinho;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.dao.DataBaseConnection;

public class ListaCarrinho {
	
	private Connection connection;

	public ListaCarrinho() {
		connection = DataBaseConnection.getInstance().getConnection();
	}
	
	public ResultSet carrinho(String cliente) {
		PreparedStatement preparedStatement;

		try {			
			String sql = "SELECT * FROM carrinho WHERE nomeDoUsuario = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, cliente);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if(!resultSet.next()) {
				System.out.print("\nNão possui dados no carrinho.\n");
				return null;
			}
			
			System.out.println("\n=============== Produtos no Carrinho ===============");
			System.out.printf("| %2s | %9s | %12s | %4s | %9s |\n", "ID", "ID Pro.", "Produto", "Qtd", "R$ total");
			System.out.println("====================================================");
			
			resultSet.previous();
			
			while(resultSet.next()) {
				System.out.printf("| %2s | %9s | %12s | %4s | %9s |\n",
						resultSet.getInt("codigo"), 
						resultSet.getInt("codigoDoProduto"), 
						resultSet.getString("nomeDoProduto"), 
						resultSet.getInt("quantidadeDoProduto"),
						resultSet.getDouble("valorTotal"));
			}
			return resultSet;
		} catch (Exception e) {
			return null;
		}
	}

	public void gerarCupom(String cliente) {
//		if (itensnDoCarrinho.size() <= 0) {
//			System.out.print("\nLista vazia.");
//			return;
//		}
		carrinho(cliente);
		System.out.println("Cliente " + cliente);
	}
}
