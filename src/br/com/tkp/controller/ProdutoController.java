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
		System.out.print(
				"\n======== MENU ========\n1 - Cadastrar itens\n2 - Listar estoque\n3 - Editar item\n4 - Remover item\n5 - Realizar venda\n6 - Sair\n======================");
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

	public List<ProdutoModel> listarProdutos(List<ProdutoModel> produtos) {
		System.out.println("\n==== Produtos Cadastrados ====\n");
		System.out.printf("| %10s | %8s | %4s | %9s |\n", "Produto", "Preço", "Qtd", "R$ total");

		produtos.forEach(produto -> {
			System.out.printf("| %8s | %8s | %4s | %9s |\n", produto.getNomeProd(), produto.getPrecoProd(),
					produto.getQtdProd(), produto.getSaldoEstoque());
		});
		return produtos;
	}
	
	public ProdutoModel editarProduto(List<ProdutoModel> produtos) {
		ProdutoModel produto = new ProdutoModel();
		int idDoProduto, indexDoCampo;
		
		System.out.println("\n==== Editar produto ====\n");
		System.out.print("Informe o ID do produto: ");
		idDoProduto = tec.nextInt();
		
		System.out.print("\n1 - Nome do produto\n2 - Preço do produto\n3 - Quantidade do produto");
		System.out.print("\nInforme o campo que deseja editar: ");
		indexDoCampo = tec.nextInt();
		
		switch (indexDoCampo) {
		case 1:
			System.out.print("Informe o novo nome do produto: ");
			produto.setNomeProd(tec.next());
			
			produto.setPrecoProd(produtos.get(idDoProduto).getPrecoProd());
			produto.setQtdProd(produtos.get(idDoProduto).getQtdProd());
			produto.setSaldoEstoque(produtos.get(idDoProduto).getSaldoEstoque());
			
			produtos.set(idDoProduto, produto);
			break;
		case 2:
			System.out.print("Informe o novo preço do produto: ");
			produto.setPrecoProd(tec.nextDouble());
			
			produto.setNomeProd(produtos.get(idDoProduto).getNomeProd());
			produto.setQtdProd(produtos.get(idDoProduto).getQtdProd());
			produto.setSaldoEstoque(produtos.get(idDoProduto).getSaldoEstoque());
			
			produtos.set(idDoProduto, produto);
			break;
		case 3:
			System.out.print("Informe a nova quantidade do produto: ");
			produto.setNomeProd(tec.next());
			
			produto.setPrecoProd(produtos.get(idDoProduto).getPrecoProd());
			produto.setQtdProd(produtos.get(idDoProduto).getQtdProd());
			produto.setSaldoEstoque(produtos.get(idDoProduto).getSaldoEstoque());
			
			produtos.set(idDoProduto, produto);
			break;

		default:
			break;
		}
		
		return null;
	}
	
	
}








