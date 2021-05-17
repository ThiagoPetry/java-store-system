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

	public String definirCliente() {
		PreparedStatement preparedStatement;
		
		try {
			System.out.print("\nInforme o nome do cliente: ");
			String nome = tec.next();
			
			String sql = "SELECT * FROM usuario WHERE nome = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, nome);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if(!resultSet.next()) {
				System.out.println("\nUsuário inválido!");
				return null;
			} else {
				return nome;
			}			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Não foi possível se conectar.");
			return null;
		}
	}
}
