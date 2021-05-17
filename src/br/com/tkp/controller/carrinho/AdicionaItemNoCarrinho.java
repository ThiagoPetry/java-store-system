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

	public void cadastrarProdutoNoCarrinho(String cliente) {
		PreparedStatement preparedStatement;
		listaProduto = new ListaProduto();

		if (listaProduto.listarProdutos() == null) {
			return;
		}

		System.out.print("\n---- Adicionar Item ----");
		System.out.print("\nInforme o ID: ");
		carrinhoModel.setIdDoProduto(tec.nextInt());
		int idDoProduto = carrinhoModel.getIdDoProduto();
		
		System.out.print("Informa a quantidade desejada: ");
		carrinhoModel.setQuantidadeDeItensNoCarrinho(tec.nextInt());
		
		try {
			String sql_procura = "SELECT * FROM produto WHERE codigoDoProduto = ?";
			preparedStatement = connection.prepareStatement(sql_procura);
			preparedStatement.setInt(1, idDoProduto);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if(!resultSet.next()) {
				System.out.print("\nEste produto não existe.\n");
				return;
			} else {
				String sql = "INSERT INTO carrinho (nomeDoUsuario, codigoDoProduto, nomeDoProduto, quantidadeDoProduto, valorTotal) VALUES(?, ?, ?, ?, ?)";
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, cliente);
				preparedStatement.setInt(2, idDoProduto);
				preparedStatement.setString(3, resultSet.getString("nomeDoProduto"));
				preparedStatement.setInt(4, carrinhoModel.getQuantidadeDeItensNoCarrinho());
				preparedStatement.setDouble(5, (resultSet.getDouble("precoDoProduto") * carrinhoModel.getQuantidadeDeItensNoCarrinho()));
				
				preparedStatement.execute();
				
				// Atualiza a tabela produto (quantidade e saldo total) de acordo com o produto selecionado
				
				int atualizaQuantidade = resultSet.getInt("quantidadeDoProduto") - carrinhoModel.getQuantidadeDeItensNoCarrinho();
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
