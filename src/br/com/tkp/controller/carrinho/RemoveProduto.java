package br.com.tkp.controller.carrinho;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import br.com.dao.DataBaseConnection;

public class RemoveProduto {
	
	private Scanner tec = new Scanner(System.in);
	private Connection connection;

	public RemoveProduto() {
		connection = DataBaseConnection.getInstance().getConnection();
	}
	
	public boolean verificaSeExisteProduto(int idDoProduto) {
		PreparedStatement preparedStatement;
		try {
			String sql = "SELECT * FROM carrinho WHERE codigoDoProduto = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, idDoProduto);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if(!resultSet.next()) {
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
	
	public double procuraPrecoDoProduto(String cliente, int id) {
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		double recebePreco;
		
		try {
			String sql = "SELECT * FROM produto WHERE codigoDoProduto = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			
			resultSet.next();
			recebePreco = resultSet.getDouble("precoDoProduto");			
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return recebePreco;
	}
	
	public int procuraQuantidadeDoProduto(String cliente, int id) {
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		int recebeQtd;
		
		try {
			String sql = "SELECT * FROM produto WHERE codigoDoProduto = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			
			resultSet.next();
			recebeQtd = resultSet.getInt("quantidadeDoProduto");			
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return recebeQtd;
	}
	
	public int procuraQuantidadeDoProdutoCar(String cliente, int id) {
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		int recebeQtd;
		
		try {
			String sql = "SELECT * FROM carrinho WHERE codigoDoProduto = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			
			resultSet.next();
			recebeQtd = resultSet.getInt("quantidadeDoProduto");			
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return recebeQtd;
	}
	
	public void removerProdutoCarrinho(String cliente) {
		PreparedStatement preparedStatementDel, preparedStatementAtu;
	  //PreparedStatement preparedStatementAtu, preparedStatementPro, preparedStatementCar;

		System.out.println("\n===== Remover produto do carrinho =====");
		System.out.print("\nInforme o ID do produto a ser removido: ");
		int idDoProduto = tec.nextInt();
		
		try {			
			if(!verificaSeExisteProduto(idDoProduto)) {
				return;
			}
		
			String sql_delete = "DELETE FROM carrinho WHERE codigoDoProduto = ? AND nomeDoUsuario = ?";
			preparedStatementDel = connection.prepareStatement(sql_delete);
			preparedStatementDel.setInt(1, idDoProduto);
			preparedStatementDel.setString(2, cliente);
			preparedStatementDel.execute();
			
//			procuraPrecoDoProduto(cliente, idDoProduto);
//			procuraQuantidadeDoProduto(cliente, idDoProduto);
//          ========================================================
			
			int qtd = procuraQuantidadeDoProduto(cliente, idDoProduto) + procuraQuantidadeDoProdutoCar(cliente, idDoProduto);
			
			String sql_atualiza = "UPDATE produto SET quantidadeDoProduto = ?, saldoEmEstoque = ? WHERE codigoDoProduto = ?";
			preparedStatementAtu = connection.prepareStatement(sql_atualiza);
			preparedStatementAtu.setInt(1, qtd);
			preparedStatementAtu.setDouble(2, (procuraPrecoDoProduto(cliente, idDoProduto) * qtd));
			preparedStatementAtu.setInt(3, idDoProduto);
			preparedStatementAtu.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Não foi possível excluir essa informação.");
			return;
		}
	}
}
