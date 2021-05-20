package br.com.tkp.controller.carrinho;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

import br.com.dao.DataBaseConnection;

public class RemoveProduto {

	private Scanner tec = new Scanner(System.in);
	private VerificaSeExisteProduto verificaSeExisteProduto;
	private Connection connection;

	public RemoveProduto() {
		connection = DataBaseConnection.getInstance().getConnection();
	}

	public void removerProdutoCarrinho(int cliente) {
		PreparedStatement preparedStatementDel, preparedStatementAtu;
		verificaSeExisteProduto = new VerificaSeExisteProduto();
		int idDoProduto;

		System.out.println("\n===== Remover produto do carrinho =====");
		System.out.print("Informe o ID do produto a ser removido: ");
		idDoProduto = tec.nextInt();

		try {
			if (verificaSeExisteProduto.procurarPeloProduto(idDoProduto) == false) {
				return;
			}

			// Atualiza a tabela produto
			String sql_atualiza = "UPDATE produto SET quantidadeDoProduto = quantidadeDoProduto + "
					+ "(SELECT quantidadeDoProduto FROM carrinho WHERE codigoDoProduto = ? AND codigoDoUsuario = ?), "
					+ "saldoEmEstoque = quantidadeDoProduto * precoDoProduto WHERE codigoDoProduto = ?;";
			preparedStatementAtu = connection.prepareStatement(sql_atualiza);
			preparedStatementAtu.setInt(1, idDoProduto);
			preparedStatementAtu.setInt(2, cliente);
			preparedStatementAtu.setInt(3, idDoProduto);
			preparedStatementAtu.execute();

			// Remove o produto do carrinho
			String sql_delete = "DELETE FROM carrinho WHERE codigoDoProduto = ? AND codigoDoUsuario = ?";
			preparedStatementDel = connection.prepareStatement(sql_delete);
			preparedStatementDel.setInt(1, idDoProduto);
			preparedStatementDel.setInt(2, cliente);
			preparedStatementDel.execute();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Não foi possível excluir essa informação.");
			return;
		}
	}
}