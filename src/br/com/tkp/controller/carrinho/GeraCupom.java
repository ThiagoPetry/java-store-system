package br.com.tkp.controller.carrinho;

import java.sql.Connection;
import java.sql.PreparedStatement;

import br.com.dao.DataBaseConnection;
import br.com.tkp.controller.pessoa.PesquisaPessoa;

public class GeraCupom {

	private ListaCarrinho listaCarrinho;
	private PesquisaPessoa pesquisaPessoa;
	private Connection connection;

	public GeraCupom() {
		connection = DataBaseConnection.getInstance().getConnection();
	}

	public void gerarCupom(int cliente) {
		PreparedStatement preparedStatement;
		listaCarrinho = new ListaCarrinho();
		pesquisaPessoa = new PesquisaPessoa();

		try {
			if(listaCarrinho.carrinho(cliente) == null) {
				System.out.print("\nVocê não possui nenhum produto no carrinho, por isso não pode finalizar a compra.\n");
				return;
			}
			
			System.out.print("\n\n====================================================");
			System.out.print("\nCOMPRA FINALIZADA | CLIENTE: " + pesquisaPessoa.pesquisaNomePessoa(cliente));
			System.out.print("\n====================================================");
			listaCarrinho.carrinho(cliente);
			
			// Remove o(s) produto(s) do carrinho após a finalização da compra
			String sql_deleta = "DELETE FROM carrinho WHERE codigoDoUsuario = ?";
			preparedStatement = connection.prepareStatement(sql_deleta);
			preparedStatement.setInt(1, cliente);
			preparedStatement.execute();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("\nNão foi possível finalizar a compra.");
			return;
		}
	}
}