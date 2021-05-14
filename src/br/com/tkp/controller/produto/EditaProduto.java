package br.com.tkp.controller.produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Scanner;

import br.com.dao.DataBaseConnection;
import br.com.tkp.model.ProdutoModel;

public class EditaProduto {

	private Scanner tec = new Scanner(System.in);
	private ListaProduto listaProduto;
	private ProdutoModel produto;
	private Connection connection;

	public EditaProduto() {
		connection = DataBaseConnection.getInstance().getConnection();
	}

	public ProdutoModel editarProduto() {
		PreparedStatement preparedStatement;
				
		listaProduto = new ListaProduto();
		produto = new ProdutoModel();
		int idDoProduto, indexDoCampo;

		if (listaProduto.listarProdutos() == null) {
			return null;
		}

		System.out.println("\n--> Editar produto\n");
		System.out.print("Informe o ID do produto: ");
		idDoProduto = tec.nextInt();
		
		try {
			String sql = "SELECT * FROM produto WHERE codigoDoProduto = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, idDoProduto);
			
			ResultSet resultSet = preparedStatement.executeQuery();			
			
			if(!resultSet.next()) {
				System.out.println("\nEste produto não existe.");
				return null;
			} else {
				produto.setNomeProd(resultSet.getString("nomeDoProduto"));
				produto.setPrecoProd(resultSet.getDouble("precoDoProduto"));
				produto.setQtdProd(resultSet.getInt("quantidadeDoProduto"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		System.out.print("\n1 - Nome do produto\n2 - Preço do produto\n3 - Quantidade do produto");
		System.out.print("\nInforme o campo que deseja editar: ");
		indexDoCampo = tec.nextInt();

		switch (indexDoCampo) {
		case 1:
			this.editarNomeDoProduto(idDoProduto);
			break;
		case 2:
			this.editarPrecoDoProduto(idDoProduto);
			break;
		case 3:
			this.editarQuantidadeDoProduto(idDoProduto);
			break;
		default:
			System.out.println("\nCampo inválido.");
			break;
		}
		return null;
	}

	private ProdutoModel editarNomeDoProduto(int idDoProduto) {
		PreparedStatement preparedStatement;
		
		System.out.print("Informe o novo nome do produto: ");
		produto.setNomeProd(tec.next());
		
		try {
			String sql = "UPDATE produto SET nomeDoProduto = ? WHERE codigoDoProduto = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, produto.getNomeProd());
			preparedStatement.setInt(2, idDoProduto);
			preparedStatement.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return produto;
	}

	private ProdutoModel editarPrecoDoProduto(int idDoProduto) {
		PreparedStatement preparedStatement;
		
		System.out.print("Informe o novo preço do produto: ");
		produto.setPrecoProd(tec.nextDouble());
		
		produto.setSaldoEstoque(produto.getPrecoProd() * produto.getQtdProd());
		
		try {
			String sql = "UPDATE produto SET precoDoProduto = ?, saldoEmEstoque = ? WHERE codigoDoProduto = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setDouble(1, produto.getPrecoProd());
			preparedStatement.setDouble(2, produto.getSaldoEstoque());
			preparedStatement.setInt(3, idDoProduto);
			preparedStatement.execute();
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return produto;
	}

	private ProdutoModel editarQuantidadeDoProduto(int idDoProduto) {
		PreparedStatement preparedStatement;
		
		System.out.print("Informe a nova quantidade do produto: ");
		produto.setQtdProd(tec.nextInt());
		
		produto.setSaldoEstoque(produto.getPrecoProd() * produto.getQtdProd());
		
		try {
			String sql = "UPDATE produto SET quantidadeDoProduto = ?, saldoEmEstoque = ? WHERE codigoDoProduto = ?";
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setInt(1, produto.getQtdProd());
			preparedStatement.setDouble(2, produto.getSaldoEstoque());
			preparedStatement.setInt(3, idDoProduto);
			
			preparedStatement.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return produto;
	}

	public List<ProdutoModel> atualizarQuantidadeValorTotal(List<ProdutoModel> produtos, int quantidade, int idDoProduto) {
		produto.setQtdProd(produtos.get(idDoProduto).getQtdProd() - quantidade);
		produto.setSaldoEstoque(produtos.get(idDoProduto).getPrecoProd() * produto.getQtdProd());
		produto.setNomeProd(produtos.get(idDoProduto).getNomeProd());
		produto.setPrecoProd(produtos.get(idDoProduto).getPrecoProd());

		produtos.set(idDoProduto, produto);

		return produtos;
	}

}