package br.com.tkp.controller.carrinho;

import java.util.Scanner;

public class Carrinho {

	private Scanner tec;
	ListaCarrinho listaCarrinho = new ListaCarrinho();
	RemoveProduto removeProduto = new RemoveProduto();

	public Carrinho() {
		tec = new Scanner(System.in);
	}

	public void menuCarrinho(String cliente) {
		listaCarrinho.carrinho(cliente);

		System.out.print("\n"
				+ "======================\n"
				+ "1 - Finalizar compra\n"
				+ "2 - Remover item\n"
				+ "3 - Voltar ao menu\n"
				+ "======================\n"
				+ "> ");
		int opc = tec.nextInt();

		switch (opc) {
		case 1:
			listaCarrinho.gerarCupom(cliente);
			break;
		case 2:
			removeProduto.removerProdutoCarrinho(cliente);
			break;
		case 3:
			break;
		default:
			System.out.print("\nOpção inválida!");
			break;
		}

	}
}
