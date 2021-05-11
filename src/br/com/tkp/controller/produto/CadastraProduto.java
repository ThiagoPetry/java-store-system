package br.com.tkp.controller.produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

import br.com.dao.DataBaseConnection;
import br.com.tkp.model.ProdutoModel;

public class CadastraProduto {

	private Scanner tec = new Scanner(System.in);
	private Connection connection;

	public CadastraProduto() {
		connection = DataBaseConnection.getInstance().getConnection();
	}

	public ProdutoModel cadastrarProduto() {
		ProdutoModel lojaModel = new ProdutoModel();

		System.out.print("\n==== Cadastrar Itens ====");
		System.out.print("\nProduto: ");
		lojaModel.setNomeProd(tec.next());
		System.out.print("Preço: ");
		lojaModel.setPrecoProd(tec.nextDouble());
		System.out.print("Quantidade: ");
		lojaModel.setQtdProd(tec.nextInt());
		lojaModel.setSaldoEstoque(lojaModel.getQtdProd() * lojaModel.getPrecoProd());

		try {
			String sql = "INSERT INTO produto (nomeDoProduto, precoDoProduto, quantidadeDoProduto, saldoEmEstoque) "
					+ "VALUES (?, ?, ?, ?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, lojaModel.getNomeProd());
			preparedStatement.setDouble(2, lojaModel.getPrecoProd());
			preparedStatement.setInt(3, lojaModel.getQtdProd());
			preparedStatement.setDouble(4, lojaModel.getSaldoEstoque());
			
			preparedStatement.execute();
		} catch (Exception e) {
			System.out.println("Erro ao cadastrar os dados.");
		}

		return lojaModel;
	}

}
