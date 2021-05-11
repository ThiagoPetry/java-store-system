package br.com.tkp.controller.carrinho;

import java.util.List;
import java.util.Scanner;

import br.com.tkp.controller.produto.EditaProduto;
import br.com.tkp.controller.produto.ListaProduto;
import br.com.tkp.model.CarrinhoModel;
import br.com.tkp.model.ProdutoModel;

public class AdicionaItemNoCarrinho {

	private Scanner tec;
	CarrinhoModel carrinhoModel = new CarrinhoModel();
	Carrinho carrinhoController = new Carrinho();
	EditaProduto editaProduto = new EditaProduto();

	public AdicionaItemNoCarrinho() {
		tec = new Scanner(System.in);
	}

	public CarrinhoModel cadastrarProdutoNoCarrinho(List<ProdutoModel> produtos) {
		ListaProduto listaProdutos = new ListaProduto();
		if (produtos.size() <= 0) {
			System.out.print("\nNão existe nenhum produto cadastrado.\n");
			return null;
		}

		listaProdutos.listarProdutos();

		System.out.print("\n---- Adicionar Item ----");
		System.out.print("\nInforme o ID: ");
		carrinhoModel.setIdDoProduto(tec.nextInt());
		int idDoProduto = carrinhoModel.getIdDoProduto();

		if (carrinhoModel.getIdDoProduto() > produtos.size()) {
			System.out.printf("\nEste produto não está cadastrado.");
			return null;
		}

		System.out.print("\nInforma a quantidade desejada: ");
		carrinhoModel.setQuantidadeDeItensNoCarrinho(tec.nextInt());

		if (carrinhoModel.getQuantidadeDeItensNoCarrinho() > produtos.get(idDoProduto).getQtdProd()) {
			System.out.print("\nEstoque insuficiente!");
			return null;
		}

		carrinhoModel.setProdutoModel(produtos.get(idDoProduto));
		carrinhoModel.setValorTotalPorItem(
				carrinhoModel.getQuantidadeDeItensNoCarrinho() * produtos.get(idDoProduto).getPrecoProd()
		);
		editaProduto.atualizarQuantidadeValorTotal(produtos, carrinhoModel.getQuantidadeDeItensNoCarrinho(), idDoProduto);

		return carrinhoModel;
	}
}
