package br.com.tkp.controller;

import java.util.List;
import java.util.Scanner;

import br.com.tkp.model.ProdutoModel;

public class ProdutoController {
	
	private Scanner tec;
	
	public ProdutoController() {
		tec = new Scanner(System.in);
	}
	
	public int opcao() {
		System.out.print("\n> ");
		return tec.nextInt();
	}

	public void menu() {
		System.out.print("\n======== MENU ========\n1 - Cadastrar itens\n2 - Listar estoque\n3 - Editar item\n4 - Remover item\n5 - Realizar venda\n6 - Sair\n======================");
	}
	
	public ProdutoModel cadastrarProduto() {
		ProdutoModel lojaModel = new ProdutoModel();
		
		System.out.print("\n==== Cadastrar Itens ====");
		System.out.print("\nProduto: ");
		tec.nextLine();
		lojaModel.setNomeProd(tec.nextLine());
		System.out.print("Preço: ");
		lojaModel.setPrecoProd(tec.nextDouble());
		System.out.print("Quantidade: ");
		lojaModel.setQtdProd(tec.nextInt());
		lojaModel.setSaldoEstoque(lojaModel.getQtdProd() * lojaModel.getPrecoProd());
		return lojaModel;
	}
	
	public void consultarProdutos(List<ProdutoModel> produtos) {
		System.out.println("\n==== Produtos Cadastrados ====\n");
		System.out.printf("| %10s | %8s | %4s | %9s |\n", "Produto", "Preço", "Quantidade", "R$ total");
		for (ProdutoModel produtoModel : produtos) {
			System.out.println(produtoModel.toString());					
		}
	}
}

















