package br.com.tkp.controller.produto;

import java.util.List;

import br.com.tkp.model.ProdutoModel;

public class ListaProduto {
	public List<ProdutoModel> listarProdutos(List<ProdutoModel> produtos) {
		System.out.println("\n============ Produtos Cadastrados =============");
		System.out.printf("| %2s | %8s | %8s | %4s | %9s |\n", "ID", "Produto", "Preço", "Qtd", "R$ total");

		produtos.forEach(produto -> {
			System.out.printf("| %2s | %8s | %8s | %4s | %9s |\n", produtos.indexOf(produto), produto.getNomeProd(),
					produto.getPrecoProd(), produto.getQtdProd(), produto.getSaldoEstoque());
		});

		return produtos;
	}
}
