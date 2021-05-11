package br.com.tkp.controller.produto;

import java.util.List;
import java.util.Scanner;

import br.com.tkp.model.ProdutoModel;

public class DeletaProduto {

	private Scanner tec;

	public DeletaProduto() {
		tec = new Scanner(System.in);
	}

	public void removerProduto(List<ProdutoModel> produtos) {
		ListaProduto listaProduto = new ListaProduto();
		if (produtos.size() <= 0) {
			System.out.print("\nNão existe nenhum produto cadastrado.\n");
			return;
		}

		System.out.println("\n============= Remover produto ==============");
		listaProduto.listarProdutos();

		System.out.print("\nInforme o ID do produto a ser removido: ");
		int idDoProduto = tec.nextInt();

		if (idDoProduto > produtos.size()) {
			System.out.println("\nEste produto não existe.");
			return;
		}

		produtos.remove(idDoProduto);
		System.out.print("\nProduto removido com sucesso!");
	}

}
