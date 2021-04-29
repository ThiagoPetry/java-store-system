package br.com.tkp.controller;

import java.util.List;
import java.util.Scanner;

import br.com.tkp.model.CarrinhoModel;
import br.com.tkp.model.ProdutoModel;

public class CarrinhoController {
	
	private Scanner tec;

	public CarrinhoController() {
		tec = new Scanner(System.in);
	}
	
	public void carrinho(List<CarrinhoModel> produtosCarrinho) {
		System.out.println("\n=========== Carrinho ==========");
		System.out.printf("| %2s | %4s | %6s | %6s |\n", "ID", "Qtd", "Valor", "Total");

		for (int x = 0; x < produtosCarrinho.size(); x++) {
			System.out.printf("| %2s | %4s | %6s | %6s |\n", x + 1, produtosCarrinho.get(x).getQtdItens(), 
			produtosCarrinho.get(x).getPrecoUnitario(), produtosCarrinho.get(x).getPrecoTotal());
		}		
	}	
	
	public void menuCarrinho(List<ProdutoModel> produtos, List<CarrinhoModel> produtosCarrinho) {
		carrinho(produtosCarrinho);
	
		System.out.print("\n1 - Remover item\n2 - Voltar ao menu\n> ");
		int opc = tec.nextInt();
		
		switch (opc) {
		case 1:
			removerProdutoCarrinho(produtos, produtosCarrinho);
			break;
		case 2:
			break;
		default:
			System.out.print("\nOpção inválida!");
			break;
		}
		
	}
	
	public void removerProdutoCarrinho(List<ProdutoModel> produtos, List<CarrinhoModel> produtosCarrinho) {
		System.out.print("\nInforme o ID do produto a ser removido: ");
		int idDoProduto = tec.nextInt() - 1;
		
		if(idDoProduto > produtosCarrinho.size()) {
			return;
		}
		produtos.get(idDoProduto).setQtdProd(produtos.get(idDoProduto).getQtdProd() + produtosCarrinho.get(idDoProduto).getQtdItens());
		produtosCarrinho.remove(idDoProduto);
	}	
}
