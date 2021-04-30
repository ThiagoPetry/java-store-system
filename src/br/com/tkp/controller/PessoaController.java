package br.com.tkp.controller;

import java.util.List;
import java.util.Scanner;

import br.com.tkp.model.CarrinhoModel;
import br.com.tkp.model.PessoaModel;
import br.com.tkp.model.ProdutoModel;

public class PessoaController {

	private Scanner tec;
	ProdutoController produtoController = new ProdutoController();

	public PessoaController() {
		tec = new Scanner(System.in);
	}

	public void menuConta(List<PessoaModel> pessoaModel, List<CarrinhoModel> produtosCarrinho,
			List<ProdutoModel> produtos) {
		PessoaController pessoaController = new PessoaController();

		System.out.print("\nBem vindo!\n1 - Criar conta\n2 - Entrar em uma conta\n3 - Sair\nOpção: ");
		int opcao = tec.nextInt();

		switch (opcao) {
		case 1:
			pessoaModel.add(pessoaController.cadastrarPessoa(pessoaModel, produtosCarrinho));
			break;
		case 2:
			this.login(pessoaModel);
			break;
		case 3:
			break;
		default:
			System.out.print("\nOpção inválida!");
			break;
		}
	}

	public int verificaLogin(List<PessoaModel> pessoaModel, String cpf) {
		if (cpf.equals("123")) {
			return 123;
		} else {
			for (int x = 0; x < pessoaModel.size(); x++) {
				if (pessoaModel.get(x).getCpf().equals(cpf)) {
					return x;
				}
			}
			return -1;
		}
	}

	public void login(List<PessoaModel> pessoaModel) {
		System.out.print("\nLOGIN\nCPF: ");
		String cpf = tec.next();

		if (this.verificaLogin(pessoaModel, cpf) == -1) {
			System.out.print("\nLogin inválido!\n");
			this.login(pessoaModel);
		}
	}

	public PessoaModel cadastrarPessoa(List<PessoaModel> pessoas, List<CarrinhoModel> produtosCarrinho) {
		var pessoa = new PessoaModel();
		var carrinho = new CarrinhoModel();

		System.out.print("\nCADASTRAR PESSOA\nNome: ");
		pessoa.setNome(tec.next());
		System.out.print("CPF: ");
		pessoa.setCpf(tec.next());
		pessoa.setCarrinhoModel(carrinho);

		return pessoa;
	}

}
