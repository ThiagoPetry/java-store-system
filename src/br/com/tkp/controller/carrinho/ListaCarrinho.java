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
	
	public ResultSet carrinho(int cliente) {
		PreparedStatement preparedStatement;
		double valorTotal = 0;
		
		try {			
			String sql = "SELECT carrinho.codigo, carrinho.codigoDoProduto, produto.nomeDoProduto, "
					+ "carrinho.quantidadeDoProduto, (carrinho.quantidadeDoProduto * produto.precoDoProduto) as Total "
					+ "FROM carrinho INNER JOIN produto ON carrinho.codigoDoProduto = produto.codigoDoProduto "
					+ "WHERE carrinho.codigoDoUsuario = ?";
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, cliente);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if(!resultSet.next()) {
				System.out.print("\nVocê não possui nenhum produto no carrinho.\n");
				return null;
			}
			
			System.out.println("\n=============== Produtos no Carrinho ===============");
			System.out.printf("| %2s | %9s | %12s | %4s | %9s |\n", "ID", "ID Pro.", "Produto", "Qtd", "R$ total");
			System.out.println("====================================================");
			
			resultSet.previous();		
			while(resultSet.next()) {
				valorTotal += resultSet.getDouble("total");
				System.out.printf("| %2s | %9s | %12s | %4s | %9s |\n",
						resultSet.getInt("codigo"), 
						resultSet.getInt("codigoDoProduto"), 
						resultSet.getString("nomeDoProduto"), 
						resultSet.getInt("quantidadeDoProduto"),
						resultSet.getDouble("total"));
			}
			System.out.println("====================================================");
			System.out.println("VALOR TOTAL: " + valorTotal);
			return resultSet;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}