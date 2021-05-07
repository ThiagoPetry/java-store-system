package br.com.tkp.controller.carrinho;

import java.util.List;

import br.com.tkp.model.CarrinhoModel;

public class ListaCarrinho {
	public List<CarrinhoModel> carrinho(List<CarrinhoModel> produtosCarrinho) {
		System.out.println("\n==================== Carrinho ===================");
		System.out.printf("| %2s | %10s | %8s | %4s | %9s |\n", "ID", "Nome", "Valor", "Qtd", "Total");
		System.out.printf("=================================================\n");

		produtosCarrinho.forEach(item -> {
			System.out.printf("| %2s | %10s | R$%6.2f | %4s | R$%7.2f |\n", produtosCarrinho.indexOf(item),
					item.getProdutoModel().getNomeProd(), item.getProdutoModel().getPrecoProd(),
					item.getQuantidadeDeItensNoCarrinho(), item.getValorTotalPorItem());
		});

		double valorTotalDoCarrinho = produtosCarrinho.stream().mapToDouble(item -> item.getValorTotalPorItem()).sum();

		System.out.printf("=================================================\n");
		System.out.println("Valor total: R$" + valorTotalDoCarrinho);

		return produtosCarrinho;
	}

	public void gerarCupom(List<CarrinhoModel> itensnDoCarrinho, String cliente) {
		if (itensnDoCarrinho.size() <= 0) {
			System.out.print("\nLista vazia.");
			return;
		}
		carrinho(itensnDoCarrinho);
		System.out.println("Cliente " + cliente);
	}
}
