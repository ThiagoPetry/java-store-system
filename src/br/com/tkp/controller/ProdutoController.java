package br.com.tkp.controller;

import java.util.List;
import java.util.Scanner;

import br.com.tkp.model.PessoaModel;
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
		System.out.print("\n======== MENU ========\n1 - Cadastrar itens\n2 - Listar estoque\n3 - Editar item\n4 - Remover item\n5 - Realizar venda\n6 - Carrinho\n7 - Conta\n8 - Sair\n======================");
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
		System.out.println("\n============ Produtos Cadastrados =============");
		System.out.printf("| %2s | %8s | %8s | %4s | %9s |\n", "ID", "Produto", "Preço", "Qtd", "R$ total");

		// produtos.forEach(produto -> {
		// System.out.printf("| %8s | %8s | %4s | %9s |\n", produto.getNomeProd(),
		// produto.getPrecoProd(),
		// produto.getQtdProd(), produto.getSaldoEstoque());
		// });

		for (int x = 0; x < produtos.size(); x++) {
			System.out.printf("| %2s | %8s | %8s | %4s | %9s |\n", x + 1, produtos.get(x).getNomeProd(),
					produtos.get(x).getPrecoProd(), produtos.get(x).getQtdProd(), produtos.get(x).getSaldoEstoque());
		}
		return produtos;
	}

	public ProdutoModel editarProduto(List<ProdutoModel> produtos) {
		ProdutoModel produto = new ProdutoModel();
		int idDoProduto, indexDoCampo;

		if (produtos.size() <= 0) {
			System.out.println("\nNão existe nenhum produto cadastrado.");
			return null;
		}

		listarProdutos(produtos);

		System.out.println("\n============= Editar produto =============\n");
		System.out.print("Informe o ID do produto: ");
		idDoProduto = tec.nextInt() - 1;

		if (idDoProduto >= produtos.size()) {
			System.out.println("\nEste produto não existe.");
			return null;
		}

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
			produto.setSaldoEstoque(produtos.get(idDoProduto).getQtdProd() * produto.getPrecoProd());

			produtos.set(idDoProduto, produto);
			break;
		case 3:
			System.out.print("Informe a nova quantidade do produto: ");
			produto.setQtdProd(tec.nextInt());

			produto.setPrecoProd(produtos.get(idDoProduto).getPrecoProd());
			produto.setNomeProd(produtos.get(idDoProduto).getNomeProd());
			produto.setSaldoEstoque(produtos.get(idDoProduto).getPrecoProd() * produto.getQtdProd());

			produtos.set(idDoProduto, produto);
			break;

		default:
			System.out.println("\nCampo inválido.");
			break;
		}
		return null;
	}

	public void removerProduto(List<ProdutoModel> produtos) {
		if (produtos.size() <= 0) {
			System.out.print("\nNão existe nenhum produto cadastrado.\n");
			return;
		}

		System.out.println("\n============= Remover produto ==============");
		listarProdutos(produtos);

		System.out.print("\nInforme o ID do produto a ser removido: ");
		int idDoProduto = tec.nextInt();

		if (idDoProduto > produtos.size()) {
			System.out.println("\nEste produto não existe.");
			return;
		}

		produtos.remove(idDoProduto - 1);
		System.out.print("\nProduto removido com sucesso!");
	}

	public void realizarVenda(List<ProdutoModel> produtos, List<PessoaModel> pessoaModel) {
		if (produtos.size() <= 0) {
			System.out.print("\nNão existe nenhum produto cadastrado.\n");
			return;
		}

		System.out.println("\n=============== Realizar venda ================");
		listarProdutos(produtos);
	}
}
