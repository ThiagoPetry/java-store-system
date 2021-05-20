package br.com.tkp.view;

import br.com.tkp.controller.carrinho.AdicionaItemNoCarrinho;
import br.com.tkp.controller.carrinho.Carrinho;
import br.com.tkp.controller.pessoa.AdicionaPessoa;
import br.com.tkp.controller.produto.CadastraProduto;
import br.com.tkp.controller.produto.DeletaProduto;
import br.com.tkp.controller.produto.EditaProduto;
import br.com.tkp.controller.produto.ListaProduto;
import br.com.tkp.controller.produto.Produto;

public class ProgramaPrincipal {

	public static void main(String[] args) {
		Produto produtoController = new Produto();
		Carrinho carrinhoController = new Carrinho();
		AdicionaPessoa adicionaPessoa = new AdicionaPessoa();
		
		AdicionaItemNoCarrinho addCarrinho = new AdicionaItemNoCarrinho();
		CadastraProduto cadastraProduto = new CadastraProduto();
		ListaProduto listaProduto = new ListaProduto();
		EditaProduto editaProduto = new EditaProduto();
		DeletaProduto deletaProduto = new DeletaProduto();
		
		boolean sair = false;
		int opcao = 0;	
		int cliente = -1;
		
		while(cliente == -1) {
			cliente = adicionaPessoa.definirCliente();
		}
		
		do {
			produtoController.menu();
			opcao = produtoController.opcao();

			switch (opcao) {
			case 1:
				cadastraProduto.cadastrarProduto();
				break;
			case 2:
				listaProduto.listarProdutos();
				break;
			case 3:
				editaProduto.editarProduto();
				break;
			case 4:
				deletaProduto.removerProduto();
				break;
			case 5:
				addCarrinho.cadastrarProdutoNoCarrinho(cliente);
				break;
			case 6:
				carrinhoController.menuCarrinho(cliente);
				break;
			case 7:
				cliente = adicionaPessoa.definirCliente();
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