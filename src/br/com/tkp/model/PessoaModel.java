package br.com.tkp.model;

public class PessoaModel {

	private String nome;
	private String cpf;
	private CarrinhoModel carrinhoModel;

	public PessoaModel() {

	}

	public PessoaModel(String nome, String cpf, CarrinhoModel carrinhoModel) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.carrinhoModel = carrinhoModel;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public CarrinhoModel getCarrinhoModel() {
		return carrinhoModel;
	}

	public void setCarrinhoModel(CarrinhoModel carrinhoModel) {
		this.carrinhoModel = carrinhoModel;
	}
}
