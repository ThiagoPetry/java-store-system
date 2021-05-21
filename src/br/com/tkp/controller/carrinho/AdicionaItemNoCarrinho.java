package br.com.tkp.controller.carrinho;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import br.com.dao.DataBaseConnection;
import br.com.tkp.controller.produto.ListaProduto;
import br.com.tkp.model.CarrinhoModel;

public class AdicionaItemNoCarrinho {

	private ListaProduto listaProduto;
	private VerificaSeExisteProduto verificaSeExisteProduto;
	private CarrinhoModel carrinhoModel;
	private Scanner tec = new Scanner(System.in);
	private Connection connection;

	public AdicionaItemNoCarrinho() {
		connection = DataBaseConnection.getInstance().getConnection();
	}

	public void cadastrarProdutoNoCarrinho(int cliente) {
		PreparedStatement preparedStatement, preparedStatementAtu, preparedStatementUpd, preparedStatementIns;
		
		verificaSeExisteProduto = new VerificaSeExisteProduto();
		carrinhoModel = new CarrinhoModel();
		listaProduto = new ListaProduto();
		
		if (listaProduto.listarProdutos() == null) {
			return;
		}
		
		int idDoProduto, quantidadeNoCarrinho;

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
				if (!verificaSeExisteProduto.procurarPeloProduto(idDoProduto, cliente)) {
					String sql_insere = "INSERT INTO carrinho (codigoDoUsuario, codigoDoProduto, quantidadeDoProduto) VALUES(?, ?, ?)";
					preparedStatementIns = connection.prepareStatement(sql_insere);
					preparedStatementIns.setInt(1, cliente);
					preparedStatementIns.setInt(2, idDoProduto);
					preparedStatementIns.setInt(3, quantidadeNoCarrinho);
					preparedStatementIns.execute();
				} else {
					String sql_update = "UPDATE carrinho SET quantidadeDoProduto = quantidadeDoProduto + ? "
							+ "WHERE codigoDoProduto = ? AND codigoDoUsuario = ?";
					preparedStatementUpd = connection.prepareStatement(sql_update);
					preparedStatementUpd.setInt(1, quantidadeNoCarrinho);
					preparedStatementUpd.setInt(2, idDoProduto);
					preparedStatementUpd.setInt(3, cliente);
					preparedStatementUpd.execute();
				}

				// Atualiza a tabela produto
				String sql_atualiza = "UPDATE produto SET quantidadeDoProduto = quantidadeDoProduto - ?,"
						+ " saldoEmEstoque = quantidadeDoProduto * precoDoProduto WHERE codigoDoProduto = ?;";
				preparedStatementAtu = connection.prepareStatement(sql_atualiza);
				preparedStatementAtu.setInt(1, quantidadeNoCarrinho);
				preparedStatementAtu.setInt(2, idDoProduto);
				preparedStatementAtu.execute();
			}
		} catch (Exception e) {
			System.out.println("\nNão foi possível realizar a compra!");
			return;
		}
	}
}