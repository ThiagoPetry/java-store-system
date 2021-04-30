package br.com.tkp.model;

public class CarrinhoModel {

	private String nomeProduto;
	private double precoUnitario;
	private int qtdItens;
	private double precoTotal;

	public CarrinhoModel() {

	}

	public CarrinhoModel(String nomeProduto, double precoUnitario, int qtdItens, double precoTotal) {
		super();
		this.nomeProduto = nomeProduto;
		this.precoUnitario = precoUnitario;
		this.qtdItens = qtdItens;
		this.precoTotal = precoTotal;
	}

	public double getPrecoUnitario() {
		return precoUnitario;
	}

	public void setPrecoUnitario(double precoUnitario) {
		this.precoUnitario = precoUnitario;
	}

	public int getQtdItens() {
		return qtdItens;
	}

	public void setQtdItens(int qtdItens) {
		this.qtdItens = qtdItens;
	}

	public double getPrecoTotal() {
		return precoTotal;
	}

	public void setPrecoTotal(double precoTotal) {
		this.precoTotal = precoTotal;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}
}
