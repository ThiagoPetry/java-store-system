package br.com.tkp.view;

import java.util.ArrayList;
import java.util.List;

import br.com.tkp.controller.CarrinhoController;
import br.com.tkp.controller.PessoaController;
import br.com.tkp.controller.ProdutoController;
import br.com.tkp.model.CarrinhoModel;
import br.com.tkp.model.PessoaModel;
import br.com.tkp.model.ProdutoModel;

public class ProgramaPrincipal {

	public static void main(String[] args) {
		List<ProdutoModel> produtos = new ArrayList<ProdutoModel>();
		List<CarrinhoModel> produtosCarrinho = new ArrayList<CarrinhoModel>();
		List<PessoaModel> pessoas = new ArrayList<PessoaModel>();

		ProdutoController produtoController = new ProdutoController();
		CarrinhoController carrinhoController = new CarrinhoController();
		PessoaController menuController = new PessoaController();

		boolean sair = false;
		int opcao = 0;

		menuController.menuConta(pessoas, produtosCarrinho, produtos);

		do {
			produtoController.menu();
			opcao = produtoController.opcao();

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
				carrinhoController.adicionarProdutoCarrinho(produtos, produtosCarrinho);
				break;
			case 6:
				carrinhoController.menuCarrinho(produtos, produtosCarrinho, pessoas);
				break;
			case 7:
				menuController.menuConta(pessoas, produtosCarrinho, produtos);
				;
				break;
			case 8:
				sair = true;
				break;
			default:
				System.out.println("\nErro!");
				break;
			}
		} while (!sair);
		System.out.println("\nSistema encerrado!");
	}
}
