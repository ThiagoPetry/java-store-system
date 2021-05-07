package br.com.tkp.controller.pessoa;

import java.util.List;
import java.util.Scanner;

import br.com.tkp.controller.produto.Produto;
import br.com.tkp.model.CarrinhoModel;
import br.com.tkp.model.PessoaModel;
import br.com.tkp.model.ProdutoModel;

public class AdicionaPessoa {

	private Scanner tec;
	Produto produtoController = new Produto();

	public AdicionaPessoa() {
		tec = new Scanner(System.in);
	}

	public String definirCliente() {
		System.out.print("Informe o nome do cliente: ");
		String nome = tec.next();
		return nome;
	}

	/* ===================================================================================================================== */

	public void menuConta(List<PessoaModel> pessoaModel, List<CarrinhoModel> produtosCarrinho, List<ProdutoModel> produtos) {
		AdicionaPessoa pessoaController = new AdicionaPessoa();

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
		PessoaModel pessoa = new PessoaModel();
		CarrinhoModel carrinho = new CarrinhoModel();

		System.out.print("\nCADASTRAR PESSOA\nNome: ");
		pessoa.setNome(tec.next());
		System.out.print("CPF: ");
		pessoa.setCpf(tec.next());
		pessoa.setCarrinhoModel(carrinho);

		return pessoa;
	}
}
