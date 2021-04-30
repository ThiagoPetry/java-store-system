package br.com.tkp.controller;

import java.util.List;
import java.util.Scanner;

import br.com.tkp.model.CarrinhoModel;
import br.com.tkp.model.PessoaModel;
import br.com.tkp.model.ProdutoModel;

public class CarrinhoController {

	private Scanner tec;

	public CarrinhoController() {
		tec = new Scanner(System.in);
	}

	public List<CarrinhoModel> carrinho(List<CarrinhoModel> produtosCarrinho) {
		System.out.println("\n=========== Carrinho ==========");
		System.out.printf("| %2s | %8s | %4s | %6s | %6s |\n", "ID", "Nome", "Qtd", "Valor", "Total");

		for (int x = 0; x < produtosCarrinho.size(); x++) {
			System.out.printf("| %2s | %8s | %4s | %6s | %6s |\n", x + 1, produtosCarrinho.get(x).getNomeProduto(),
					produtosCarrinho.get(x).getQtdItens(), produtosCarrinho.get(x).getPrecoUnitario(),
					produtosCarrinho.get(x).getPrecoTotal());
		}
		return produtosCarrinho;
	}

	public void menuCarrinho(List<ProdutoModel> produtos, List<CarrinhoModel> produtosCarrinho,
			List<PessoaModel> pessoaModel) {
		carrinho(produtosCarrinho);

		System.out.print("\n1 - Finalizar compra\n2 - Remover item\n3 - Voltar ao menu\n> ");
		int opc = tec.nextInt();

		switch (opc) {
		case 1:
			this.finalizarCompra(produtosCarrinho, pessoaModel);
			break;
		case 2:
			this.removerProdutoCarrinho(produtos, produtosCarrinho);
			break;
		case 3:
			break;
		default:
			System.out.print("\nOpção inválida!");
			break;
		}

	}

	public void finalizarCompra(List<CarrinhoModel> produtosCarrinho, List<PessoaModel> pessoaModel) {
		if (produtosCarrinho.size() == 0) {
			System.out.print("\nNão existe nenhum produto no carrinho.\n");
			return;
		}
		
		double total = 0;

		System.out.print("\n[COMPROVANTE - LOJA SENAI]");
		System.out.print("\n--------------------------------------------------------------");
		System.out.print("\nCliente: ");
		System.out.print("\n--------------------------------------------------------------\n");
		System.out.printf("| %8s | %3s | %6s | %6s |\n", "Produto", "Qtd", "Valor", "Total");
		
		for (int x = 0; x < produtosCarrinho.size(); x++) {
			System.out.printf("| %8s | %3s | %6s | %6s |\n", produtosCarrinho.get(x).getNomeProduto(),
					produtosCarrinho.get(x).getQtdItens(), produtosCarrinho.get(x).getPrecoUnitario(), produtosCarrinho.get(x).getPrecoTotal());
			
			total += produtosCarrinho.get(x).getPrecoTotal();
			produtosCarrinho.remove(x);
		}
		
		System.out.print("--------------------------------------------------------------");
		System.out.printf("\nTotal: %5s", total);
		System.out.print("\n--------------------------------------------------------------\n");		
		
		
	}

	public void adicionarProdutoCarrinho(List<ProdutoModel> produtos, List<CarrinhoModel> produtosCarrinho) {
		if (produtos.size() <= 0) {
			System.out.print("\nNão existe nenhum produto cadastrado.\n");
			return;
		}

		System.out.println("\n=============== Realizar venda ================");
		var adicionarNoCarrinho = new CarrinhoModel();
		var listaProdutos = new ProdutoController();

		if (listaProdutos.listarProdutos(produtos) == null) {
			return;
		}

		int idDoProduto, qtdDoProduto;

		System.out.print("\nInforme o ID do produto a ser comprado: ");
		idDoProduto = tec.nextInt() - 1;

		if (idDoProduto > produtos.size()) {
			System.out.println("\nEste produto não existe.");
			return;
		}

		System.out.print("Informe a quantidade: ");
		qtdDoProduto = tec.nextInt();

		if (qtdDoProduto > produtos.get(idDoProduto).getQtdProd()) {
			System.out.print("\nEstoque insuficiente!");
			return;
		}

		produtos.get(idDoProduto).setQtdProd(produtos.get(idDoProduto).getQtdProd() - qtdDoProduto);
		produtos.get(idDoProduto).setSaldoEstoque(produtos.get(idDoProduto).getPrecoProd() * qtdDoProduto);

		adicionarNoCarrinho.setNomeProduto(produtos.get(idDoProduto).getNomeProd());
		adicionarNoCarrinho.setQtdItens(qtdDoProduto);
		adicionarNoCarrinho.setPrecoUnitario(produtos.get(idDoProduto).getPrecoProd());
		adicionarNoCarrinho.setPrecoTotal(adicionarNoCarrinho.getPrecoUnitario() * adicionarNoCarrinho.getQtdItens());

		produtosCarrinho.add(adicionarNoCarrinho);

	}

	public void removerProdutoCarrinho(List<ProdutoModel> produtos, List<CarrinhoModel> produtosCarrinho) {
		if (produtosCarrinho.size() == 0) {
			System.out.print("\nNão existe nenhum produto no carrinho.\n");
			return;
		}

		System.out.print("\nInforme o ID do produto a ser removido: ");
		int idDoProduto = tec.nextInt() - 1;

		if (idDoProduto > produtosCarrinho.size()) {
			return;
		}

		produtos.get(idDoProduto).setQtdProd(produtos.get(idDoProduto).getQtdProd() + produtosCarrinho.get(idDoProduto).getQtdItens());
		produtosCarrinho.remove(idDoProduto);
	}
}
