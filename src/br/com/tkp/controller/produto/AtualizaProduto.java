package br.com.tkp.controller.produto;

import java.sql.Connection;
import java.sql.PreparedStatement;

import br.com.dao.DataBaseConnection;
import br.com.tkp.controller.carrinho.VerificaSeExisteProduto;

public class AtualizaProduto {

	private VerificaSeExisteProduto verificaSeExisteProduto;
	private Connection connection;

	public AtualizaProduto() {
		connection = DataBaseConnection.getInstance().getConnection();
	}

	public void atualizaProduto(int cliente, int idDoProduto) {
		PreparedStatement preparedStatementAtu;
		verificaSeExisteProduto = new VerificaSeExisteProduto();

		try {
			if (verificaSeExisteProduto.procurarPeloProduto(idDoProduto, cliente) == false) {
				return;
			}

			String sql_atualiza = "UPDATE produto SET quantidadeDoProduto = quantidadeDoProduto + "
					+ "(SELECT quantidadeDoProduto FROM carrinho WHERE codigoDoProduto = ? AND codigoDoUsuario = ?), "
					+ "saldoEmEstoque = quantidadeDoProduto * precoDoProduto WHERE codigoDoProduto = ?;";

			preparedStatementAtu = connection.prepareStatement(sql_atualiza);
			preparedStatementAtu.setInt(1, idDoProduto);
			preparedStatementAtu.setInt(2, cliente);
			preparedStatementAtu.setInt(3, idDoProduto);
			preparedStatementAtu.execute();

		} catch (Exception e) {
			System.out.println("Não foi possível atualizar o produto.");
			return;
		}
	}
}
