package br.com.tkp.controller.produto;

import java.util.List;
import java.util.Scanner;

import br.com.tkp.model.ProdutoModel;

public class EditaProduto {

	private Scanner tec;
	ProdutoModel produto = new ProdutoModel();

	public EditaProduto() {
		tec = new Scanner(System.in);
	}

	public ProdutoModel editarProduto(List<ProdutoModel> produtos) {
		ListaProduto listaProduto = new ListaProduto();
		int idDoProduto, indexDoCampo;

		if (produtos.size() <= 0) {
			System.out.println("\nNão existe nenhum produto cadastrado.");
			return null;
		}

		listaProduto.listarProdutos();

		System.out.println("\n============= Editar produto =============\n");
		System.out.print("Informe o ID do produto: ");
		idDoProduto = tec.nextInt();

		if (idDoProduto >= produtos.size()) {
			System.out.println("\nEste produto não existe.");
			return null;
		}

		System.out.print("\n1 - Nome do produto\n2 - Preço do produto\n3 - Quantidade do produto");
		System.out.print("\nInforme o campo que deseja editar: ");
		indexDoCampo = tec.nextInt();

		switch (indexDoCampo) {
		case 1:
			this.editarNomeDoProduto(produtos, idDoProduto);
			break;
		case 2:
			this.editarPrecoDoProduto(produtos, idDoProduto);
			break;
		case 3:
			this.editarQuantidadeDoProduto(produtos, idDoProduto);
			break;

		default:
			System.out.println("\nCampo inválido.");
			break;
		}
		return null;
	}
	
	private ProdutoModel editarNomeDoProduto(List<ProdutoModel> produtos, int idDoProduto) {
		System.out.print("Informe o novo nome do produto: ");
		produto.setNomeProd(tec.next());

		produto.setPrecoProd(produtos.get(idDoProduto).getPrecoProd());
		produto.setQtdProd(produtos.get(idDoProduto).getQtdProd());
		produto.setSaldoEstoque(produtos.get(idDoProduto).getSaldoEstoque());

		produtos.set(idDoProduto, produto);
		
		return produto;
	}
	
	private ProdutoModel editarPrecoDoProduto(List<ProdutoModel> produtos, int idDoProduto) {
		System.out.print("Informe o novo preço do produto: ");
		produto.setPrecoProd(tec.nextDouble());

		produto.setNomeProd(produtos.get(idDoProduto).getNomeProd());
		produto.setQtdProd(produtos.get(idDoProduto).getQtdProd());
		produto.setSaldoEstoque(produtos.get(idDoProduto).getQtdProd() * produto.getPrecoProd());

		produtos.set(idDoProduto, produto);
		
		return produto;
	}
	
	private ProdutoModel editarQuantidadeDoProduto(List<ProdutoModel> produtos, int idDoProduto) {
		System.out.print("Informe a nova quantidade do produto: ");
		produto.setQtdProd(tec.nextInt());

		produto.setPrecoProd(produtos.get(idDoProduto).getPrecoProd());
		produto.setNomeProd(produtos.get(idDoProduto).getNomeProd());
		produto.setSaldoEstoque(produtos.get(idDoProduto).getPrecoProd() * produto.getQtdProd());

		produtos.set(idDoProduto, produto);
		
		return produto;
	}

	public List<ProdutoModel> atualizarQuantidadeValorTotal(List<ProdutoModel> produtos, int quantidade, int idDoProduto) {

		produto.setQtdProd(produtos.get(idDoProduto).getQtdProd() - quantidade);
		produto.setSaldoEstoque(produtos.get(idDoProduto).getPrecoProd() * produto.getQtdProd());
		produto.setNomeProd(produtos.get(idDoProduto).getNomeProd());
		produto.setPrecoProd(produtos.get(idDoProduto).getPrecoProd());

		produtos.set(idDoProduto, produto);

		return produtos;
	}

}
