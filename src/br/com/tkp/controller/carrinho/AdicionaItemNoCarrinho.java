package br.com.tkp.controller.carrinho;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import br.com.dao.DataBaseConnection;
import br.com.tkp.controller.produto.EditaProduto;
import br.com.tkp.controller.produto.ListaProduto;
import br.com.tkp.model.CarrinhoModel;

public class AdicionaItemNoCarrinho {

	private ListaProduto listaProduto;
	private Scanner tec = new Scanner(System.in);
	private Connection connection;

	CarrinhoModel carrinhoModel = new CarrinhoModel();
	Carrinho carrinhoController = new Carrinho();
	EditaProduto editaProduto = new EditaProduto();

	public AdicionaItemNoCarrinho() {
		connection = DataBaseConnection.getInstance().getConnection();
	}

	public void cadastrarProdutoNoCarrinho(int cliente) {
		PreparedStatement preparedStatement;
		listaProduto = new ListaProduto();
		int idDoProduto, quantidadeNoCarrinho;

		if (listaProduto.listarProdutos() == null) {
			return;
		}

		System.out.print("\n---- Adicionar Item ----\nInforme o ID: ");
		carrinhoModel.setIdDoProduto(tec.nextInt());
		idDoProduto = carrinhoModel.getIdDoProduto();

		System.out.print("Informa a quantidade desejada: ");
		carrinhoModel.setQuantidadeDeItensNoCarrinho(tec.nextInt());
		quantidadeNoCarrinho = carrinhoModel.getQuantidadeDeItensNoCarrinho();

		try {
			String sql_procura = "SELECT * FROM produto WHERE codigoDoProduto = ?";
			preparedStatement = connection.prepareStatement(sql_procura);
			preparedStatement.setInt(1, idDoProduto);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (!resultSet.next()) {
				System.out.print("\nEste produto não existe.\n");
				return;
			} else {
				String sql = "INSERT INTO carrinho (codigoDoUsuario, codigoDoProduto, quantidadeDoProduto) VALUES(?, ?, ?)";
				preparedStatement = connection.prepareStatement(sql);	
				preparedStatement.setInt(1, cliente);
				preparedStatement.setInt(2, idDoProduto);
				preparedStatement.setInt(3, quantidadeNoCarrinho);
				preparedStatement.execute();

				// Atualiza a tabela produto (quantidade e saldo total) de acordo com o produto selecionado
				int atualizaQuantidade = resultSet.getInt("quantidadeDoProduto") - quantidadeNoCarrinho;
				double atualizaSaldoTotal = atualizaQuantidade * resultSet.getDouble("precoDoProduto");

				String sql_atualiza = "UPDATE produto SET quantidadeDoProduto = ?, saldoEmEstoque = ? WHERE codigoDoProduto = ?";
				preparedStatement = connection.prepareStatement(sql_atualiza);
				preparedStatement.setInt(1, atualizaQuantidade);
				preparedStatement.setDouble(2, atualizaSaldoTotal);
				preparedStatement.setInt(3, idDoProduto);

				preparedStatement.execute();
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erro!");
		}
	}
}