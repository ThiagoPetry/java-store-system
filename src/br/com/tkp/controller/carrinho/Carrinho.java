package br.com.tkp.controller.carrinho;

import java.util.List;
import java.util.Scanner;

import br.com.tkp.model.CarrinhoModel;
import br.com.tkp.model.ProdutoModel;

public class Carrinho {

	private Scanner tec;
	ListaCarrinho listaCarrinho = new ListaCarrinho();

	public Carrinho() {
		tec = new Scanner(System.in);
	}

	public void menuCarrinho(List<ProdutoModel> produtos, List<CarrinhoModel> produtosCarrinho, String  cliente) {
		listaCarrinho.carrinho(produtosCarrinho);

		System.out.print("\n"
				+ "======================\n"
				+ "1 - Finalizar compra\n"
				+ "2 - Remover item\n"
				+ "3 - Voltar ao menu\n"
				+ "======================\n"
				+ "> ");
		int opc = tec.nextInt();

		switch (opc) {
		case 1:
			listaCarrinho.gerarCupom(produtosCarrinho, cliente);
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
	
	// ListaCarrinho
//	public List<CarrinhoModel> carrinho(List<CarrinhoModel> produtosCarrinho) {
//		System.out.println("\n==================== Carrinho ===================");
//		System.out.printf("| %2s | %10s | %8s | %4s | %9s |\n", "ID", "Nome", "Valor", "Qtd", "Total");
//		System.out.printf("=================================================\n");
//		
//		produtosCarrinho.forEach(item -> {
//			System.out.printf("| %2s | %10s | R$%6.2f | %4s | R$%7.2f |\n",
//					produtosCarrinho.indexOf(item),
//					item.getProdutoModel().getNomeProd(),
//					item.getProdutoModel().getPrecoProd(),
//					item.getQuantidadeDeItensNoCarrinho(),
//					item.getValorTotalPorItem());
//		});
//		
//		double valorTotalDoCarrinho = produtosCarrinho.stream()
//				.mapToDouble(item -> item.getValorTotalPorItem()).sum();
//		
//				System.out.printf("=================================================\n");
//				System.out.println("Valor total: R$" + valorTotalDoCarrinho);
//				
//				
//		return produtosCarrinho;
//	}
	
//	public void gerarCupom(List<CarrinhoModel> itensnDoCarrinho, String cliente) {
//		if(itensnDoCarrinho.size() <= 0) {
//			System.out.print("\nLista vazia.");
//			return;
//		}
//		listaCarrinho.carrinho(itensnDoCarrinho);
//		System.out.println("Cliente " + cliente);
//	}
	
	// AdicionarItemNoCarrinho
//	public CarrinhoModel cadastrarProdutoNoCarrinho(List<ProdutoModel> produtos) {
//		CarrinhoModel carrinhoModel = new CarrinhoModel();
//		ProdutoController listaProdutos = new ProdutoController();
//		
//		if (produtos.size() <= 0) {
//			System.out.print("\nNão existe nenhum produto cadastrado.\n");
//			return null;
//		}
//		
//		listaProdutos.listarProdutos(produtos);
//		
//		System.out.print("\n---- Adicionar Item ----");
//		System.out.print("\nInforme o ID: ");
//		carrinhoModel.setIdDoProduto(tec.nextInt());
//		int idDoProduto = carrinhoModel.getIdDoProduto();
//		
//		if(carrinhoModel.getIdDoProduto() > produtos.size()) {
//			System.out.printf("\nEste produto não está cadastrado.");
//			return null;
//		}
//		
//		System.out.print("\nInforma a quantidade desejada: ");
//		carrinhoModel.setQuantidadeDeItensNoCarrinho(tec.nextInt());
//		
//		if(carrinhoModel.getQuantidadeDeItensNoCarrinho() > produtos.get(idDoProduto).getQtdProd()) {
//			System.out.print("\nEstoque insuficiente!");
//			return null;
//		}
//		
//		carrinhoModel.setProdutoModel(produtos.get(idDoProduto));
//		carrinhoModel.setValorTotalPorItem(carrinhoModel.getQuantidadeDeItensNoCarrinho() * produtos.get(idDoProduto).getPrecoProd());
//		
//		atualizarQuantidadeValorTotal(produtos, carrinhoModel.getQuantidadeDeItensNoCarrinho(), idDoProduto);
//		
//		return carrinhoModel;
//	}
	
//	public List<ProdutoModel> atualizarQuantidadeValorTotal(List<ProdutoModel> produtos, int quantidade, int idDoProduto) {
//		ProdutoModel produto = new ProdutoModel();
//		
//		produto.setQtdProd(produtos.get(idDoProduto).getQtdProd() - quantidade);
//		produto.setSaldoEstoque(produtos.get(idDoProduto).getPrecoProd() * produto.getQtdProd());
//		produto.setNomeProd(produtos.get(idDoProduto).getNomeProd());
//		produto.setPrecoProd(produtos.get(idDoProduto).getPrecoProd());
//		
//		produtos.set(idDoProduto, produto);
//		
//		return produtos;
//	}
	
	public void removerProdutoCarrinho(List<ProdutoModel> produtos, List<CarrinhoModel> produtosCarrinho) {
		if (produtosCarrinho.size() == 0) {
			System.out.print("\nNão existe nenhum produto no carrinho.\n");
			return;
		}

		System.out.print("\nInforme o ID do produto a ser removido: ");
		int idDoProduto = tec.nextInt();

		if (idDoProduto > produtosCarrinho.size()) {
			return;
		}

		produtos.get(idDoProduto).setQtdProd(
				produtos.get(idDoProduto).getQtdProd() + produtosCarrinho.get(idDoProduto).getQuantidadeDeItensNoCarrinho()
		);
		produtos.get(idDoProduto).setSaldoEstoque(
				produtos.get(idDoProduto).getQtdProd() * produtos.get(idDoProduto).getPrecoProd()
		);
		produtosCarrinho.remove(idDoProduto);
	}
}
