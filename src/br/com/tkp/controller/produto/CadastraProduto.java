package br.com.tkp.controller.produto;

import java.util.Scanner;

import br.com.tkp.model.ProdutoModel;

public class CadastraProduto {

	private Scanner tec;

	public CadastraProduto() {
		tec = new Scanner(System.in);
	}

	public ProdutoModel cadastrarProduto() {
		ProdutoModel lojaModel = new ProdutoModel();
		
		System.out.print("\n==== Cadastrar Itens ====");
		System.out.print("\nProduto: ");
		lojaModel.setNomeProd(tec.next());
		System.out.print("Preço: ");
		lojaModel.setPrecoProd(tec.nextDouble());
		System.out.print("Quantidade: ");
		lojaModel.setQtdProd(tec.nextInt());
		lojaModel.setSaldoEstoque(lojaModel.getQtdProd() * lojaModel.getPrecoProd());
		
		return lojaModel;
	}

}
