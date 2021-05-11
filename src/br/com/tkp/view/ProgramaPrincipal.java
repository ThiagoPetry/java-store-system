package br.com.tkp.view;

import java.util.ArrayList;
import java.util.List;

import br.com.tkp.controller.carrinho.AdicionaItemNoCarrinho;
import br.com.tkp.controller.carrinho.Carrinho;
import br.com.tkp.controller.pessoa.AdicionaPessoa;
import br.com.tkp.controller.produto.CadastraProduto;
import br.com.tkp.controller.produto.DeletaProduto;
import br.com.tkp.controller.produto.EditaProduto;
import br.com.tkp.controller.produto.ListaProduto;
import br.com.tkp.controller.produto.Produto;
import br.com.tkp.model.CarrinhoModel;
import br.com.tkp.model.PessoaModel;
import br.com.tkp.model.ProdutoModel;

public class ProgramaPrincipal {

	public static void main(String[] args) {
		List<ProdutoModel> produtos = new ArrayList<ProdutoModel>();
		List<CarrinhoModel> produtosCarrinho = new ArrayList<CarrinhoModel>();
		List<PessoaModel> pessoas = new ArrayList<PessoaModel>();

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
		
		String cliente = adicionaPessoa.definirCliente();

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
				editaProduto.editarProduto(produtos);
				break;
			case 4:
				deletaProduto.removerProduto(produtos);
				break;
			case 5:
				produtosCarrinho.add(addCarrinho.cadastrarProdutoNoCarrinho(produtos));
				break;
			case 6:
				carrinhoController.menuCarrinho(produtos, produtosCarrinho, cliente);
				break;
			case 7:
				adicionaPessoa.menuConta(pessoas, produtosCarrinho, produtos);
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
