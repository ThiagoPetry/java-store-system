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
	
	public void removerProdutoCarrinho() {
		PreparedStatement preparedStatementDel;
		PreparedStatement preparedStatementAtu, preparedStatementPro, preparedStatementCar;

		System.out.println("\n===== Remover produto do carrinho =====");
		System.out.print("\nInforme o ID do produto a ser removido: ");
		int idDoProduto = tec.nextInt();
		
		try {			
			if(!verificaSeExisteProduto(idDoProduto)) {
				return;
			}
		
			String sql_delete = "DELETE FROM carrinho WHERE codigoDoProduto = ?";
			preparedStatementDel = connection.prepareStatement(sql_delete);
			preparedStatementDel.setInt(1, idDoProduto);
			preparedStatementDel.execute();
			
			// ==============
			
			String sql_procuraCar = "SELECT * FROM carrinho WHERE codigoDoProduto = ?";
			preparedStatementCar = connection.prepareStatement(sql_procuraCar);
			preparedStatementCar.setInt(1, idDoProduto);
			ResultSet resultSetCar = preparedStatementCar.executeQuery();
			
			int qtdC = resultSetCar.getInt("quantidadeDoProduto");
			
			String sql_procuraProd = "SELECT * FROM produto WHERE codigoDoProduto = ?";
			preparedStatementPro = connection.prepareStatement(sql_procuraProd);
			preparedStatementPro.setInt(1, idDoProduto);
			ResultSet resultSetPro = preparedStatementPro.executeQuery();
			
			int qtdP = resultSetPro.getInt("quantidadeDoProduto");
			double pp = resultSetPro.getDouble("precoDoProduto");
			
		    int atualizaQuantidade = qtdP + qtdC;
		    double atualizaSaldoTotal = atualizaQuantidade * pp;
			
			String sql_atualiza = "UPDATE produto SET quantidadeDoProduto = ?, saldoEmEstoque = ? WHERE codigoDoProduto = ?";
			preparedStatementAtu = connection.prepareStatement(sql_atualiza);
			preparedStatementAtu.setInt(1, atualizaQuantidade);
			preparedStatementAtu.setDouble(2, atualizaSaldoTotal);
			preparedStatementAtu.setInt(3, idDoProduto);
			preparedStatementAtu.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Não foi possível excluir essa informação.");
			return;
		}
	}
}
