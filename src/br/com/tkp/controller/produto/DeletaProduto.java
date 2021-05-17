package br.com.tkp.controller.produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import br.com.dao.DataBaseConnection;

public class DeletaProduto {

	private ListaProduto listaProduto;
	private Scanner tec = new Scanner(System.in);
	private Connection connection;

	public DeletaProduto() {
		connection = DataBaseConnection.getInstance().getConnection();
	}
	
	public boolean verificaSeExisteProduto(int idDoProduto) {
		PreparedStatement preparedStatement;
		try {
			String sql = "SELECT * FROM produto WHERE codigoDoProduto = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, idDoProduto);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if(!resultSet.next()) {
				System.out.println("Este produto não existe.");
				return false;
			} else {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public void removerProduto() {
		PreparedStatement preparedStatement;
		listaProduto = new ListaProduto();

		if (listaProduto.listarProdutos() == null) {
			return;
		}

		System.out.println("\n=============== Remover produto ================");
		System.out.print("\nInforme o ID do produto a ser removido: ");
		int idDoProduto = tec.nextInt();
		
		try {
			if(!verificaSeExisteProduto(idDoProduto)) {
				return;
			}
			String sql = "DELETE FROM produto WHERE codigoDoProduto = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, idDoProduto);
			preparedStatement.execute();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Não foi possível excluir essa informação.");
			return;
		}	
	}
}