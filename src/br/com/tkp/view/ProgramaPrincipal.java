package br.com.tkp.view;

import java.util.ArrayList;
import java.util.List;
import br.com.tkp.controller.ProdutoController;
import br.com.tkp.model.ProdutoModel;

public class ProgramaPrincipal {

	public static void main(String[] args) {
		List<ProdutoModel> produtos = new ArrayList<ProdutoModel>();
		List<ProdutoModel> produtosCarrinho = new ArrayList<ProdutoModel>();
		ProdutoController produtoController = new ProdutoController();
		
		boolean sair = false;
		
		do {	
			produtoController.menu();
			int opcao = produtoController.opcao();
			
			switch (opcao) {
			case 1:
				produtos.add(produtoController.cadastrarProduto());
				break;
			case 2:
				produtoController.listarProdutos(produtos);
				break;
			case 3:
				produtoController.editarProduto(produtos);
				break;
			case 4:
				produtoController.removerProduto(produtos);
				break;
			case 5:
				produtoController.realizarVenda(produtos, produtosCarrinho);
				break;
			case 6:
				produtoController.carrinho(produtosCarrinho);
				break;
			case 7:
				sair = true;
				break;
			default:
				System.out.println("\nErro!");
				break;
			}
		} while(!sair);	
		System.out.println("\nSistema encerrado!"); 
	}	
}

















