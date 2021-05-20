package br.com.tkp.controller.produto;

import java.util.Scanner;

public class Produto {

	private Scanner tec;

	public Produto() {
		tec = new Scanner(System.in);
	}

	public int opcao() {
		System.out.print("\n> ");
		return tec.nextInt();
	}

	public void menu() {
		System.out.print("\n======== MENU ========\n"
				+ "1 - Cadastrar itens\n"
				+ "2 - Listar estoque\n"
				+ "3 - Editar item\n"
				+ "4 - Remover item\n"
				+ "5 - Adicionar no carrinho\n"
				+ "6 - Carrinho\n"
				+ "7 - Conta\n"
				+ "8 - Sair\n"
				+ "======================");
	}
}