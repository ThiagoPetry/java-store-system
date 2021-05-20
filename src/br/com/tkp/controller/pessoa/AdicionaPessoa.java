package br.com.tkp.controller.pessoa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import br.com.dao.DataBaseConnection;
import br.com.tkp.controller.produto.Produto;

public class AdicionaPessoa {

	private Scanner tec = new Scanner(System.in);
	Produto produtoController = new Produto();
	private Connection connection;

	public AdicionaPessoa() {
		connection = DataBaseConnection.getInstance().getConnection();
	}

	public int definirCliente() {
		PreparedStatement preparedStatement;
		String nome, sql;

		try {
			System.out.print("\nInforme o nome do cliente: ");
			nome = tec.next();

			sql = "SELECT * FROM usuario WHERE nome = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, nome);

			ResultSet resultSet = preparedStatement.executeQuery();

			if (!resultSet.next()) {
				System.out.println("\nUsuário inválido!");
				return -1;
			} else {
				return resultSet.getInt("codigo");
			}
		} catch (Exception e) {
			System.out.println("\nNão foi possível se conectar.");
			return -1;
		}
	}
}
